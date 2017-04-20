package units.balls;


import units.Movable;

public interface Ball extends Movable {

    int getRadius();

    void pressSpace(boolean command);

    void sizeUp();

    void speedUp();

    int getSpeedX();

    int getSpeedY();
}
