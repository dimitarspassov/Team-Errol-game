package units;

import javax.swing.*;
import java.awt.*;

// The Brick class represents the breakable tiles.
public class Brick {


    /* The destroyed attribute indicates whether the current brick is destroyed.
     * The game loop uses this attribute to decide whether or not to draw it
     * to the screen and consider for collision checks.
     *
     * This method is set to public for now because it has only 2 possible
     * values: true and false, which I can directly control. And there is
     * no other functionality associated with changing this value
     * (like keeping values in limits, for example).
     */
    protected Image image;
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    public boolean destroyed;
    public int hitCount;
    public void hitBrick(){
        this.hitCount--;
        if(hitCount==2){
            setImage(new ImageIcon(
                    this.getClass().getResource("/brick_yellow.png")).getImage());
        }
        if(hitCount==1){
            setImage(new ImageIcon(
                    this.getClass().getResource("/brick_green.png")).getImage());
        }

        if(this.hitCount==0){
            this.destroyed = true;
        }
    }
    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
        width = height = 0;
        image = null;
        hitCount =3;
        setImage(new ImageIcon(
                this.getClass().getResource("/brick.png")).getImage());
        destroyed = false;
    }
    public void setImage(Image img) {
        image = img;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    public Image getImage() { return image; }

    public int getWidth() {return width; }
    public int getHeight() { return height; }
    public int getX() { return x; }

    // Setter and getter methods for the Y attribute
    public void setY(int y) { this.y = y; }
    public int getY() { return y; }

    /* Getter method for sprite rectangle. It is typically the rectangle of the
     * image, but strictly speaking it is the bounding box of the sprite, used
     * for collision detection.
     */
    public Rectangle getRect() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
}
