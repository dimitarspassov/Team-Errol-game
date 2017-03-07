package game;

import display.Display;
import graphics.ImageLoader;
import units.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

//By far the most complex component of our project. This is the game itself.

public class Game extends JFrame implements Runnable {
    public static boolean IS_BALL_SPEED_UP = false;

    private String name;
    private int width, height;

    private Display display;
    private Platform platform;
    private Ball ball;
    private Ball ballSecond;
    private Ball ballThird;

    private static boolean isGamePaused;
    private static boolean isSoundMuted;
    private Brick[] bricks;
    private Stone[] stones;
    private ArrayList<Bonus> bonuses;
    private int bricksRemaining;

    private static byte currentLevel;
    private byte maxLevel;
    public static boolean levelSwitched;

    public static Highscores highScores;

    public BufferStrategy bs;
    public Graphics graphics;

    public void addBonus(Bonus bonus) {
        this.bonuses.add(bonus);
    }

    private Thread thread;
    public static boolean isRunning;
    private GameTimer gameTimer;
    private int secondsRemaining;
    public static StringBuilder playerName;

    private Menu menu;
    public static int lastResult;
    public static long lastBonusPoints;
    private int score;
    private int levelScore;

    public static enum STATE {
        MENU,
        GAME,
        MID_LEVEL_PAUSE,
        PLAYER_INIT,
        HIGHSCORES,
        GAME_OVER,
        WIN
    }

    public static STATE State = STATE.MENU;


    public Game(String name, int width, int height) {

        this.name = name;
        this.width = width;
        this.height = height;

    }

    public void initialization() {

        this.display = new Display(name, width, height);
        this.addKeyListener(new InputHandler(this.display.getCanvas()));
        this.menu = new Menu();
        this.addMouseListener(new MouseInput(this.display.getCanvas()));
        currentLevel = 1;
        this.maxLevel = 10;
        levelSwitched = true;
        this.bricks = new Brick[1];
        this.stones = null;
        this.bonuses = new ArrayList<>();
        playerName = new StringBuilder("");
        highScores = new Highscores();
        this.gameTimer = new GameTimer();
    }

    public void thick() {

        if (State == STATE.GAME) {
            this.platform.thick();
        }

    }

    public void render() {


        //This is the buffered strategy. We get it from the canvas. If it is null, we set it with 2 buffers.
        //We can change it later.
        this.bs = this.display.getCanvas().getBufferStrategy();

        if (this.bs == null) {

            this.display.getCanvas().createBufferStrategy(2);
            this.bs = this.display.getCanvas().getBufferStrategy();
        }
        this.graphics = this.bs.getDrawGraphics();

        if (levelSwitched) {
            if (currentLevel == 1) {
                score = 0;
                levelScore = 0;
            }
            levelSwitched = false;
            this.bricks = Level.getLevel(currentLevel);
            this.bricksRemaining = this.bricks.length;
            this.platform = new Platform(350, 550, 100, 20, 12);
            this.stones = Level.getStones(currentLevel);
            this.ball = new Ball(350, 550, 10, 20, 20, 5, 5, platform, bricks, stones);
            this.ball.isSpacePressed = false;
            // this.ballSecond = new Ball(350, 550, 10, 20, 20, -5, 5, platform, bricks, stones);
            levelScore = 0;
            this.gameTimer.setStartTime(System.currentTimeMillis());
            // this.secondsRemaining = this.gameTimer.getSeconds();
        }

        if (State == STATE.GAME) {

            switch (currentLevel) {
                case 1:
                    this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic1.png"), 0, 0, 800, 600, null);
                    break;
                case 2:
                    this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic2.png"), 0, 0, 800, 600, null);
                    break;
                case 3:
                    this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic3.png"), 0, 0, 800, 600, null);
                    break;
                case 4:
                    this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic4.png"), 0, 0, 800, 600, null);
                    break;
                case 5:
                    this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic5.png"), 0, 0, 800, 600, null);
                    break;
                case 6:
                    this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic6.png"), 0, 0, 800, 600, null);
                    break;
                case 7:
                    this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic7.png"), 0, 0, 800, 600, null);
                    break;
                case 8:
                    this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic8.png"), 0, 0, 800, 600, null);
                    break;
                case 9:
                    this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic9.png"), 0, 0, 800, 600, null);
                    break;
                default:
                    this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic10.png"), 0, 0, 800, 600, null);
                    break;
            }


            //Creating the platform
            this.platform.render(graphics);
            this.graphics.drawImage(ImageLoader.loadImage("/latest-platform.png"),
                    platform.getPlatformX(),
                    platform.getPlatformY(),
                    platform.getPlatformWidth(),
                    platform.getPlatformHeight(), null);
            //Main Ball
            this.ball.render(graphics);
            this.graphics.setColor(Color.WHITE);
            this.graphics.fillOval((int) ball.getCenterX(), (int) ball.getCenterY(), ball.getH(), ball.getW());
            //Second Ball
            if (this.ballSecond != null) {
                this.ballSecond.render(graphics);
                // this.graphics.setColor(Color.WHITE);
                this.graphics.fillOval((int) ballSecond.getCenterX(), (int) ballSecond.getCenterY(), ballSecond.getH(), ballSecond.getW());
            }
            //Third Ball
            if (this.ballThird != null) {
                this.ballThird.render(graphics);
                //    this.graphics.setColor(Color.WHITE);
                this.graphics.fillOval((int) ballThird.getCenterX(), (int) ballThird.getCenterY(), ballThird.getH(), ballThird.getW());
            }

            // Draw the bricks
            score -= levelScore;
            levelScore = 0;
            this.bricksRemaining = Level.getLevel(currentLevel).length;
            for (Brick brick : this.bricks) {

                // If brick is destroyed, continue to next brick.
                if (brick.isDestroyed()) {
                    // Increment player scores
                    levelScore += 5;
                    this.bricksRemaining--;
                } else {
                    // Else, draw the brick.
                    if (this.bricksRemaining != 0) {
                        this.graphics.drawImage(brick.getImage(), brick.getX(), brick.getY(),
                                brick.getWidth(), brick.getHeight(), this);
                    }
                }
            }
            score += levelScore;

            if (stones != null) {
                for (Stone stone : this.stones) {
                    this.graphics.drawImage(stone.getImage(), stone.getX(), stone.getY(),
                            stone.getWidth(), stone.getHeight(), this);
                }
            }
            //Bonuses
            if (bonuses != null) {
                for (Bonus bonus : this.bonuses) {
                    if (bonus.getStatus()) {
                        bonus.setY(bonus.getY() + 3);
                        this.graphics.drawImage(bonus.getImage(), bonus.getX(), bonus.getY(),
                                bonus.getWidth(), bonus.getHeight(), this);
                    }
                    if (bonus.getRect().intersects(new Rectangle(platform.getPlatformX(), platform.getPlatformY(), platform.getPlatformWidth(), platform.getPlatformHeight()))) {
                        String bonusType = bonus.getBonusType();
                        bonus.setStatus(false);
                        switch (bonusType) {
                            case "ballSizeUp":
                                //Ball Size Up Bonus
                                this.ball.sizeUp();
                                if (this.ballSecond != null) {
                                    this.ballSecond.sizeUp();
                                }
                                if (this.ballThird != null) {
                                    this.ballThird.sizeUp();
                                }
                                break;
                            case "platformSizeUp":
                                //Platform Size Up Bonus
                                this.platform.sizeUp();
                                break;
                            case "platformSizeDown":
                                //Platform Size Down Bonus
                                this.platform.sizeDown();
                                break;

                            case "ballSpeedUp":
                                //Ball Speed Up Bonus
                                this.ball.speedUp();
                                if (this.ballSecond != null) {
                                    this.ballSecond.speedUp();
                                }
                                if (this.ballThird != null) {
                                    this.ballThird.speedUp();
                                }
                                break;

                            case "platformSpeedUp":
                                //Platform Speed Up Bonus
                                this.platform.speedUp();
                                break;
                            case "threeBalls":
                                //Three Ball Bonus
                                this.ballSecond = new Ball(
                                        (int) this.ball.getCenterX(),
                                        (int) this.ball.getCenterY(),
                                        this.ball.getRadius(),
                                        this.ball.getW(),
                                        this.ball.getH(),
                                        this.ball.getSpeedX(),
                                        this.ball.getSpeedY() * -1,
                                        platform, bricks, stones);
                                this.ballThird = new Ball(
                                        (int) this.ball.getCenterX(),
                                        (int) this.ball.getCenterY(),
                                        this.ball.getRadius(),
                                        this.ball.getW(), this.ball.getH(),
                                        this.ball.getSpeedX() * -1,
                                        this.ball.getSpeedY(),
                                        platform, bricks, stones);
                                break;


                        }

                    }
                }
                // ArrayList<Bonus> newBonuses = new ArrayList<>();
                // for (Bonus bonus : this.bonuses) {
                //    if(bonus.getStatus()){
                //        newBonuses.add(bonus);
                //    }
                // }
                // this.bonuses=newBonuses;
            }

            lastResult = score;
            // Show player scores
            this.graphics.setFont(new Font("serif", Font.BOLD, 27));
            // this.secondsRemaining = gameTimer.getSeconds();
            //  this.graphics.drawString("Seconds: " + secondsRemaining, 30, 30);
            long bonusPoints = 60 - this.gameTimer.getElapsedTime();
          //  System.out.println(bonusPoints);
            if(currentLevel == 1 || currentLevel == 2){
                if (bonusPoints >= 0) {

                    this.graphics.drawString("Bonus Points: " + bonusPoints, 30, 30);
                }

            } else {
                bonusPoints = 120 - this.gameTimer.getElapsedTime();
                if (bonusPoints >= 0) {

                    this.graphics.drawString("Bonus Points: " + bonusPoints, 30, 30);
                }
            }
            lastBonusPoints =bonusPoints;

            this.graphics.drawString("Score: " + score, 620, 30);

            // Draw buttons when user is paused the game
            if (isGamePaused) {
                this.graphics.drawImage(ImageLoader.loadImage("/button_resume-game.png"), 300, 250, 200, 50, null);
                this.graphics.drawImage(ImageLoader.loadImage("/button_exit.png"), 300, 350, 200, 50, null);
            }

            // Draw image for state of sound
            if (isSoundMuted) {
                this.graphics.drawImage(ImageLoader.loadImage("/mute.png"), 740, 50, 40, 40, null);
            } else {
                this.graphics.drawImage(ImageLoader.loadImage("/sound.png"), 740, 50, 40, 40, null);
            }

        } else {
            this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic.png"), 0, 0, 800, 600, null);
            this.menu.render(graphics, currentLevel);
        }

        // Whatever we draw, it finally goes through dispose and the it is shown.
        this.graphics.dispose();
        this.bs.show();
    }

    @Override
    public void run() {

        // Here we initialize the game loop.
        this.initialization();

        int fps = 60;
        double timePerTick = 1_000_000_000 / fps;

        double delta = 0;
        long lasTimeTicked = System.nanoTime();

        while (isRunning) {

            if (isGamePaused) {
                pause();
            }

            long now = System.nanoTime();

            delta += (now - lasTimeTicked) / timePerTick;

            if (delta >= 2) {
                thick();
                delta--;
                ball.move(this);
                if (ballSecond != null) {
                    ballSecond.move(this);
                }
                if (ballThird != null) {
                    ballThird.move(this);
                }

            }
            render();


            if (this.bricksRemaining == 0 && State == STATE.GAME) {

                //If a player passes level 1 or level 2 under 1 minute - gets bonus points 60 minus one's points
                //Example - player passes level one for 50 seconds - one gets 60 - 50 = 10 points bonus
                if(currentLevel == 1 || currentLevel == 2){
                    if( this.gameTimer.getElapsedTime()/60 < 1){
                        long bonusPointsFromTimer = 60 - (this.gameTimer.getElapsedTime()%60);

                        this.score += bonusPointsFromTimer;
                        lastBonusPoints =bonusPointsFromTimer;

                    }

                    //Other levels should be passed for less than 2 minutes to get bonus points
                } else {
                    if( this.gameTimer.getElapsedTime()/60 < 2){
                        long bonusPointsFromTimer = 60 - (this.gameTimer.getElapsedTime()%60);
                        //this.graphics.drawString("Bonus Points: " + bonusPointsFromTimer, 30, 30);
                        this.score += bonusPointsFromTimer;
                        lastBonusPoints =bonusPointsFromTimer;
                    }
                }


                currentLevel++;
                levelSwitched = true;
                if (currentLevel > this.maxLevel) {
                    State = STATE.WIN;

                } else {
                    playSound(this, "/sounds/level_complete.wav");
                    State = STATE.MID_LEVEL_PAUSE;
                    this.initLevel();
                }
            }
            //Bonus clear
            ArrayList<Bonus> newBonuses = new ArrayList<>();
            for (Bonus bonus : this.bonuses) {
                if (bonus.getStatus() && bonus.getY() < 570) {
                    newBonuses.add(bonus);
                }
            }
            this.bonuses = newBonuses;

            if (this.ballSecond != null && this.ballSecond.getCenterY() >= 570) {
                this.ballSecond = null;
            }
            if (this.ballThird != null && this.ballThird.getCenterY() >= 570) {
                this.ballThird = null;
            }
            // Stop the game when the ball exits game field
            if (!this.levelSwitched && this.ball.getCenterY() >= 570) {
                if (this.ballSecond != null) {
                    this.ball = new Ball((int) this.ballSecond.getCenterX(), (int) this.ballSecond.getCenterY(), this.ballSecond.getRadius(), this.ballSecond.getW(), this.ballSecond.getH(), this.ballSecond.getSpeedX(), this.ballSecond.getSpeedY(), platform, bricks, stones);

                    this.ballSecond = null;
                } else if (this.ballThird != null) {
                    this.ball = new Ball((int) this.ballThird.getCenterX(), (int) this.ballThird.getCenterY(), this.ballThird.getRadius(), this.ballThird.getW(), this.ballThird.getH(), this.ballThird.getSpeedX(), this.ballThird.getSpeedY(), platform, bricks, stones);

                    this.ballThird = null;
                } else {
                    State = STATE.GAME_OVER;
                    this.levelSwitched = true;
                    this.initLevel();
                    currentLevel = 1;
                }
            }
        }

        this.stop();
    }

    private void initLevel() {
        this.ball = new Ball(350, 550, 10, 20, 20, 5, 5, platform, bricks, stones);
        this.ballSecond = null;
        this.ballThird = null;
        this.bonuses = new ArrayList<>();
    }

    private synchronized void pause() {

        while (isGamePaused) {
            render();
            Thread.yield();
        }
    }

    public static boolean getPauseState() {
        return isGamePaused;
    }

    public static void turnPauseOnOff(boolean state) {
        isGamePaused = state;
    }

    public static void turnSoundOnOff() {
        isSoundMuted = !isSoundMuted;
    }

    public synchronized void start() {

        this.isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {

        try {
            this.isRunning = false;
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    static byte getCurrentLevel() {
        return currentLevel;
    }

    static void setCurrentLevel(byte level) {

        currentLevel = level;
    }

    public static void playSound(Object object, String filename) {

        if (!isSoundMuted) {
            try {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(object.getClass().getResource(filename));
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

