package units;

import game.Game;
import game.State;
import gameState.StaticData;

import java.awt.*;

public class Ball extends Sprite {
    private int radius;

    private Platform platform;
    private Brick[] bricks;
    private Stone[] stones;

    public static boolean isSpacePressed;

    public Ball(int centerX, int centerY, int radius, int w, int h, int speedX, int speedY, Platform platform, Brick[] bricks, Stone[] stones) {
        this.setX(centerX);
        this.setY(centerY);
        this.radius = radius;
        this.setWidth(w);
        this.setHeight(h);
        this.setDx(speedX);
        this.setDy(speedY);
        this.platform = platform;
        this.bricks = bricks;
        this.stones = stones;
    }

    public int getRadius() {
        return radius;
    }

    private void setRadius(int radius) {
        this.radius = radius;
    }


    public void move(Game game) {
        int ballMinX = radius;
        int ballMaxX = 800 - radius;
        int ballMaxY = 600 - radius;


// If Space is pressed-ball moves,
// otherwise stands still on platform
        if (isSpacePressed) {
            this.setX(this.getX() + this.getDx());
            this.setY(this.getY() + this.getDy());

            if (new Rectangle(this.getX(), this.getY(), this.getWidth(), getHeight())
                    .intersects(new Rectangle(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight()))) {
                if (game.getGameState() == State.GAME) {
                    game.playSound(StaticData.SOUND_PLATFORM);
                }
                this.setDy(-this.getDy());

                int segment = platform.getWidth() / 5;
                int first = platform.getX() + segment;
                int second = platform.getX() + segment * 2;
                int third = platform.getX() + segment * 3;
                int fourth = platform.getX() + segment * 4;
                int center = (this.getX() + this.getWidth() / 2);

                if (center < first || (center >= first && center < second)) {
                    this.setDx(-5);
                } else if (center >= second && center < third) {
                    if (this.getDx() > 0) {
                        this.setDx(1);
                    } else {
                        this.setDx(-1);
                    }
                } else if (center > fourth || (center >= third && center < fourth)) {
                    this.setDx(5);
                }
                // Reset ball's position out of collision.
                this.setY(platform.getY() - this.getHeight());
            }

            // Draw the bricks
            if (bricks != null) {
                for (Brick brick : bricks) {
                    if (brick.isDestroyed()) continue;
                    hitBrick(brick, game);
                }
            }
            // Draw the stones
            if (stones != null) {
                for (Stone stone : stones) {
                    if (stone != null) {
                        stone.incHitCount();
                        hitBrick(stone, game);
                    }
                }
            }


            if (this.getX() <= 0) {

                this.setDx(-this.getDx());
                this.setX(ballMinX - 1);
                if (game.getGameState() == State.GAME) {
                    game.playSound(StaticData.SOUND_WALL);
                }

            } else if (this.getX() + this.getDx() > ballMaxX - this.getDx() - radius) {

                this.setDx(-this.getDx());
                if (game.getGameState() == State.GAME) {
                    game.playSound(StaticData.SOUND_WALL);
                }
            }
            if (this.getY() <= 0) {
                this.setDy(-this.getDy());
                {
                    game.playSound(StaticData.SOUND_WALL);
                }
            } else if (this.getY() > ballMaxY) {
                this.setDy(-this.getDy());
                this.setY(ballMaxY);
                if (game.getGameState() == State.GAME) {
                    game.playSound(StaticData.SOUND_WALL);
                }
            }
        } else {
            this.setX(platform.getX() + 45);
            this.setY(platform.getY() - 20);
        }
    }

    private void hitBrick(Brick brick, Game game) {


        if (brick.getRect().intersects(new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight()))) {

            if (game.getGameState() == State.GAME) {
                game.playSound(StaticData.SOUND_BRICK);
            }
            int top = this.getY();
            int bottom = (this.getY() + this.getHeight());
            int left = this.getX();
            int right = (this.getX() + this.getWidth());


            int oldDy = this.getDy();
            if (brick.getRect().contains(left, top - 1)) {
                int dy = this.getDy();
                this.setDy(dy < 0 ? -dy : dy);
            } else if (brick.getRect().contains(left, bottom + 1)) {
                int dy = this.getDy();
                this.setDy(dy < 0 ? dy : -dy);
            }

            if (brick.getRect().contains(right + 1, top) && oldDy == this.getDy()) {
                int dx = this.getDx();
                this.setDx(dx < 0 ? dx : -dx);
            } else if (brick.getRect().contains(left - 1, top) && oldDy == this.getDy()) {
                int dx = this.getDx();
                this.setDx(dx < 0 ? -dx : dx);
            }
            brick.hitBrick();
            if (brick.getBonus() != null && brick.isDestroyed()) {
                game.addBonus(brick.getBonus());

            }
        }
    }

    public void sizeUp() {
        this.setHeight(40);
        this.setWidth(40);
        this.setRadius(20);

    }

    public void speedUp() {
        this.setDx((int) (this.getDx() * 1.7));
        this.setDy((int) (this.getDy() * 1.7));
    }
}
