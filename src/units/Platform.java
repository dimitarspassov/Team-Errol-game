package units;


import game.Commons;
import graphics.ImageLoader;

import java.awt.*;

public class Platform extends Sprite implements Commons {
    private int velocity;
    public static boolean isMovingLeft;
    public static boolean isMovingRight;


    public Platform(int platformX, int platformY, int platformWidth, int platformHeight, int velocity) {
        this.setX(platformX);
        this.setY(platformY);
        this.setWidth(platformWidth);
        this.setHeight(platformHeight);
        this.setVelocity(velocity);
     }

    private void setVelocity(int velocity) {
        this.velocity = velocity;
    }




    public void thick() {
        int xMax=795-this.getWidth();
        if (isMovingRight) {

            if (this.getX() >= xMax) {
                this.setX(xMax);
            } else {
                this.setX(this.getX()+this.velocity);
            }
        } else if (isMovingLeft) {

            if (this.getX() <= 0) {
                this.setX(0);
            } else {
               this.setX(this.getX()-this.velocity);
            }
        }
    }

    public void render(Graphics g) {
        if (isMovingRight) {
            g.drawImage(ImageLoader.loadImage(PIC_LATEST_PLATFORM),
                    this.getX(),
                    this.getY(),
                    this.getWidth(),
                    this.getHeight(), null);
        } else if (isMovingLeft) {
            g.drawImage(ImageLoader.loadImage(PIC_LATEST_PLATFORM),
                    this.getX(),
                    this.getY(),
                    this.getWidth(),
                    this.getHeight(), null);
        }
    }

    public void speedUp() {
        this.setVelocity(20);
    }

    public void sizeUp(){
        this.setWidth(200);
    }

    public void sizeDown(){
        this.setWidth(70);
    }

}