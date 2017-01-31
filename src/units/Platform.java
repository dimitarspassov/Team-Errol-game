package units;

import java.awt.*;

//TODO: Create the platform class
public class Platform {
    private int platformX = 350;
    private int platformY = 550;
    private int platformWidth = 100;
    private int platformHeight = 10;
    private int velocity = 30;
    public static boolean isMovingLeft;
    public static boolean isMovingRight;

    public int getPlatformX() {
        return platformX;
    }

    public void setPlatformX(int platformX) {
        this.platformX = platformX;
    }

    public int getPlatformY() {
        return platformY;
    }

    public void setPlatformY(int platformY) {
        this.platformY = platformY;
    }

    public int getPlatformWidth() {
        return platformWidth;
    }

    public void setPlatformWidth(int platformWidth) {
        this.platformWidth = platformWidth;
    }

    public int getPlatformHeight() {
        return platformHeight;
    }

    public void setPlatformHeight(int platformHeight) {
        this.platformHeight = platformHeight;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public Platform(int platformX, int platformY, int platformWidth, int platformHeight, int velocity) {
        this.platformX = platformX;
        this.platformY = platformY;
        this.platformWidth = platformWidth;
        this.platformHeight = platformHeight;
        this.velocity = velocity;
    }

    public void moving(){
        if (isMovingLeft){
            this.platformX -= this.velocity;
        } else if (isMovingRight){
            this.platformY += this.velocity;
        }
    }
}
