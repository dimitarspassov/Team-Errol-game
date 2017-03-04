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
    protected int hitCount;

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public void hitBrick(){
        this.decHitCount();
        switch(this.hitCount){
            case 2: setImage(new ImageIcon(this.getClass().getResource(PIC_YELLOW_BRICK)).getImage());break;
            case 1: setImage(new ImageIcon(this.getClass().getResource(PIC_GREEN_BRICK)).getImage());break;
            case 0: this.setDestroyed(true);break;
        }
     }
    public Brick(int x, int y) {
        super(x, y);
        this.setHitCount(3);
        setImage(new ImageIcon(
                this.getClass().getResource(PIC_BRICK)).getImage());
        this.setDestroyed(false);
    }
    public Brick(int x, int y,int hitCountIn) {
        super(x, y);
        this.setHitCount(hitCountIn);
        this.setBonus(null);
        this.setDestroyed(false);
        switch(hitCountIn){
            case 2: setImage(new ImageIcon(this.getClass().getResource(PIC_YELLOW_BRICK)).getImage());break;
            case 1: setImage(new ImageIcon(this.getClass().getResource(PIC_GREEN_BRICK)).getImage());break;
            default: setImage(new ImageIcon(this.getClass().getResource(PIC_BRICK)).getImage());break;
        }
    }
    public void setImage(Image img) {
        image = img;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    public void decHitCount(){
        this.hitCount--;
    }
    public Image getImage() { return image; }
    public Bonus getBonus() {
        return this.bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public void addBonus(String BonusType) {
        this.setBonus(new Bonus(this.getX(),this.getY(),BonusType));
     }
}
