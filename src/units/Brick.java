package units;

import game.Commons;

import javax.swing.*;
import java.awt.*;

public class Brick extends Sprite implements Commons{


    private Bonus bonus;
    private boolean destroyed;

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public int hitCount;
    public void hitBrick(){
        this.hitCount--;
        if(hitCount==2){
            setImage(new ImageIcon(
                    this.getClass().getResource(PIC_YELLOW_BRICK)).getImage());
        }
        if(hitCount==1){
            setImage(new ImageIcon(
                    this.getClass().getResource(PIC_GREEN_BRICK)).getImage());
        }

        if(this.hitCount==0){
            this.destroyed = true;
        }
     }
    public Brick(int x, int y) {
        super(x, y);
        hitCount =3;
        setImage(new ImageIcon(
                this.getClass().getResource(PIC_BRICK)).getImage());
        destroyed = false;
    }
    public Brick(int x, int y,int hitCountIn) {
        super(x, y);
        this.hitCount =hitCountIn;
        this.bonus=null;
        if(hitCount==2){
            setImage(new ImageIcon(
                    this.getClass().getResource(PIC_YELLOW_BRICK)).getImage());
        }else if(hitCount==1){
            setImage(new ImageIcon(
                    this.getClass().getResource(PIC_GREEN_BRICK)).getImage());
        }else
            setImage(new ImageIcon(
                    this.getClass().getResource(PIC_BRICK)).getImage());
        this.destroyed = false;
    }
    public void setImage(Image img) {
        image = img;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    public Image getImage() { return image; }
    public Bonus getBonus() {
        return this.bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public void addBonus(String BonusType) {
        this.bonus = new Bonus(this.getX(),this.getY(),BonusType);
     }
}
