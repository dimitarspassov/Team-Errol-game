package units.balls;

import enumerations.State;
import game.Game;
import graphics.ImageLoader;
import units.bricks.Brick;
import units.bricks.Stone;
import units.platform.Platform;
import utilities.StaticData;

import java.awt.*;

public class SimpleBall extends AbstractBall implements Ball {
    private static final Image SIMPLE_BALL_IMAGE = ImageLoader.loadImage(StaticData.PIC_BALL);

    public SimpleBall(int centerX, int centerY, int radius, int w, int h, int speedX, int speedY,
                      Platform platform, Brick[] bricks, Stone[] stones) {
        super(centerX, centerY, radius, w, h, speedX, speedY, platform, bricks, stones, SIMPLE_BALL_IMAGE);
    }

    public void move(Game game) {
        super.move(game);
    }


    protected void hitBrick(Brick brick, Game game) {

        if (brick.getRect().intersects(new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight()))) {

            if (game.getGameState() == State.GAME) {
                game.playSound(StaticData.SOUND_BRICK);
            }
            super.richocet(brick);
            brick.hit();
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
