package units.bullets;

import units.Movable;


public interface Ammo extends Movable {

    boolean isOutOfMap();
    boolean hasHitTarget();

}
