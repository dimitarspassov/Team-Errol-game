package units.bricks;

import utilities.StaticData;

import javax.swing.*;
import java.awt.*;

public class Stone extends AbstractBrick {

    private Image image;

    public Stone(int x, int y) {
        super(x, y);
        super.setImage(new ImageIcon(
                this.getClass().getResource(StaticData.PIC_STONE_BRICK)).getImage());
    }

    @Override
    public void hitBrick() {
            //todo:What happens when a stone is hit?
    }


}
