package units.balls;


import enumerations.State;
import game.Game;
import units.bricks.Brick;
import units.bricks.Stone;
import units.platform.Platform;
import utilities.StaticData;

import java.awt.*;

public abstract class AbstractBall implements Ball {

    private int radius;
    private int x;
    private int y;
    private int speedX;
    private int speedY;
    private boolean spacePressed;
    private int width;
    private int height;
    private Image image;
    private Platform platform;
    private Brick[] bricks;
    private Stone[] stones;

    protected AbstractBall(int x, int y, int radius, int width, int height, int speedX, int speedY, Platform platform, Brick[] bricks, Stone[] stones, Image image) {
        this.setX(x);
        this.setY(y);
        this.setRadius(radius);
        this.setWidth(width);
        this.setHeight(height);
        this.setSpeedX(speedX);
        this.setSpeedY(speedY);
        this.platform = platform;
        this.bricks = bricks;
        this.stones = stones;
        this.image = image;
    }

    @Override
    public void pressSpace(boolean command) {
        this.spacePressed = command;
    }


    public Image getImage() {
        return image;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getRadius() {
        return this.radius;
    }

    public int getSpeedX() {
        return this.speedX;
    }

    public int getSpeedY() {
        return this.speedY;
    }


    public Brick[] getBricks() {
        return bricks;
    }

    public Stone[] getStones() {
        return stones;
    }

    public void move(Game game) {
        int ballMinX = this.getRadius();
        int ballMaxX = 800 - this.getRadius();
        int ballMaxY = 600 - this.getRadius();
        this.detectCollisionWithPlatform(game);
        this.detectBrickCollision(game);
        this.detectWallCollisions(game, ballMinX, ballMaxX, ballMaxY);
    }

    protected Platform getPlatform() {
        return platform;
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    protected void setHeight(int height) {
        this.height = height;
    }

    protected void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    protected void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    protected void setRadius(int radius) {
        this.radius = radius;
    }

    protected void detectCollisionWithPlatform(Game game) {

        if (spacePressed) {

            this.setX(this.getX() + this.getSpeedX());
            this.setY(this.getY() + this.getSpeedY());
            
            if (new Rectangle(this.getX(), this.getY(), this.getWidth(), getHeight())
                    .intersects(new Rectangle(
                            this.getPlatform().getX(),
                            this.getPlatform().getY(),
                            this.getPlatform().getWidth(),
                            this.getPlatform().getHeight()))) {
                if (game.getGameState() == State.GAME) {
                    game.playSound(StaticData.SOUND_PLATFORM);
                }
                this.setSpeedY(-this.getSpeedY());

                int segment = this.getPlatform().getWidth() / 5;
                int first = this.getPlatform().getX() + segment;
                int second = this.getPlatform().getX() + segment * 2;
                int third = this.getPlatform().getX() + segment * 3;
                int fourth = this.getPlatform().getX() + segment * 4;
                int center = (this.getX() + this.getWidth() / 2);

                if (center < first || (center >= first && center < second)) {
                    this.setSpeedX(-5);
                } else if (center >= second && center < third) {
                    if (this.getSpeedX() > 0) {
                        this.setSpeedX(1);
                    } else {
                        this.setSpeedX(-1);
                    }
                } else if (center > fourth || (center >= third && center < fourth)) {
                    this.setSpeedX(5);
                }
                // Reset ballsAndBullets's position out of collision.
                this.setY(this.getPlatform().getY() - this.getHeight());
            }
        }

    }

    protected void detectBrickCollision(Game game) {
        // Draw the bricks
        if (this.getBricks() != null) {
            for (Brick brick : this.getBricks()) {
                if (brick.isDestroyed()) continue;
                hitBrick(brick, game);
            }
        }
        // Draw the stones
        if (this.getStones() != null) {
            for (Stone stone : this.getStones()) {
                if (!stone.isDestroyed()) {
                    hitBrick(stone, game);
                }
            }
        }
    }

    protected void detectWallCollisions(Game game, int ballMinX, int ballMaxX, int ballMaxY) {

        if (this.getX() <= 0) {

            this.setSpeedX(-this.getSpeedX());
            this.setX(ballMinX - 1);
            if (game.getGameState() == State.GAME) {
                game.playSound(StaticData.SOUND_WALL);
            }

        } else if (this.getX() + this.getSpeedX() > ballMaxX - this.getSpeedX() - this.getRadius()) {

            this.setSpeedX(-this.getSpeedX());
            if (game.getGameState() == State.GAME) {
                game.playSound(StaticData.SOUND_WALL);
            }
        }
        if (this.getY() <= 0) {
            this.setSpeedY(-this.getSpeedY());
            {
                game.playSound(StaticData.SOUND_WALL);
            }
        } else if (this.getY() > ballMaxY) {
            this.setSpeedY(-this.getSpeedY());
            this.setY(ballMaxY);
            if (game.getGameState() == State.GAME) {
                game.playSound(StaticData.SOUND_WALL);
            }
        } else {
            if (!spacePressed) {
                this.setX(this.getPlatform().getX() + 45);
                this.setY(this.getPlatform().getY() - 20);
            }
        }

    }

    protected void richocet(Brick brick) {
        int top = this.getY();
        int bottom = (this.getY() + this.getHeight());
        int left = this.getX();
        int right = (this.getX() + this.getWidth());


        int oldDy = this.getSpeedY();
        if (brick.getRect().contains(left, top - 1)) {
            int dy = this.getSpeedY();
            this.setSpeedY(dy < 0 ? -dy : dy);
        } else if (brick.getRect().contains(left, bottom + 1)) {
            int dy = this.getSpeedY();
            this.setSpeedY(dy < 0 ? dy : -dy);
        }

        if (brick.getRect().contains(right + 1, top) && oldDy == this.getSpeedY()) {
            int dx = this.getSpeedX();
            this.setSpeedX(dx < 0 ? dx : -dx);
        } else if (brick.getRect().contains(left - 1, top) && oldDy == this.getSpeedY()) {
            int dx = this.getSpeedX();
            this.setSpeedX(dx < 0 ? -dx : dx);
        }

    }

    protected abstract void hitBrick(Brick brick, Game game);
}
