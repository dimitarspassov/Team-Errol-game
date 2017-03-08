package gameState;


import game.Commons;
import graphics.ImageLoader;
import units.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UnitLoader implements Commons{

    public static void renderBalls(List<Ball> balls, Graphics graphics) {

        balls.stream().forEach(ball -> {
             graphics.drawImage(ImageLoader.loadImage(PIC_BALL),
                   ball.getX(),
                   ball.getY(),
                   ball.getWidth(),
                   ball.getHeight(),null);
        });

    }

    public static void renderBonuses(ArrayList<Bonus> bonuses, List<Ball> balls, Brick[] bricks, Stone[] stones, Platform platform, Graphics graphics) {


        for (Bonus bonus : bonuses) {
            if (bonus.getStatus()) {
                bonus.setY(bonus.getY() + 3);
                graphics.drawImage(bonus.getImage(), bonus.getX(), bonus.getY(),
                        bonus.getWidth(), bonus.getHeight(), null);
            }
            if (bonus.getRect().intersects(new Rectangle(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight()))) {
                String bonusType = bonus.getBonusType();
                bonus.setStatus(false);
                switch (bonusType) {
                    case "ballSizeUp":
                        //Ball Size Up Bonus
                        balls.stream().forEach(ball -> ball.sizeUp());
                        break;
                    case "platformSizeUp":
                        //Platform Size Up Bonus
                        platform.sizeUp();
                        break;
                    case "platformSizeDown":
                        //Platform Size Down Bonus
                        platform.sizeDown();
                        break;

                    case "ballSpeedUp":
                        //Ball Speed Up Bonus
                        balls.stream().forEach(ball -> ball.speedUp());
                        break;

                    case "platformSpeedUp":
                        //Platform Speed Up Bonus
                        platform.speedUp();
                        break;
                    case "threeBalls":
                        //Three Ball Bonus
                        List<Ball> ballsNew = new ArrayList<>() ;
                        balls.stream().forEach(ball ->{ballsNew.add(new Ball(
                                 ball.getX()+15,
                                        ball.getY()-15,
                                ball.getRadius(),
                                ball.getWidth(),
                                ball.getHeight(),
                                ball.getDx(),
                                ball.getDy() * -1,
                                platform, bricks, stones));

                            ballsNew.add(new Ball(
                                        ball.getX()-15,
                                ball.getY()+15,
                                ball.getRadius(),
                                ball.getWidth(),
                                ball.getHeight(),
                                ball.getDx()*-2,
                                ball.getDy() ,
                                platform, bricks, stones));
                                  });
                       balls.addAll(ballsNew);
                        break;
                }
            }
        }

    }
}
