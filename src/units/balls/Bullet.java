package units.balls;

import game.Game;
import graphics.ImageLoader;
import units.bricks.Brick;
import units.bricks.Stone;
import units.platform.Platform;
import utilities.StaticData;

import java.awt.*;

/**
 * Created by Krasimir on 15.4.2017 г..
 */
public class Bullet extends AbstractBall implements Ball  {
    private static final Image image = ImageLoader.loadImage(StaticData.PIC_BALL);
    public Bullet(int x, int y, int radius, int width, int height, int speedX, int speedY, Platform platform, Brick[] bricks, Stone[] stones) {
        super(x, y, radius, width, height, speedX, speedY, platform, bricks, stones, image);
    }

    @Override
    public void move(Game game) {
         this.setY(this.getY()-3);


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


    }
    private void hitBrick(Brick brick, Game game) {

        if (brick.getRect().intersects(new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight()))) {
            brick.hitBrick();
            if (brick.isDestroyed()) {
                this.collectBonus(brick, game);
            }
        }
    }
    private void collectBonus(Brick brick, Game game) {
        if (brick.getBonus() != null) {
            game.addBonus(brick.getBonus());
        }
    }
    @Override
    public void pressSpace(boolean command) {

    }

    @Override
    public void sizeUp() {

    }

    @Override
    public void speedUp() {

    }
}
