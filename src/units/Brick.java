package units;

import javax.swing.*;
import java.awt.*;

public class Brick {


    protected Image image;
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected Bonus bonus;
    public boolean destroyed;
    public int hitCount;
    public void hitBrick(){
        this.hitCount--;
        if(hitCount==2){
            setImage(new ImageIcon(
                    this.getClass().getResource("/brick_yellow.png")).getImage());
        }
        if(hitCount==1){
            setImage(new ImageIcon(
                    this.getClass().getResource("/brick_green.png")).getImage());
        }

        if(this.hitCount==0){
            this.destroyed = true;
        }
     }
    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
        width = height = 0;
        image = null;
        hitCount =3;
        setImage(new ImageIcon(
                this.getClass().getResource("/brick.png")).getImage());
        destroyed = false;
    }
    public Brick(int x, int y,int hitCountIn) {
        this.x = x;
        this.y = y;
        width = height = 0;
        image = null;
        this.hitCount =hitCountIn;
        this.bonus=null;
        if(hitCount==2){
            setImage(new ImageIcon(
                    this.getClass().getResource("/brick_yellow.png")).getImage());
        }else if(hitCount==1){
            setImage(new ImageIcon(
                    this.getClass().getResource("/brick_green.png")).getImage());
        }else
            setImage(new ImageIcon(
                    this.getClass().getResource("/brick.png")).getImage());
        destroyed = false;
    }
    public void setImage(Image img) {
        image = img;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    public Image getImage() { return image; }

    public int getWidth() {return width; }
    public int getHeight() { return height; }
    public int getX() { return x; }

    public void setY(int y) { this.y = y; }
    public int getY() { return y; }

    public Bonus getBonus() {
        return this.bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    public void addBonus(String BonusType) {
        this.bonus = new Bonus(this.getX(),this.getY(),BonusType);
     }
}
