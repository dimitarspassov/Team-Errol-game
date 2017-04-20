package units.platform;


import java.awt.*;

public abstract class AbstractPlatform implements Platform {

    private int platformX;
    private int platformY;
    private int platformWidth;
    private int platformHeight;
    private int velocity;
    private Image image;
    private boolean movingLeft;
    private boolean movingRight;
    private boolean canFire;

    protected AbstractPlatform(int platformX, int platformY, int platformWidth, int platformHeight, int velocity, Image image) {
        this.setPlatformX(platformX);
        this.setPlatformY(platformY);
        this.setPlatformWidth(platformWidth);
        this.setPlatformHeight(platformHeight);
        this.setVelocity(velocity);
        this.setImage(image);
        //todo:Remove unnecessary setters!
    }


    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }

    @Override
    public boolean isCanFire() {
        return this.canFire;
    }

    public int getX() {
        return this.platformX;
    }

    public int getY() {
        return this.platformY;
    }

    public int getWidth() {
        return this.platformWidth;
    }

    public int getHeight() {
        return this.platformHeight;
    }

    public void speedUp() {
        this.setVelocity(20);
    }

    public void sizeUp() {
        this.setPlatformWidth(200);
    }

    public void sizeDown() {
        this.setPlatformWidth(70);
    }

    public boolean isMovingLeft() {
        return this.movingLeft;
    }

    public boolean isMovingRight() {
        return this.movingRight;
    }

    public void moveLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void moveRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    private void setImage(Image image) {
        this.image = image;
    }

    public int getVelocity() {
        return velocity;
    }

    protected void setPlatformX(int platformX) {
        this.platformX = platformX;
    }

    private void setPlatformHeight(int platformHeight) {
        this.platformHeight = platformHeight;
    }

    private void setPlatformY(int platformY) {
        this.platformY = platformY;
    }

    private void setPlatformWidth(int platformWidth) {
        this.platformWidth = platformWidth;
    }

    private void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public void reset() {
        this.setPlatformX(350);
        this.setPlatformY(550);
        this.setPlatformWidth(100);
        this.setPlatformHeight(20);
        this.setVelocity(12);
    }
}
