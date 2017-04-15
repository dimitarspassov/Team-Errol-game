package game;

import display.Display;
import enumerations.State;
import graphics.BackgroundLoader;
import graphics.ImageLoader;
import sound.SoundLoader;
import units.balls.Ball;
import units.balls.SimpleBall;
import units.bonuses.Bonus;
import units.bricks.Brick;
import units.bricks.Stone;
import units.platform.Platform;
import units.platform.SimplePlatform;
import utilities.StaticData;
import utilities.UnitLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//By far the most complex component of our project. This is the game itself.

public class Game extends JFrame implements Runnable {

    private static final byte MAX_LEVEL = 10;

    private String name;
    private int width, height;

    private Display display;
    private Platform platform;
    private List<Ball> balls;

    private static boolean isGamePaused;
    private static boolean isSoundMuted;
    private Brick[] bricks;
    private Stone[] stones;
    private ArrayList<Bonus> bonuses;
    private int bricksRemaining;
    private boolean unitsInitialized;

    private static byte currentLevel = 1;

    public static boolean levelSwitched;

    public static Highscores highScores;

    private SoundLoader soundLoader;
    private BufferStrategy bs;
    private Graphics graphics;

    public void addBonus(Bonus bonus) {
        this.bonuses.add(bonus);
    }

    private Thread thread;
    private boolean isRunning;
    private GameTimer gameTimer;
    private StringBuilder playerName;

    private Menu menu;
    static int lastResult;
    static long lastBonusPoints;
    private int score;
    private int levelScore;
    private int lives;
    private State state;


    public Game(String name, int width, int height) {

        this.name = name;
        this.width = width;
        this.height = height;
        this.state = State.MENU;
    }

    public void liveUp() {
        this.lives++;
    }

    private void initialization() {

        this.display = new Display(name, width, height);
        this.addKeyListener(new InputHandler(this.display.getCanvas(), this));
        this.menu = new Menu(this);
        this.lives = 3;
        this.addMouseListener(new MouseInput(this.display.getCanvas(), this));
        currentLevel = 1;
        levelSwitched = true;
        this.bricks = new Brick[0];
        this.stones = null;
        this.bonuses = new ArrayList<>();
        playerName = new StringBuilder("");
        highScores = new Highscores();
        this.gameTimer = new GameTimer();
        this.soundLoader = new SoundLoader(this);
        soundLoader.playBackgroundMusic(false);
    }

    private void thick() {

        if (this.state == State.GAME && unitsInitialized) {
            this.platform.thick();
            balls.stream().forEach(b -> b.move(this));
        }

    }

    private void render() {


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
                this.lives = 3;
            }
            levelSwitched = false;

            this.bricks = UnitLoader.getBricks(currentLevel);
            this.bricksRemaining = this.bricks.length;
            this.platform = new SimplePlatform(350, 550, 100, 20, 12);
            this.stones = UnitLoader.getStones(currentLevel);
            this.balls = new ArrayList<>();
            balls.add(new SimpleBall(350, 550, 10, 20, 20, 5, 5, platform, bricks, stones));
            balls.get(0).pressSpace(false);
            levelScore = 0;
            this.gameTimer.setStartTime(System.currentTimeMillis());
            unitsInitialized = true;
        }

        soundLoader.playBackgroundMusic(isSoundMuted);
        if (this.state == State.GAME) {


            BackgroundLoader.setBackgroundForLevel(currentLevel, graphics);

            //Creating the platform
            this.platform.render(graphics);
            this.graphics.drawImage(platform.getImage(),
                    platform.getX(),
                    platform.getY(),
                    platform.getWidth(),
                    platform.getHeight(), null);


            UnitLoader.renderBalls(balls, graphics);

            // Draw the bricks
            score -= levelScore;
            levelScore = 0;
            this.bricksRemaining = this.bricks.length;
            for (Brick brick : this.bricks) {

                // If bricks is destroyed, continue to next bricks.
                if (brick.isDestroyed()) {
                    // Increment player scores
                    levelScore += 5;
                    this.bricksRemaining--;
                } else {
                    // Else, draw the bricks.
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
                UnitLoader.renderBonuses(this.bonuses, balls, bricks, stones, this.platform, this.graphics,this);
            }

            lastResult = score;
            // Show player scores
            this.graphics.setColor(Color.WHITE);
            this.graphics.setFont(new Font("serif", Font.BOLD, 27));

            long bonusPoints = 60 - this.gameTimer.getElapsedTime();

            if (currentLevel == 1 || currentLevel == 2) {
                if (bonusPoints >= 0) {

                    this.graphics.drawString("Bonus Points: " + bonusPoints, 30, 30);
                }

            } else {
                bonusPoints = 120 - this.gameTimer.getElapsedTime();
                if (bonusPoints >= 0) {

                    this.graphics.drawString("Bonus Points: " + bonusPoints, 30, 30);
                }
            }
            lastBonusPoints = bonusPoints;

            this.graphics.drawString("Score: " + score, 620, 30);
            this.graphics.drawString("Lives: " + this.lives, 300, 30);


            // Draw image for state of sound
            if (isSoundMuted) {
                this.graphics.drawImage(ImageLoader.loadImage(StaticData.PIC_MUTE), 740, 50, 40, 40, null);
            } else {
                this.graphics.drawImage(ImageLoader.loadImage(StaticData.PIC_SOUND), 740, 50, 40, 40, null);
            }

        } else if (this.state == State.PAUSE) {
            // Draw buttons when user is paused the game
            if (isGamePaused) {
                this.graphics.drawImage(ImageLoader.loadImage(StaticData.BUTTON_RESUME_GAME), 300, 250, 200, 50, null);
                this.graphics.drawImage(ImageLoader.loadImage(StaticData.BUTTON_EXIT), 300, 350, 200, 50, null);
            }
        } else {

            this.graphics.drawImage(ImageLoader.loadImage(StaticData.BACKGROUND_PIC), 0, 0, 800, 600, null);
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
            lasTimeTicked = now;

            if (delta >= 1) {
                thick();
                delta--;

            }
            render();


            if (this.bricksRemaining == 0 && this.state == State.GAME && unitsInitialized) {

                //If a player passes level 1 or level 2 under 1 minute - gets bonus points 60 minus one's points
                //Example - player passes level one for 50 seconds - one gets 60 - 50 = 10 points bonus
                if (currentLevel == 1 || currentLevel == 2) {
                    if (this.gameTimer.getElapsedTime() / 60 < 1) {
                        long bonusPointsFromTimer = 60 - (this.gameTimer.getElapsedTime() % 60);

                        this.score += bonusPointsFromTimer;
                        lastBonusPoints = bonusPointsFromTimer;

                    }

                    //Other levels should be passed for less than 2 minutes to get bonus points
                } else {
                    if (this.gameTimer.getElapsedTime() / 60 < 2) {
                        long bonusPointsFromTimer = 60 - (this.gameTimer.getElapsedTime() % 60);
                        //this.graphics.drawString("Bonus Points: " + bonusPointsFromTimer, 30, 30);
                        this.score += bonusPointsFromTimer;
                        lastBonusPoints = bonusPointsFromTimer;
                    }
                }


                currentLevel++;
                levelSwitched = true;
                if (currentLevel > MAX_LEVEL) {
                    this.state = State.WIN;
                    soundLoader.playSound(StaticData.SOUND_LEVEL_COMPLETE);

                } else {
                    soundLoader.playSound(StaticData.SOUND_LEVEL_COMPLETE);
                    this.state = State.PAUSE_BETWEEN_LEVELS;
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

            if (this.state == State.GAME && unitsInitialized) {

                balls = balls.stream().filter(ball -> ball.getY() < 570).collect(Collectors.toList());

                // Stop the game when all balls exit game field
                if (balls.size() == 0) {

                    this.lives--;

                    if (lives == 0) {
                        this.state = State.GAME_OVER;
                        levelSwitched = true;
                        this.initLevel();
                        currentLevel = 1;
                    } else {
                        this.platform = new SimplePlatform(350, 550, 100, 20, 12);
                        balls.add(new SimpleBall(350, 550, 10, 20, 20, 5, 5, platform, bricks, stones));
                        this.pressSpace(false);
                    }
                }
            }
        }

        this.stop();
    }

    private void initLevel() {
        balls = new ArrayList<>();
        balls.add(new SimpleBall(350, 550, 10, 20, 20, 5, 5, platform, bricks, stones));
        this.bonuses = new ArrayList<>();
    }

    private synchronized void pause() {

        while (isGamePaused) {
            render();
            Thread.yield();
        }
    }

    public static boolean isSoundMuted() {
        return isSoundMuted;
    }

    static boolean isPaused() {
        return isGamePaused;
    }

    void turnPauseOnOff(boolean state) {
        isGamePaused = state;

        if (isGamePaused) {
            this.state = State.PAUSE;
        } else {
            this.state = State.GAME;
        }
    }

    static void turnSoundOnOff() {
        isSoundMuted = !isSoundMuted;
    }

    synchronized void start() {

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

    public State getGameState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void playSound(String fileName) {
        this.soundLoader.playSound(fileName);
    }

    public void pressSpace(boolean command) {
        this.balls.forEach(b -> b.pressSpace(command));
    }

    public Platform getPlatform() {
        return this.platform;
    }

    public StringBuilder getPlayerName() {
        return this.playerName;
    }
}

