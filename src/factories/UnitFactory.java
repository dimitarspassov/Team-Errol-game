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

    private static final int PLATFORM_DEFAULT_X = 350;
    private static final int PLATFORM_DEFAULT_Y = 550;
    private static final int PLATFORM_DEFAULT_WIDTH = 100;
    private static final int PLATFORM_DEFAULT_HEIGHT = 20;
    private static final int PLATFORM_DEFAULT_VELOCITY = 12;

    //used in game class
    public static SimplePlatform makeDefaultPlatform() {
        return new SimplePlatform(PLATFORM_DEFAULT_X, PLATFORM_DEFAULT_Y, PLATFORM_DEFAULT_WIDTH, PLATFORM_DEFAULT_HEIGHT, PLATFORM_DEFAULT_VELOCITY);
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
