package units.platform;


public interface Shooter {

    void canFire(boolean b);

    boolean isCanFire();

    void reset();

    void fire();

    int getRemainingBullets();
}
