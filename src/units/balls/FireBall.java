package units.balls;

import enumerations.State;
import game.Game;
import graphics.ImageLoader;
import units.bricks.Brick;
import units.bricks.Stone;
import units.platform.Platform;
import utilities.StaticData;

import java.awt.*;

/**
 * Created by Димитър on 19/04/2017.
 */
public class FireBall extends AbstractBall implements Ball {
    private static final Image image = ImageLoader.loadImage(StaticData.PIC_FIREBALL);

    public FireBall(int centerX, int centerY, int radius, int w, int h, int speedX, int speedY,
                      Platform platform, Brick[] bricks, Stone[] stones) {
        super(centerX, centerY, radius, w, h, speedX, speedY, platform, bricks, stones, image);
    }

    public void move(Game game) {
        int ballMinX = super.getRadius();
        int ballMaxX = 800 - super.getRadius();
        int ballMaxY = 600 - super.getRadius();


        // If Space is pressed-ballsAndBullets moves,
        // otherwise stands still on platform
        if (super.isSpacePressed()) {
            this.setX(this.getX() + this.getSpeedX());
            this.setY(this.getY() + this.getSpeedY());

            if (new Rectangle(this.getX(), this.getY(), this.getWidth(), getHeight())
                    .intersects(new Rectangle(
                            super.getPlatform().getX(),
                            super.getPlatform().getY(),
                            super.getPlatform().getWidth(),
                            super.getPlatform().getHeight()))) {
                if (game.getGameState() == State.GAME) {
                    game.playSound(StaticData.SOUND_PLATFORM);
                }
                this.setSpeedY(-this.getSpeedY());

                int segment = super.getPlatform().getWidth() / 5;
                int first = super.getPlatform().getX() + segment;
                int second = super.getPlatform().getX() + segment * 2;
                int third = super.getPlatform().getX() + segment * 3;
                int fourth = super.getPlatform().getX() + segment * 4;
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
                this.setY(super.getPlatform().getY() - this.getHeight());
            }

            // Draw the bricks
            if (super.getBricks() != null) {
                for (Brick brick : super.getBricks()) {
                    if (brick.isDestroyed()) continue;
                    hitBrick(brick, game);
                }
            }
            // Draw the stones
            if (super.getStones() != null) {
                for (Stone stone : super.getStones()) {
                    if (stone != null) {
                        stone.hitBrick();
                        hitBrick(stone, game);
                    }
                }
            }


            if (this.getX() <= 0) {

                this.setSpeedX(-this.getSpeedX());
                this.setX(ballMinX - 1);
                if (game.getGameState() == State.GAME) {
                    game.playSound(StaticData.SOUND_WALL);
                }

            } else if (this.getX() + this.getSpeedX() > ballMaxX - this.getSpeedX() - super.getRadius()) {

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
            }
        } else {
            this.setX(super.getPlatform().getX() + 45);
            this.setY(super.getPlatform().getY() - 20);
        }
    }

    @Override
    public void pressSpace(boolean command) {
        this.spacePressed = command;
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
            brick.hitBrick();
            if (brick.isDestroyed()) {
                this.collectBonus(brick, game);
            }
        }
    }
    public void sizeUp() {
        this.setHeight(40);
        this.setWidth(40);
        this.setRadius(20);
    }

    public void speedUp() {
        this.setSpeedX((int) (this.getSpeedX() * 1.7));
        this.setSpeedY((int) (this.getSpeedY() * 1.7));
    }

    private void collectBonus(Brick brick, Game game) {
        if (brick.getBonus() != null) {
            game.addBonus(brick.getBonus());
        }
    }
}
