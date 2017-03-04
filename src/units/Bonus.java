package units;

import javax.swing.*;

/**
 * Created by Krasimir on 1.3.2017 г..
 */
public class Bonus extends Brick{
    private String bonusType;
    private boolean status;

    public String getBonusType() {
        return this.bonusType;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public Bonus(int x ,int y,String bonusType) {
        super(x,y);
        this.status=true;
        this.bonusType = bonusType;
        switch(bonusType){

            case "ballSizeUp":setImage(new ImageIcon(
                    this.getClass().getResource(PIC_BALL_UP)).getImage());break;
            case "platformSizeUp":setImage(new ImageIcon(
                    this.getClass().getResource(PIC_PLATFORM_UP)).getImage());break;
            case "threeBalls":setImage(new ImageIcon(
                    this.getClass().getResource(PIC_THREE_BALLS)).getImage());break;
            case "ballSpeedUp":setImage(new ImageIcon(
                    this.getClass().getResource(PIC_BALL_SPEED_UP)).getImage());break;
            case "platformSizeDown":setImage(new ImageIcon(
                    this.getClass().getResource(PIC_PLATFORM_DOWN)).getImage());break;

        }

    }
}
