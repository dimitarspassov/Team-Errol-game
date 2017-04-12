package units.bricks;

import units.bonuses.Bonus;
import units.bonuses.BonusImpl;
import utilities.StaticData;

import javax.swing.*;

public class RectangleBrick extends AbstractBrick implements BonusHolder {

    private Bonus bonus;
    private boolean destroyed;


    public RectangleBrick(int x, int y) {
        super(x, y);
        super.setImage(new ImageIcon(
                this.getClass().getResource(StaticData.PIC_BRICK)).getImage());
    }

    public RectangleBrick(int x, int y, int hitCountIn) {
        super(x, y, hitCountIn);
        switch (hitCountIn) {
            case 2:
                super.setImage(new ImageIcon(this.getClass().getResource(StaticData.PIC_YELLOW_BRICK)).getImage());
                break;
            case 1:
                super.setImage(new ImageIcon(this.getClass().getResource(StaticData.PIC_GREEN_BRICK)).getImage());
                break;
            default:
                super.setImage(new ImageIcon(this.getClass().getResource(StaticData.PIC_BRICK)).getImage());
                break;
        }
    }

    public void hitBrick() {
        super.decreaseHitCount();
        switch (this.getHitCount()) {
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

    @Override
    public Bonus getBonus() {
        return this.bonus;
    }


    @Override
    public void addBonus(String BonusType) {
        this.setBonus(new BonusImpl(this.getX(), this.getY(), BonusType));
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    private void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    private void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

}
