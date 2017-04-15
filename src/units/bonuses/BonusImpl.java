package units.bonuses;

import enumerations.BonusType;
import utilities.StaticData;

import javax.swing.*;
import java.awt.*;


public class BonusImpl implements Bonus {

    private int x;
    private int y;
    private BonusType bonusType;
    private Image image;
    private boolean status;

    public BonusImpl(int x, int y, BonusType bonusType) {
        this.x = x;
        this.y = y;
        this.setStatus(true);
        this.setBonusType(bonusType);
        switch (bonusType) {

            case BALL_SIZE_UP:
                setImage(new ImageIcon(
                        this.getClass().getResource(StaticData.PIC_BALL_UP)).getImage());
                break;
            case PLATFORM_SIZE_UP:
                setImage(new ImageIcon(
                        this.getClass().getResource(StaticData.PIC_PLATFORM_UP)).getImage());
                break;
            case THREE_BALLS:
                setImage(new ImageIcon(
                        this.getClass().getResource(StaticData.PIC_THREE_BALLS)).getImage());
                break;
            case BALL_SPEED_UP:
                setImage(new ImageIcon(
                        this.getClass().getResource(StaticData.PIC_BALL_SPEED_UP)).getImage());
                break;
            case PLATFORM_SIZE_DOWN:
                setImage(new ImageIcon(
                        this.getClass().getResource(StaticData.PIC_PLATFORM_DOWN)).getImage());
                break;
            case PLATFORM_SPEED_UP:
                setImage(new ImageIcon(
                        this.getClass().getResource(StaticData.PIC_PLATFORM_SPEED_UP)).getImage());
                break;
            case LIVE_UP:
                setImage(new ImageIcon(
                        this.getClass().getResource(StaticData.LIVE_UP)).getImage());
                break;

        }

    }

    private void setBonusType(BonusType bonusType) {
        this.bonusType = bonusType;
    }

    public BonusType getBonusType() {
        return this.bonusType;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getWidth() {
        return this.getImage().getWidth(null);
    }

    @Override
    public int getHeight() {
        return this.getImage().getHeight(null);
    }

    public boolean getStatus() {
        return this.status;
    }

    public Image getImage() {
        return this.image;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(this.getX(), this.getY(), this.image.getWidth(null), this.image.getHeight(null));
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private void setImage(Image image) {
        this.image = image;
    }
}
