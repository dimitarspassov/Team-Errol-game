package units;

import java.awt.*;

/**
 * Created by Krasimir on 4.3.2017 г..
 */

public class Sprite {

    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected Image image;
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


    public void setDx(int dx) { this.dx = dx; }
    public int getDx() { return dx; }


    public void setDy(int dy) { this.dy = dy; }
    public int getDy() { return dy; }


    public void setImage(Image img) {
        image = img;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    public Image getImage() { return image; }


    public int getWidth() {return width; }
    public int getHeight() { return height; }


    public Rectangle getRect() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
}
