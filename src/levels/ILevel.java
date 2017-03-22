package levels;


import units.Brick;
import units.Stone;

public interface ILevel {


    Brick[] generateBricks();

    Stone[] generateStones();

    void setBonuses(Brick[]bricks);


}
