package game;

import display.Display;
import graphics.ImageLoader;
import units.Ball;
import units.Brick;
import units.Platform;

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
    private int bricksRemaining;
    public BufferStrategy bs;

    public Graphics graphics;
    private Thread thread;
    private boolean isRunning;
    public boolean menuMode;


    private InputHandler ih;

    public Game(String name, int width, int height) {

        this.name = name;
        this.width = width;
        this.height = height;

    }

    public void initialization() {
        //Initial of brick composition, to be done with load of file for every Level
        Brick bricks[];
        bricks = new Brick[30];
        int bricksRemaining = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                bricks[bricksRemaining++] = new Brick(40 + j * 40 * 3, 48 + i * 12 * 3);
            }
        }
        this.display = new Display(name, width, height);
        this.ih = new InputHandler(this.display.getCanvas(), true);
        this.platform = new Platform(350, 550, 100, 10, 30);
        this.ball = new Ball(350, 550, 15, 30, 30, 5, 5, platform, bricks);
        this.bricks = bricks;
        this.bricksRemaining = bricksRemaining;  //if brickRemaining=0 then level ends
    }

    public void thick() {
        this.platform.thick();
    }

    public void displayMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new GameMenu());
        //  menuBar.add(new ColorMenu());
        //  menuBar.add(new SpeedMenu());
        JFrame frame = this.display.getFrame();
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        frame.setSize(350, 250);
    }

    public void render() {


        //Display Game Menu
//        displayMenu();


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

        //This is the place for rendering graphics.

        //By default the menu mode is true. As we render, if the ENTER key is pressed, menu mode becomes false and then the game starts.

        if (this.menuMode) {

            this.graphics.drawImage(ImageLoader.loadImage("/starter.png"), 200, 150, 400, 150, null);
            this.menuMode = ih.isMenuModeOn();

        } else {
            //Creating the platform

            this.platform.render(graphics);
            this.graphics.drawImage(ImageLoader.loadImage("/platform.png"),
                    platform.getPlatformX(),
                    platform.getPlatformY(),
                    platform.getPlatformWidth(),
                    platform.getPlatformHeight(), null);


            this.ball.render(graphics);
            this.graphics.setColor(Color.RED);
            this.graphics.fillOval((int) ball.getCenterX(), (int) ball.getCenterY(), ball.getH(), ball.getW());
            // Draw the bricks
            this.bricksRemaining = 30;
            for (Brick brick : bricks) {
                // If brick is destroyed, continue to next brick.
                if (brick.destroyed) {
                    this.bricksRemaining--;
                    continue;
                } else {
                    // Else, draw the brick.
                    if (this.bricksRemaining != 0)
                        this.graphics.drawImage(brick.getImage(), brick.getX(), brick.getY(),
                                brick.getWidth(), brick.getHeight(), this);
                }
            }
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
            //To be managed level complete

            //Here we have a crucial game element - level switching!
            //If we have run out of bricks, we set the menuMode On. We also assign the remaining bricks with -1. Thus we are sure that
            // the level will be switched ONLY after the menuMode is Off and the bricks are -1.
            //Then we have to invoke the next level.
            if (this.bricksRemaining == 0 && this.menuMode) {

                this.menuMode = ih.isMenuModeOn();


            } else if (this.bricksRemaining == -1 && !this.menuMode) {

                //Here is almost an exact copy of what we do in initialize(). However, we will switch it with leveling functionality.
                //We can later remove this from initialize() and this way all the levels will be loaded here.
                Brick bricks[];
                bricks = new Brick[30];
                int bricksRemaining = 0;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 6; j++) {
                        bricks[bricksRemaining++] = new Brick(40 + j * 40 * 3, 48 + i * 12 * 3);
                    }
                }

                this.platform = new Platform(350, 550, 100, 10, 30);
                this.ball = new Ball(350, 550, 15, 30, 30, 5, 5, platform, bricks);
                this.bricks = bricks;
                this.bricksRemaining = bricksRemaining;

            } else if (this.bricksRemaining == 0) {


                this.ih.goBackToMenu();
                this.menuMode = ih.isMenuModeOn();
                this.bricksRemaining = -1;
                //this.stop();
            }

        }

        this.stop();
    }

    public synchronized void start() {

        this.isRunning = true;
        this.menuMode = true;
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