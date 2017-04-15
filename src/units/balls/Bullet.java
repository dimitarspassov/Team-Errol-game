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
public class Bullet extends AbstractBall{
    private static final Image image = ImageLoader.loadImage(StaticData.PIC_BULLET);
    public Bullet(int x, int y, int radius, int width, int height, int speedX, int speedY, Platform platform, Brick[] bricks, Stone[] stones) {
        super(x, y, radius, width, height, speedX, speedY, platform, bricks, stones, image);
    }

    public void move(Game game) {
         this.setY(this.getY()-5);

            // Draw the bricks
            if (super.getBricks() != null) {
                for (Brick brick : super.getBricks()) {
                    if (brick.isDestroyed()) continue;
                    super.hitBrick(brick, game);
                }
            }
    }


}
