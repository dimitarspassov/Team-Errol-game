package units;

import java.awt.*;

public class Sprite {

    protected Image image;
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        dx = dy = 0;
        width = height = 0;
        image = null;
    }
    public void setImage(Image img) {
        image = img;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    public Image getImage() { return image; }
    public int getX() { return x; }
    public void setY(int y) { this.y = y; }
    public int getY() { return y; }
    public int getWidth() {return width; }
    public int getHeight() { return height; }

}