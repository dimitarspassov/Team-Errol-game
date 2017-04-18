package units.balls;



import units.Moveable;

public interface Ball extends  Moveable {

    int getRadius();

    void pressSpace(boolean command);

    void sizeUp();

    void speedUp();

    int getSpeedX();

    int getSpeedY();
}
