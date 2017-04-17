package units.platform;


import graphics.ImageLoader;
import utilities.StaticData;

import java.awt.*;

public class SimplePlatform extends AbstractPlatform {


    public SimplePlatform(int x, int y, int width, int height, int velocity) {
        super(x, y, width, height, velocity, ImageLoader.loadImage(StaticData.PIC_LATEST_PLATFORM));
    }


    public void thick() {
        int xMax = 795 - this.getWidth();
        if (super.isMovingRight()) {


            if (this.getX() >= xMax) {
                super.setPlatformX(xMax);
            } else {

                this.setPlatformX(this.getX() + super.getVelocity());

            }
        } else if (super.isMovingLeft()) {

            if (this.getX() <= 0) {
                this.setPlatformX(0);
            } else {
                this.setPlatformX(this.getX() - super.getVelocity());
            }
        }
    }

    public void render(Graphics g) {
        if (super.isMovingRight()) {

            g.drawImage(super.getImage(),
                    this.getX(),
                    this.getY(),
                    this.getWidth(),
                    this.getHeight(), null);

        } else if (super.isMovingLeft()) {

            g.drawImage(super.getImage(),
                    this.getX(),
                    this.getY(),
                    this.getWidth(),
                    this.getHeight(), null);
        }
    }

    @Override
    public void canFire(boolean b) {
        this.setCanFire(b);
    }
}
