package game;

import display.Display;
import graphics.ImageLoader;
import units.Ball;
import units.Brick;
import units.Platform;
import units.Stone;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

//By far the most complex component of our project. This is the game itself.

public class Game extends JFrame implements Runnable {

    private String name;
    private int width, height;

    private Display display;
    private Platform platform;
    private Ball ball;

    private static boolean isGamePaused;
    private Brick[] bricks;
    private Stone[] stones;
    private int bricksRemaining;

    private static byte currentLevel;
    private byte maxLevel;
    public static boolean levelSwitched;

    public static Highscores highScores;

    public BufferStrategy bs;
    public Graphics graphics;
    private Thread thread;
    public static boolean isRunning;


    public static StringBuilder playerName;

    private Menu menu;
    public static int lastResult;
    private int score;

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
        this.levelSwitched = true;
        this.bricks = new Brick[1];
        this.stones = null;

        score = 0;
        playerName = new StringBuilder("");
        this.highScores = new Highscores();
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

        if (this.levelSwitched) {
            if (currentLevel == 1) {
                score = 0;
            }
            this.levelSwitched = false;
            this.bricks = Level.getLevel(currentLevel);
            this.bricksRemaining = this.bricks.length;
            this.platform = new Platform(350, 550, 100, 20, 30);
            this.stones = Level.getStones(currentLevel);
            this.ball = new Ball(350, 550, 10, 20, 20, 5, 5, platform, bricks, stones);
            this.ball.isSpacePressed = false;
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
            this.graphics.drawImage(ImageLoader.loadImage("/platform.png"),
                    platform.getPlatformX(),
                    platform.getPlatformY(),
                    platform.getPlatformWidth(),
                    platform.getPlatformHeight(), null);

            this.ball.render(graphics);
            this.graphics.setColor(Color.WHITE);
            this.graphics.fillOval((int) ball.getCenterX(), (int) ball.getCenterY(), ball.getH(), ball.getW());
            // Draw the bricks

            this.bricksRemaining = Level.getLevel(currentLevel).length;
            for (Brick brick : this.bricks) {

                // If brick is destroyed, continue to next brick.
                if (brick.destroyed) {
                    // Increment player scores
                    score += 5;
                    this.bricksRemaining--;
                } else {
                    // Else, draw the brick.
                    if (this.bricksRemaining != 0) {
                        this.graphics.drawImage(brick.getImage(), brick.getX(), brick.getY(),
                                brick.getWidth(), brick.getHeight(), this);
                    }
                }
            }


            if (stones != null) {
                for (Stone stone : this.stones) {
                    this.graphics.drawImage(stone.getImage(), stone.getX(), stone.getY(),
                            stone.getWidth(), stone.getHeight(), this);
                }
            }

            lastResult = score;
            // Show player scores
            this.graphics.setFont(new Font("serif", Font.BOLD, 27));
            this.graphics.drawString("" + score, 740, 30);

            // Draw buttons when user is paused the game
            if (isGamePaused) {
                this.graphics.drawImage(ImageLoader.loadImage("/button_resume-game.png"), 300, 250, 200, 50, null);
                this.graphics.drawImage(ImageLoader.loadImage("/button_exit.png"), 300, 350, 200, 50, null);
            }

        } else {
            this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic.png"), 0, 0, 800, 600, null);
            this.menu.render(graphics, currentLevel);
        }
        //Take a careful look at these two operations. This is the cornerstone of visualizing our graphics.
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
                ball.move();
            }
            render();


            if (this.bricksRemaining == 0 && State == STATE.GAME) {
                currentLevel++;
                levelSwitched = true;
                if (currentLevel > this.maxLevel) {
                    State = STATE.WIN;

                } else {
                    playSound(this, "/sounds/level_complete.wav");
                    State = STATE.MID_LEVEL_PAUSE;
                }
            }

            // Stop the game when the ball exits game field
            if (!this.levelSwitched && this.ball.getCenterY() >= 570) {
                State = STATE.GAME_OVER;
                this.levelSwitched = true;
                currentLevel = 1;
            }
        }

        this.stop();
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
