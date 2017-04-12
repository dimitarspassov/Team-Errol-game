package units.bricks;


import enumerations.BonusType;
import units.bonuses.Bonus;
import units.bonuses.BonusImpl;

import java.awt.*;

public abstract class AbstractBrick implements Brick {

    private int x;
    private int y;
    private int hitCount;
    private boolean destroyed;
    private Image image;
    private Bonus bonus;

    protected AbstractBrick(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.setHitCount(3);
        this.destroyed = false;
        this.bonus = null;
    }

    protected AbstractBrick(int x, int y, int hitCountIn) {
        this(x, y);
        this.setHitCount(hitCountIn);
        this.destroyed = false;
    }

    @Override
    public Bonus getBonus() {
        return this.bonus;
    }

    @Override
    public void addBonus(BonusType type) {
        this.setBonus(new BonusImpl(this.getX(), this.getY(), type));
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getHitCount() {
        return this.hitCount;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(this.getX(), this.getY(), this.image.getWidth(null), this.image.getHeight(null));
    }

    @Override
    public void destroy() {
        this.destroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return this.destroyed;
    }

    @Override
    public int getWidth() {
        return this.image.getWidth(null);
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public int getHeight() {
        return this.image.getHeight(null);
    }

    protected void setImage(Image img) {
        this.image = img;
    }

    protected void decreaseHitCount() {
        this.hitCount--;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    private void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    private void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

}
