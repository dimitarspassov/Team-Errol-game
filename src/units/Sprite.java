package units;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Krasimir on 4.3.2017 г..
 */

public abstract class Sprite {

    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected Image image;

    private BufferedImage bufferedImage;

    protected int width;
    protected int height;


    public Sprite() {
        x = y = 0;
        dx = dy = 0;
        width = height = 0;
        image = null;
    }
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        dx = dy = 0;
        width = height = 0;
        image = null;
    }


    public void setX(int x) { this.x = x; }
    public int getX() { return x; }


    public void setY(int y) { this.y = y; }
    public int getY() { return y; }


    public void setImage(Image img) {
        image = img;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    public Image getImage() { return image; }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {return width; }
    public int getHeight() { return height; }

    public void setDx(int dx) { this.dx = dx; }
    public int getDx() { return dx; }


    public void setDy(int dy) { this.dy = dy; }
    public int getDy() { return dy; }

    public Rectangle getRect() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
}
