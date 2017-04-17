package units.bullets;

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
public class Bullet extends AbstractBullet implements Bulletable{
    private static final Image image = ImageLoader.loadImage(StaticData.PIC_BULLET);
        public Bullet(int x, int y, int width, int height, Platform platform, Brick[] bricks, Stone[] stones) {
        super(x, y, width, height, platform, bricks, stones, image);
    }

    public void move(Game game) {
         this.setY(this.getY()-5);
         // Draw the bricks
            if (super.getBricks() != null) {
                for (Brick brick : super.getBricks()) {
                    if (brick.isDestroyed()) continue;
                    this.hitBrick(brick, game);
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

}
