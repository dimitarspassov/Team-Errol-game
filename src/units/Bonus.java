package units;

import javax.swing.*;

/**
 * Created by Krasimir on 1.3.2017 г..
 */
public class Bonus extends Brick{
    public Bonus(int x, int y) {
        super(x, y);
        setImage(new ImageIcon(
                this.getClass().getResource("/bonus_ball_up.png")).getImage());
    }
}
