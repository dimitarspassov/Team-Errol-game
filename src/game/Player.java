package game;

import factories.UnitFactory;
import units.balls.Ball;
import units.balls.FireBall;
import units.balls.FrostBall;
import units.bricks.Brick;
import units.bricks.Stone;
import units.bullets.Ammo;
import units.bullets.Bullet;
import units.platform.Platform;
import utilities.ScoreCounter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Player {

    private static final int DEFAULT_LIVES = 3;

    private Platform platform;
    private List<Ball> balls;
    private List<Ammo> bullets;
    private int lives;
    private ScoreCounter scoreCounter;

    public Player(Platform platform, ScoreCounter scoreCounter) {
        this.platform = platform;
        this.balls = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.lives = DEFAULT_LIVES;
        this.scoreCounter = scoreCounter;
    }

    public Platform getPlatform() {
        return platform;
    }

    //todo:Unmodifiable?
    public List<Ball> getBalls() {
        return this.balls;
    }

    public List<Ammo> getBullets() {

        this.filterAmmo();
        return this.bullets;
    }

    private void filterAmmo() {

        List<Ammo> filteredAmmo = this.bullets.stream().filter(b -> !b.hasHitTarget() && !b.isOutOfMap()).collect(Collectors.toList());
        this.bullets = filteredAmmo;
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
        this.balls = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.balls.add(UnitFactory.makeDefaultBall("SimpleBall", platform, bricks, stones));
        this.balls.get(0).pressSpace(false);
        this.platform.reset();
        this.platform.canFire(false);
    }

    public void fireFromPlatform(Brick[] bricks, Stone[] stones) {
        if (platform.isCanFire()) {
            this.bullets.add(UnitFactory.makeBullet("1", platform, bricks, stones));
            this.bullets.add(UnitFactory.makeBullet("2", platform, bricks, stones));
        }
    }

    public void removeFallenBalls() {
        this.balls = balls.stream().filter(ball -> ball.getY() < 570).collect(Collectors.toList());
    }

    public ScoreCounter getScoreCounter() {
        return scoreCounter;
    }

    public void setFireBalls(Brick[] bricks, Stone[] stones) {

        List<Ball> newBalls = new ArrayList<>();
        for (Ball ball : this.balls) {
            newBalls.add(UnitFactory.makeBall(ball, "FireBall", platform, bricks, stones));

        }
//        this.balls.forEach(ball -> newBalls.add(new FireBall(ball.getX(), ball.getY(),
//                ball.getRadius(), ball.getWidth(), ball.getHeight(), ball.getSpeedX(), ball.getSpeedY(), platform, bricks, stones)));
//        this.balls.forEach(ball -> newBalls.add(UnitFactory.makeBall(ball, "FireBall", platform, bricks, stones)));
        this.balls = newBalls;
        this.balls.forEach(ball -> ball.pressSpace(true));
    }

    public void setFrostBalls(Brick[] bricks, Stone[] stones) {

        List<Ball> newBalls = new ArrayList<>();
        for (Ball ball : this.balls) {
            newBalls.add(UnitFactory.makeBall(ball, "FrostBall", platform, bricks, stones));
        }

//        this.balls.forEach(ball -> newBalls.add(new FrostBall(ball.getX(), ball.getY(),
//                ball.getRadius(), ball.getWidth(), ball.getHeight(), ball.getSpeedX(), ball.getSpeedY(), platform, bricks, stones)));
//        this.balls.forEach(ball -> newBalls.add(UnitFactory.makeBall(ball, "FrostBall", platform, bricks, stones)));
        this.balls = newBalls;
        this.balls.forEach(ball -> ball.pressSpace(true));
    }

    public void resetLives() {
        this.lives = DEFAULT_LIVES;
    }
}
