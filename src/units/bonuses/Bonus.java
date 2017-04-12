package units.bonuses;

import units.GameUnit;

import java.awt.*;

public interface Bonus extends GameUnit {

    boolean getStatus();

    void setStatus(boolean status);

    String getBonusType();

    void setY(int y);

    Rectangle getRect();
}
