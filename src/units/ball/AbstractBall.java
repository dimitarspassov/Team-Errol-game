package units.ball;


import units.brick.Brick;
import units.platform.Platform;
import units.brick.Stone;

public abstract class AbstractBall implements Ball {

    private int radius;
    private int x;
    private int y;
    private int speedX;
    private int speedY;
    protected boolean spacePressed;
    private int width;
    private int height;

    private Platform platform;
    private Brick[] bricks;
    private Stone[] stones;

    protected AbstractBall(int x, int y, int radius, int width, int height, int speedX, int speedY, Platform platform, Brick[] bricks, Stone[] stones) {
        this.setX(x);
        this.setY(y);
        this.setRadius(radius);
        this.setWidth(width);
        this.setHeight(height);
        this.setSpeedX(speedX);
        this.setSpeedY(speedY);
        this.platform = platform;
        this.bricks = bricks;
        this.stones = stones;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getRadius() {
        return this.radius;
    }

    public int getSpeedX() {
        return this.speedX;
    }

    public int getSpeedY() {
        return this.speedY;
    }


    public boolean isSpacePressed() {
        return spacePressed;
    }

    public Brick[] getBricks() {
        return bricks;
    }

    public Stone[] getStones() {
        return stones;
    }

    protected Platform getPlatform() {
        return platform;
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    protected void setHeight(int height) {
        this.height = height;
    }

    protected void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    protected void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    protected void setRadius(int radius) {
        this.radius = radius;
    }


}
