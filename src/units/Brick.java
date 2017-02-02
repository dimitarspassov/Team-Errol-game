package units;

import java.awt.*;

//This is the Brick class here you can get/set bricks and other features
public class Brick {
    //initializing variables
    private int BrickX;
    private int BrickY;
    private int BrickWidth;
    private int BrickHeight;

    //Getter Setters parameteres

    public int getBrickX() {
        return this.BrickX;
    }

    public void setBrickX(int BrickX) {
        this.BrickX = BrickX;
    }

    public int getBrickY() {
        return this.BrickY;
    }

    public void setBrickY(int BrickY) {
        this.BrickY = BrickY;
    }

    public int getBrickWidth() {
        return this.BrickWidth;
    }

    public void setBrickWidth(int BrickWidth) {
        this.BrickWidth = BrickWidth;
    }

    public int getBrickHeight() {
        return this.BrickHeight;
    }

    public void setBrickHeight(int BrickHeight) {
        this.BrickHeight = BrickHeight;
    }

    public Brick(int BrickX, int BrickY, int BrickWidth, int BrickHeight) {
        this.BrickX = BrickX;
        this.BrickY = BrickY;
        this.BrickWidth = BrickWidth;
        this.BrickHeight = BrickHeight;
    }

    public void render(Graphics g){

            g.drawRect(this.BrickX,this.BrickY,this.BrickWidth,this.BrickHeight);


    }


}
