package game;

import display.Display;
import graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;

//By far the most complex component of our project. This is the game itself.

public class Game implements Runnable {

    private String name;
    private int width, height;

    private Display display;
    private BufferStrategy bs;
    private Graphics graphics;


    private Thread thread;
    private boolean isRunning;

    public Game(String name, int width, int height) {

        this.name = name;
        this.width = width;
        this.height = height;

    }

    public void initialization() {

        this.display = new Display(name, width, height);

    }

    public void thick() {

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

        //Here we draw the background on the canvas.
        this.graphics.drawImage(ImageLoader.loadImage("/backgroundPic.png"), 0, 0, 800, 600, null);

        //This is the place for rendering graphics.
        //TODO: Write classes for ball, bricks, platform, etc.

        //Take a carefull look at these two operations. This is the cornerstone of visualizing our graphics.
        // Whatever we draw, it finally goes through dispose and the it is shown.
        this.graphics.dispose();
        this.bs.show();
    }

    @Override
    public void run() {

        // Here we initialize the game loop.
        this.initialization();

        while (isRunning) {

            thick();
            render();
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
