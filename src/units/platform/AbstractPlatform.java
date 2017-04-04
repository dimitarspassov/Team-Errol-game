package units.platform;


public abstract class AbstractPlatform implements Platform {

    private int platformX;
    private int platformY;
    private int platformWidth;
    private int platformHeight;
    private int velocity;

    private boolean movingLeft;
    private boolean movingRight;

    public AbstractPlatform(int platformX, int platformY, int platformWidth, int platformHeight, int velocity) {
        this.setPlatformX(platformX);
        this.setPlatformY(platformY);
        this.setPlatformWidth(platformWidth);
        this.setPlatformHeight(platformHeight);
        this.setVelocity(velocity);
    }

    public int getVelocity() {
        return velocity;
    }

    public int getX() {
        return this.platformX;
    }

    protected void setPlatformX(int platformX) {
        this.platformX = platformX;
    }

    public int getY() {
        return this.platformY;
    }

    protected void setPlatformY(int platformY) {
        this.platformY = platformY;
    }

    public int getWidth() {
        return this.platformWidth;
    }

    protected void setPlatformWidth(int platformWidth) {
        this.platformWidth = platformWidth;
    }

    public int getHeight() {
        return this.platformHeight;
    }

    protected void setPlatformHeight(int platformHeight) {
        this.platformHeight = platformHeight;
    }

    protected void setVelocity(int velocity) {
        this.velocity = velocity;
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
}
