package units;


import graphics.ImageLoader;

import java.awt.*;

public class Platform {
    private int platformX;
    private int platformY;
    private int platformWidth;
    private int platformHeight;
    private int velocity;
    public static boolean isMovingLeft;
    public static boolean isMovingRight;

    public int getPlatformX() {
        return this.platformX;
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
        return this.platformWidth;
    }

    public void setPlatformWidth(int platformWidth) {
        this.platformWidth = platformWidth;
    }

    public int getPlatformHeight() {
        return this.platformHeight;
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
            this.platformX += this.velocity;
        }
    }

    public void thick(){
        if(isMovingRight){
           // this.velocity = 12;
            if (this.platformX >= 695) {
                this.platformX = 695;
            } else {
                this.platformX += this.velocity;
            }        }
        else if (isMovingLeft) {
         //   this.velocity = 12;
            if (this.platformX <= 0) {
                this.platformX = 0;
            } else {
                this.platformX -= this.velocity;
            }
        }
    }

    public void render(Graphics g){
        if(isMovingRight){
            g.drawImage(ImageLoader.loadImage("/platform.png"),
                    this.platformX,
                    this.platformY,
                    this.platformWidth,
                    this.platformHeight,null);
        }
        else if(isMovingLeft){
            g.drawImage(ImageLoader.loadImage("/platform.png"),
                    this.platformX,
                    this.platformY,
                    this.platformWidth,
                    this.platformHeight,null);
        }
    }
}