package units;

import game.Game;

import java.awt.*;

public class Ball extends Sprite {
    private int radius;

    private Platform platform;
    Brick[] bricks;
    Stone[] stones;

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

    public void setRadius(int radius) {
        this.radius = radius;
    }


    public void move(Game game) {
        int ballMinX = radius;
        int ballMinY = radius;
        int ballMaxX = 800 - radius;
        int ballMaxY = 600 - radius;


// If Space is pressed-ball moves,
// otherwise stands still on platform
        if (isSpacePressed) {
            this.setX(this.getX() + this.getDx());
            this.setY(this.getY() + this.getDy());

            if (new Rectangle(this.getX(),  this.getY(), this.getWidth(), getHeight())
                    .intersects(new Rectangle(platform.getPlatformX(), platform.getPlatformY(), platform.getPlatformWidth(), platform.getPlatformHeight()))) {
                if (Game.State == Game.STATE.GAME) {
                    Game.playSound(this, "/sounds/ping_platform.wav");
                }
                this.setDy(-this.getDy());

                int segment = platform.getPlatformWidth() / 5;
                int first = platform.getPlatformX() + segment;
                int second = platform.getPlatformX() + segment * 2;
                int third = platform.getPlatformX() + segment * 3;
                int fourth = platform.getPlatformX() + segment * 4;
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
                this.setY(platform.getPlatformY() - this.getHeight());
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
                if (Game.State == Game.STATE.GAME) {
                    Game.playSound(this, "/sounds/ping_wall.wav");
                }

            } else if (this.getX() + this.getDx() > ballMaxX - this.getDx() - radius) {

                this.setDx(-this.getDx());
                if (Game.State == Game.STATE.GAME) {
                    Game.playSound(this, "/sounds/ping_wall.wav");
                }
            }
            if (this.getY() <= 0) {
                this.setDy(-this.getDy());
                {
                    Game.playSound(this, "/sounds/ping_wall.wav");
                }
            } else if (this.getY() > ballMaxY) {
                this.setDy(-this.getDy());
                this.setY(ballMaxY);
                if (Game.State == Game.STATE.GAME) {
                    Game.playSound(this, "/sounds/ping_wall.wav");
                }
            }
        } else {
            this.setX(platform.getPlatformX() + 45);
            this.setY(platform.getPlatformY() - 20);
        }
    }

    private void hitBrick(Brick brick, Game game) {


        if (brick.getRect().intersects(new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight()))) {

            if (Game.State == Game.STATE.GAME) {
                Game.playSound(this, "/sounds/ping_brick.wav");
            }
            int top =  this.getY();
            int bottom =(this.getY() + this.getHeight());
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

    public void render(Graphics g) {
        g.drawOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
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
