package units;

import java.awt.*;

//TODO: Create the ball class

public class Ball {
    private float centerX;
    private float centerY;
    private int w;
    private int h;
    private int radius;

    private int speedX;
    private int speedY;
    private Platform platform;
    Brick[] bricks;

    public static boolean isSpacePressed;

    public Ball(int centerX, int centerY, int radius, int w, int h, int speedX, int speedY, Platform platform, Brick[] bricks) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.w = w;
        this.h = h;
        this.speedX = speedX;
        this.speedY = speedY;
        this.platform = platform;
        this.bricks = bricks;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void move() {
        float ballMinX = radius;
        float ballMinY = radius;
        float ballMaxX = 800 - radius;
        float ballMaxY = 600 - radius;


// If Space is pressed-ball moves,
// otherwise stands still on platform
        if(isSpacePressed) {
            centerX += speedX;
            centerY += speedY;

            if (new Rectangle((int) getCenterX(), (int) getCenterY(), getW(), getH())
                    .intersects(new Rectangle(platform.getPlatformX(), platform.getPlatformY(), platform.getPlatformWidth(), platform.getPlatformHeight()))) {

                speedY = -speedY;
            }
            // This to be in main loop
            // Draw the bricks
            if (bricks != null) {
                for (Brick brick : bricks) {
                    // If brick is destroyed, continue to next brick.
                    if (brick.destroyed) continue;
                    if (brick.getRect().intersects(new Rectangle((int) getCenterX(), (int) getCenterY(), getW(), getH()))) {
                        speedY = -speedY;
                        brick.destroyed = true;
                    }
                }
            }

            if (centerX <= 0) {

                speedX = -speedX;
                centerX = ballMinX - 1;

            } else if (centerX + speedX > ballMaxX - speedX - radius) {

                speedX = -speedX;
            }
            if (centerY <= 0) {
                speedY = -speedY;
            } else if (centerY > ballMaxY) {
                speedY = -speedY;
                centerY = ballMaxY;
            }
        }
        else {
            centerX=platform.getPlatformX()+35;
            centerY=platform.getPlatformY()-30;
        }
    }

    public void render(Graphics g) {
        g.drawOval((int) this.centerX, (int) this.centerY, this.w, this.h);
    }
}
