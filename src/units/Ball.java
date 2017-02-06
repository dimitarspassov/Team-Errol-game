package units;

import javafx.application.*;

import java.awt.*;

//TODO: Create the ball class

public class Ball {
    private float centerX = 300;
    private float centerY = 200;
    private int w = 50;
    private int h = 50;
    private int radius = 25;

    private int speedX = 5;
    private int speedY = 5;
    Platform platform;

    public Ball(int centerX, int centerY, int radius, int w, int h, int speedX, int speedY, Platform platform) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.w = w;
        this.h = h;
        this.speedX = speedX;
        this.speedY = speedY;
        this.platform = platform;
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
        float ballMinX = 0 + radius;
        float ballMinY = 0 + radius;
        float ballMaxX = 800 - radius;
        float ballMaxY = 600 - radius;

        centerX += speedX;
        centerY += speedY;

        if (new Rectangle((int)getCenterX(), (int)getCenterY(), getW(), getH())
                .intersects(new Rectangle(platform.getPlatformX(), platform.getPlatformY(), platform.getPlatformWidth(), platform.getPlatformHeight()))) {

            speedY = -speedY;
        }

        if (centerX < ballMinX) {
            speedX = -speedX;
            centerX = ballMinX;
        } else if (centerX > ballMaxX) {
            speedX = -speedX;
            centerX = ballMaxX;
        }
        if (centerY < ballMinY) {
            speedY = -speedY;
            centerY = ballMinY;
        } else if (centerY > ballMaxY) {
            speedY = -speedY;
            centerY = ballMaxY;
        }
    }

    public void render(Graphics g) {
        g.drawOval((int) this.centerX, (int) this.centerY, this.w, this.h);
    }
}
