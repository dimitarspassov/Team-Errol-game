package units.platform;


import units.GameUnit;

import java.awt.*;

public interface Platform extends GameUnit {

    void thick();

    void render(Graphics g);

    void speedUp();

    void sizeUp();

    void sizeDown();

    boolean isMovingLeft();

    boolean isMovingRight();

    void moveLeft(boolean command);

    void moveRight(boolean command);
}