package factories;

import units.balls.Ball;
import units.balls.FireBall;
import units.balls.FrostBall;
import units.balls.SimpleBall;
import units.bricks.Brick;
import units.bricks.Stone;
import units.platform.Platform;
import units.platform.SimplePlatform;


/**
 * Created by mila on 20/04/2017.
 */
public class UnitFactory {
    //used in game class
    public static SimplePlatform makeDefaultPlatform() {
        return new SimplePlatform(350, 550, 100, 20, 12);
    }

    //used in player class
    public static Ball makeBall(String type, Platform platform, Brick[] bricks, Stone[] stones) {
        Ball ball = null;
        switch (type) {
            case "FireBall":
                ball = new FireBall(type, 350, 550, 10, 20, 20, 5, 5, platform, bricks, stones);
                break;
            case "FrostBall":
                ball = new FrostBall(type, 350, 550, 10, 20, 20, 5, 5, platform, bricks, stones);
                break;
            default:
                ball = new SimpleBall(type, 250, 550, 10, 20, 20, 5, 5, platform, bricks, stones);
                break;
        }
        return ball;
    }

}
