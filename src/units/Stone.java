package units;

import javax.swing.*;

/**
 * Created by Krasimir on 14.2.2017 г..
 */
public class Stone extends Brick {
    public Stone(int x, int y) {
        super(x, y);
        setImage(new ImageIcon(
                this.getClass().getResource("/stone.png")).getImage());
        destroyed = false;
    }
}
