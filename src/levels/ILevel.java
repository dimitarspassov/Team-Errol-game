package levels;


import units.bricks.Brick;
import units.bricks.Stone;

public interface ILevel {


    Brick[] generateBricks();

    Stone[] generateStones();

    void setBonuses(Brick[]bricks);


}
