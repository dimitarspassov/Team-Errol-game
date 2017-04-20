package units.bricks;


import enumerations.BonusType;
import units.GameUnit;
import units.bonuses.Bonus;

import java.awt.*;

public interface Brick extends GameUnit {

    boolean hit();

    boolean isDestroyed();

    Rectangle getRect();

    void destroy();

    Bonus getBonus();

    void addBonus(BonusType type);

}
