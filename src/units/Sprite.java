package units;

import java.awt.Image;
import java.awt.Rectangle;


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

    // Setter and getter methods for the X attribute
    public void setX(int x) { this.x = x; }
    public int getX() { return x; }

    // Setter and getter methods for the Y attribute
    public void setY(int y) { this.y = y; }
    public int getY() { return y; }

    // Setter and getter methods for the DX attribute
    public void setDx(int dx) { this.dx = dx; }
    public int getDx() { return dx; }

    // Setter and getter methods for the DY attribute
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

    /* Getter method for sprite rectangle. It is typically the rectangle of the
     * image, but strictly speaking it is the bounding box of the sprite, used
     * for collision detection.
     */
    public Rectangle getRect() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
}
