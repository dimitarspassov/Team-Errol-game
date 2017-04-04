package levels;


import units.brick.Brick;
import units.brick.Stone;

public interface ILevel {


    Brick[] generateBricks();

    Stone[] generateStones();

    void setBonuses(Brick[]bricks);


}
