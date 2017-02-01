package units;

import java.awt.*;

//TODO: Create the ball class
public class Ball {
    private int centerX;
    private int centerY;
    private int w;
    private int h;

    private int xMove;
    private int yMove;

    private int xSpeed;
    private int ySpeed;

    public Ball(int centerX, int centerY, int w, int h, int xMove, int yMove) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.w = w;
        this.h = h;
        this.xMove = xMove;
        this.yMove = yMove;
    }

    public int getyMove() {

        return yMove;
    }

    public void setyMove(int yMove) {
        this.yMove = yMove;
    }

    public int getxMove() {

        return xMove;
    }

    public void setxMove(int xMove) {
        this.xMove = xMove;
    }

    public int getH() {

        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {

        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getCenterY() {

        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getCenterX() {

        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void render(Graphics g){

        g.drawOval(this.centerX,this.centerY,this.h,this.w);

    }
}
