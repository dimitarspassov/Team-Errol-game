package units.bullets;


import units.bricks.Brick;
import units.bricks.Stone;
import units.platform.Platform;

import java.awt.*;

public abstract class AbstractBullet implements Ammo {

    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private Platform platform;
    private Brick[] bricks;
    private Stone[] stones;
    private boolean targetHit;

    protected AbstractBullet(int x, int y, int width, int height, Platform platform, Brick[] bricks, Stone[] stones, Image image) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.platform = platform;
        this.bricks = bricks;
        this.stones = stones;
        this.image = image;
    }

    public Image getImage() {
        return image;
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

    public Brick[] getBricks() {
        return this.bricks;
    }

    public Stone[] getStones() {
        return this.stones;
    }

    @Override
    public boolean hasHitTarget() {
        return this.targetHit;
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

    protected void hitTarget() {
        this.targetHit = true;
    }
}
