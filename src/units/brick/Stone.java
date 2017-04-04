package units.brick;

import utilities.StaticData;

import javax.swing.*;

public class Stone extends Brick {
    public Stone(int x, int y) {
        super(x, y);
        setImage(new ImageIcon(
                this.getClass().getResource(StaticData.PIC_STONE_BRICK)).getImage());
        super.setDestroyed(false);
    }
    public void incHitCount(){
        this.hitCount++;
    }
}
