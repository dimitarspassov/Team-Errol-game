package factories;

import units.balls.Ball;
import units.balls.FireBall;
import units.balls.FrostBall;
import units.balls.SimpleBall;
import units.bonuses.Bonus;
import units.bricks.Brick;
import units.bricks.RectangleBrick;
import units.bricks.Stone;
import units.bullets.Bullet;
import units.platform.Platform;
import units.platform.SimplePlatform;

import java.util.ArrayList;


/**
 * Created by mila on 20/04/2017.
 */
public class UnitFactory {

    private static final int PLATFORM_DEFAULT_X = 350;
    private static final int PLATFORM_DEFAULT_Y = 550;
    private static final int PLATFORM_DEFAULT_WIDTH = 100;
    private static final int PLATFORM_DEFAULT_HEIGHT = 20;
    private static final int PLATFORM_DEFAULT_VELOCITY = 12;

    private static final int BALL_DEFAULT_X = 350;
    private static final int BALL_DEFAULT_Y = 550;
    private static final int BALL_DEFAULT_RADIUS = 10;
    private static final int BALL_DEFAULT_WIDTH = 20;
    private static final int BALL_DEFAULT_HEIGHT = 20;
    private static final int BALL_DEFAULT_SPEED_X = 5;
    private static final int BALL_DEFAULT_SPEED_Y = 5;

    //used in game class
    public static SimplePlatform makeDefaultPlatform() {
        return new SimplePlatform(PLATFORM_DEFAULT_X, PLATFORM_DEFAULT_Y, PLATFORM_DEFAULT_WIDTH, PLATFORM_DEFAULT_HEIGHT, PLATFORM_DEFAULT_VELOCITY);
    }

    //used in player class
    public static Ball makeDefaultBall(String type, Platform platform, Brick[] bricks, Stone[] stones) {
        Ball ball;
        switch (type) {
            case "FireBall":
                ball = new FireBall(BALL_DEFAULT_X, BALL_DEFAULT_Y, BALL_DEFAULT_RADIUS, BALL_DEFAULT_WIDTH, BALL_DEFAULT_HEIGHT, BALL_DEFAULT_SPEED_X, BALL_DEFAULT_SPEED_Y, platform, bricks, stones);
                break;
            case "FrostBall":
                ball = new FrostBall(BALL_DEFAULT_X, BALL_DEFAULT_Y, BALL_DEFAULT_RADIUS, BALL_DEFAULT_WIDTH, BALL_DEFAULT_HEIGHT, BALL_DEFAULT_SPEED_X, BALL_DEFAULT_SPEED_Y, platform, bricks, stones);
                break;
            default:
                ball = new SimpleBall(BALL_DEFAULT_X, BALL_DEFAULT_Y, BALL_DEFAULT_RADIUS, BALL_DEFAULT_WIDTH, BALL_DEFAULT_HEIGHT, BALL_DEFAULT_SPEED_X, BALL_DEFAULT_SPEED_Y, platform, bricks, stones);
                break;
        }
        return ball;
    }

    //used in player class
    public static Ball makeBall(Ball ball, String type, Platform platform, Brick[] bricks, Stone[] stones) {

        switch (type) {
            case "FireBall":
                ball = new FireBall(ball.getX(), ball.getY(),
                        ball.getRadius(), ball.getWidth(), ball.getHeight(), ball.getSpeedX(), ball.getSpeedY(), platform, bricks, stones);
                break;
            case "FrostBall":
                ball = new FrostBall(ball.getX(), ball.getY(),
                        ball.getRadius(), ball.getWidth(), ball.getHeight(), ball.getSpeedX(), ball.getSpeedY(), platform, bricks, stones);
                break;
        }
        return ball;

    }

    //used in player class
    public static Bullet makeBullet(String type, Platform platform, Brick[] bricks, Stone[] stones) {
        Bullet bullet = null;

        switch (type) {
            case "1":
                bullet = new Bullet(platform.getX() + 2 * platform.getWidth() / 10, platform.getY() - 20, 10, 20, platform, bricks, stones);
                break;
            case "2":
                bullet = new Bullet(platform.getX() + 7 * platform.getWidth() / 10, platform.getY() - 20, 10, 20, platform, bricks, stones);
                break;
        }
        return bullet;
    }

    //used in UnitLoader class
    public static Ball makeTwoMoreBalls(Ball ball, String type, Platform platform, Brick[] bricks, Stone[] stones) {

        switch (type) {
            case "BallOne":
                ball = new SimpleBall(ball.getX() + 15, ball.getY() - 15, ball.getRadius(), ball.getWidth(), ball.getHeight(),
                        ball.getSpeedX(), ball.getSpeedY() * -1, platform, bricks, stones);
                break;
            case "BallTwo":
                ball = new SimpleBall(ball.getX() - 15, ball.getY() + 15, ball.getRadius(), ball.getWidth(), ball.getHeight(),
                        ball.getSpeedX() * -2, ball.getSpeedY(), platform, bricks, stones);
                break;

        }
        return ball;
    }

}

