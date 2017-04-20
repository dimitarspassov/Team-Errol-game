package units.bricks;

import utilities.StaticData;

import javax.swing.*;

public class RectangleBrick extends AbstractBrick {


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

    public boolean hit() {
        super.decreaseHitCount();
        switch (this.getHitCount()) {
            case 2:
                setImage(new ImageIcon(this.getClass().getResource(StaticData.PIC_YELLOW_BRICK)).getImage());
                return true;
            case 1:
                setImage(new ImageIcon(this.getClass().getResource(StaticData.PIC_GREEN_BRICK)).getImage());
                return true;
            case 0:
                this.destroy();
                return true;
        }
        return false;
    }
}
