package units;

import utilities.StaticData;

import javax.swing.*;

public class Brick extends Sprite {


    private Bonus bonus;
    private boolean destroyed;

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    protected int hitCount;

    private void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public void hitBrick() {
        this.decHitCount();
        switch (this.hitCount) {
            case 2:
                setImage(new ImageIcon(this.getClass().getResource(StaticData.PIC_YELLOW_BRICK)).getImage());
                break;
            case 1:
                setImage(new ImageIcon(this.getClass().getResource(StaticData.PIC_GREEN_BRICK)).getImage());
                break;
            case 0:
                this.setDestroyed(true);
                break;
        }
    }

    public Brick(int x, int y) {
        super(x, y);
        this.setHitCount(3);
        setImage(new ImageIcon(
                this.getClass().getResource(StaticData.PIC_BRICK)).getImage());
        this.setDestroyed(false);
    }

    public Brick(int x, int y, int hitCountIn) {
        super(x, y);
        this.setHitCount(hitCountIn);
        this.setBonus(null);
        this.setDestroyed(false);
        switch (hitCountIn) {
            case 2:
                setImage(new ImageIcon(this.getClass().getResource(StaticData.PIC_YELLOW_BRICK)).getImage());
                break;
            case 1:
                setImage(new ImageIcon(this.getClass().getResource(StaticData.PIC_GREEN_BRICK)).getImage());
                break;
            default:
                setImage(new ImageIcon(this.getClass().getResource(StaticData.PIC_BRICK)).getImage());
                break;
        }
    }

    private void decHitCount() {
        this.hitCount--;
    }

    public Bonus getBonus() {
        return this.bonus;
    }

    private void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public void addBonus(String BonusType) {
        this.setBonus(new Bonus(this.getX(), this.getY(), BonusType));
    }
}
