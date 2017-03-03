package units;

import javax.swing.*;

/**
 * Created by Krasimir on 1.3.2017 г..
 */
public class Bonus extends Brick{
    private String bonusType;

    public String getBonusType() {
        return this.bonusType;
    }

   // public Bonus(int x, int y) {
//
   //     super(x, y);
   //     setImage(new ImageIcon(
   //             this.getClass().getResource("/bonus_ball_up.png")).getImage());
   // }

    public Bonus(int x ,int y,String bonusType) {
        super(x,y);
        this.bonusType = bonusType;
        switch(bonusType){

            case "ballSizeUp":setImage(new ImageIcon(
                    this.getClass().getResource("/bonus_ball_up.png")).getImage());break;
            case "platformSizeUp":setImage(new ImageIcon(
                    this.getClass().getResource("/bonus_platform_up.png")).getImage());break;
            case "threeBalls":setImage(new ImageIcon(
                    this.getClass().getResource("/bonus_three_balls.png")).getImage());break;

        }

    }
}
