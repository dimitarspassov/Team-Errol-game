package game;

import display.Display;
import graphics.ImageLoader;
import units.Ball;
import units.Brick;
import units.Platform;
import units.Stone;

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


    private Brick[] bricks;
    private Stone[] stones;
    private int bricksRemaining;

    public static byte currentLevel;
    private byte maxLevel;
    public static boolean levelSwitched;

    public static HighScores highScores;

    public BufferStrategy bs;
    public Graphics graphics;
    private Thread thread;
    private boolean isRunning;


    public static StringBuilder playerName;

    private Menu menu;
    public static int result;
    public static int lastResult;
    private int score;

    public static enum STATE {
        MENU,
        GAME,
        PAUSE,
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
        this.currentLevel = 1;
        this.maxLevel = 4;
        this.levelSwitched = true;
        this.bricks = new Brick[1];
        this.stones = new Stone[3];

        result = 0;
        playerName = new StringBuilder("");
        this.highScores = new HighScores();
    }

    public void thick() {

        if (State == STATE.GAME) {
            this.platform.thick();
        }

    }


    public void render() {

        score = result;


        //This is the buffered strategy. We get it from the canvas. If it is null, we set it with 2 buffers.
        //We can change it later.
        this.bs = this.display.getCanvas().getBufferStrategy();


        if (this.bs == null) {

            this.display.getCanvas().createBufferStrategy(2);
            this.bs = this.display.getCanvas().getBufferStrategy();
        }
        this.graphics = this.bs.getDrawGraphics();

        //Here we draw the background on the canvas.
        this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic.png"), 0, 0, 800, 600, null);


        if (this.levelSwitched) {
            if (currentLevel == 1) {
                result = 0;
            }
            this.levelSwitched = false;
            this.bricks = Level.getLevel(this.currentLevel);
            this.bricksRemaining = this.bricks.length;
            this.platform = new Platform(350, 550, 100, 10, 30);
            this.ball = new Ball(350, 550, 10, 20, 20, 5, 5, platform, bricks,stones);
            this.ball.isSpacePressed = false;
        }

        if (State == STATE.GAME) {

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
            //Stones
            if(this.currentLevel==5){
                for (int i = 0; i < 1; i++) {
                    for (int j = 0; j < 3; j++) {
                        stones[j] = new Stone(90 + j * 40 *6, 280 + i * 12 * 3);
                    }
                }
                for (Stone stone : this.stones) {
                    this.graphics.drawImage(stone.getImage(), stone.getX(), stone.getY(),
                            stone.getWidth(), stone.getHeight(), this);
                }
            }

            lastResult = score;
            // Show player scores
            this.graphics.setFont(new Font("serif", Font.BOLD, 27));
            this.graphics.drawString("" + score, 740, 30);

        } else {
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
        long now;
        long lasTimeTicked = System.nanoTime();

        while (isRunning) {
            now = System.nanoTime();

            delta += (now - lasTimeTicked) / timePerTick;

            if (delta >= 1) {
                thick();
                render();
                delta--;
                ball.move();
            }


            if (this.bricksRemaining == 0 && State == STATE.GAME) {
                currentLevel++;
                levelSwitched = true;
                result += score;
                if (this.currentLevel > this.maxLevel) {
                    State = STATE.WIN;
                } else {
                    State = STATE.PAUSE;
                }
            }

            // Stop the game when the ball exits game field
            if (!this.levelSwitched && this.ball.getCenterY() >= 570) {
                State = STATE.GAME_OVER;
                this.levelSwitched = true;
                this.currentLevel = 1;
            }
        }

        this.stop();
    }

    public synchronized void start() {

        this.isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {

        try {
            this.isRunning = false;
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
