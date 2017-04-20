package units.bricks;

import utilities.StaticData;

import javax.swing.*;

public class Stone extends AbstractBrick {

    public Stone(int x, int y) {
        super(x, y);
        super.setImage(new ImageIcon(
                this.getClass().getResource(StaticData.PIC_STONE_BRICK)).getImage());
    }

    @Override
    public boolean hit() {
        return false;
    }

}