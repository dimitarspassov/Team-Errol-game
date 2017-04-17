package units.bullets;

import game.Game;
import units.GameUnit;

/**
 * Created by Krasimir on 17.4.2017 г..
 */
public interface Bulletable extends GameUnit {
    void move(Game game);
}
