package game;


import units.Movable;
import units.balls.Ball;
import units.balls.SimpleBall;
import units.bricks.Brick;
import units.bricks.Stone;
import units.bullets.Bullet;
import units.platform.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {

    private static final int DEFAULT_LIVES = 3;

    private Platform platform;
    private List<Ball> balls;
    private List<Movable> bullets;
    //todo:Try to add the scores here
    //    private static int lastResult;
//    private static long lastBonusPoints;
//    private int score;
//    private int levelScore;
    private int lives;

    public Player(Platform platform) {
        this.platform = platform;
        this.balls = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.lives = DEFAULT_LIVES;
    }


    public Platform getPlatform() {
        return platform;
    }

    //todo:Unmodifiable?
    public List<Ball> getBalls() {
        return this.balls;
    }

    public List<Movable> getBullets() {

        return this.bullets;
    }

    public int getLives() {
        return lives;
    }

    public void decreaseLives() {
        this.lives--;
    }

    public void increaseLives() {
        this.lives++;
    }

    public void init(Brick[] bricks, Stone[] stones) {
        this.balls.add(new SimpleBall(350, 550, 10, 20, 20, 5, 5, platform, bricks, stones));
        this.balls.get(0).pressSpace(false);
    }

    public void fireFromPlatform(Brick[] bricks, Stone[] stones) {
        if (platform.isCanFire()) {
            Bullet bullet1 = new Bullet(platform.getX() + 2 * platform.getWidth() / 10, platform.getY() - 20, 10, 20, platform, bricks, stones);
            Bullet bullet2 = new Bullet(platform.getX() + 7 * platform.getWidth() / 10, platform.getY() - 20, 10, 20, platform, bricks, stones);
            this.bullets.add(bullet1);
            this.bullets.add(bullet2);
        }
    }

    public void removeFallenBalls() {
        this.balls = balls.stream().filter(ball -> ball.getY() < 570).collect(Collectors.toList());
    }
}
