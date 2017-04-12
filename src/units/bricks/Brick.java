package units.bricks;


import units.GameUnit;

import java.awt.*;

public interface Brick extends GameUnit {

    void hitBrick();

    boolean isDestroyed();

    Rectangle getRect();

}
