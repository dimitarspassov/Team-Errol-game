package units.bonuses;

import enumerations.BonusType;
import units.GameUnit;

import java.awt.*;

public interface Bonus extends GameUnit {

    boolean getStatus();

    void setStatus(boolean status);

    BonusType getBonusType();

    void setY(int y);

    Rectangle getRect();
}
