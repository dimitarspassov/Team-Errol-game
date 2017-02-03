package units;

//TODO: Create the brick class

import javax.swing.*;

// The Brick class extends Sprite, and represents the breakable tiles.
public class Brick extends Sprite {


    /* The destroyed attribute indicates whether the current brick is destroyed.
     * The game loop uses this attribute to decide whether or not to draw it
     * to the screen and consider for collision checks.
     *
     * This method is set to public for now because it has only 2 possible
     * values: true and false, which I can directly control. And there is
     * no other functionality associated with changing this value
     * (like keeping values in limits, for example).
     */
    public boolean destroyed;

    public Brick(int x, int y) {
        super(x, y);
        setImage(new ImageIcon(
                this.getClass().getResource("/brick.png")).getImage());
        destroyed = false;
    }
}
