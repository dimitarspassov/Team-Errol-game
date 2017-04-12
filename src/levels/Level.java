package levels;


import units.bricks.Brick;
import units.bricks.Stone;

public interface Level {


    Brick[] generateBricks();

    Stone[] generateStones();

}
