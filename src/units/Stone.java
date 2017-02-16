package units;

import javax.swing.*;

public class Stone extends Brick {
    public Stone(int x, int y) {
        super(x, y);
        setImage(new ImageIcon(
                this.getClass().getResource("/stone.png")).getImage());
        destroyed = false;
    }
}
