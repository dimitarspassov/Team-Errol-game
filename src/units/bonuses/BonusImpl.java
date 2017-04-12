package units.bonuses;

import utilities.StaticData;

import javax.swing.*;
import java.awt.*;


public class BonusImpl implements Bonus {

    private int x;
    private int y;
    private String bonusType;
    private Image image;
    private boolean status;

    public BonusImpl(int x, int y, String bonusType) {
        this.x = x;
        this.y = y;
        this.setStatus(true);
        this.setBonusType(bonusType);
        switch (bonusType) {

            case "ballSizeUp":
                setImage(new ImageIcon(
                        this.getClass().getResource(StaticData.PIC_BALL_UP)).getImage());
                break;
            case "platformSizeUp":
                setImage(new ImageIcon(
                        this.getClass().getResource(StaticData.PIC_PLATFORM_UP)).getImage());
                break;
            case "threeBalls":
                setImage(new ImageIcon(
                        this.getClass().getResource(StaticData.PIC_THREE_BALLS)).getImage());
                break;
            case "ballSpeedUp":
                setImage(new ImageIcon(
                        this.getClass().getResource(StaticData.PIC_BALL_SPEED_UP)).getImage());
                break;
            case "platformSizeDown":
                setImage(new ImageIcon(
                        this.getClass().getResource(StaticData.PIC_PLATFORM_DOWN)).getImage());
                break;
            case "platformSpeedUp":
                setImage(new ImageIcon(
                        this.getClass().getResource(StaticData.PIC_PLATFORM_SPEED_UP)).getImage());
                break;

        }

    }

    private void setBonusType(String bonusType) {
        this.bonusType = bonusType;
    }

    public String getBonusType() {
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
