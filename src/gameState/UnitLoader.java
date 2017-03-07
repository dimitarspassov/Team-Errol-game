package gameState;


import units.*;

import java.awt.*;
import java.util.ArrayList;

public class UnitLoader {

    public static void renderBalls(Ball ball, Ball ballSecond, Ball ballThird, Graphics graphics) {

        //Main Ball
        ball.render(graphics);
        graphics.setColor(Color.WHITE);
        graphics.fillOval((int) ball.getCenterX(), (int) ball.getCenterY(), ball.getH(), ball.getW());
        //Second Ball
        if (ballSecond != null) {
            ballSecond.render(graphics);
            // this.graphics.setColor(Color.WHITE);
            graphics.fillOval((int) ballSecond.getCenterX(), (int) ballSecond.getCenterY(), ballSecond.getH(), ballSecond.getW());
        }
        //Third Ball
        if (ballThird != null) {
            ballThird.render(graphics);
            //this.graphics.setColor(Color.WHITE);
            graphics.fillOval((int) ballThird.getCenterX(), (int) ballThird.getCenterY(), ballThird.getH(), ballThird.getW());
        }

    }

    public static void renderBonuses(ArrayList<Bonus> bonuses, Ball ball, Ball ballSecond, Ball ballThird, Brick[] bricks, Stone[] stones, Platform platform, Graphics graphics) {


        for (Bonus bonus : bonuses) {
            if (bonus.getStatus()) {
                bonus.setY(bonus.getY() + 3);
                graphics.drawImage(bonus.getImage(), bonus.getX(), bonus.getY(),
                        bonus.getWidth(), bonus.getHeight(), null);
            }
            if (bonus.getRect().intersects(new Rectangle(platform.getPlatformX(), platform.getPlatformY(), platform.getPlatformWidth(), platform.getPlatformHeight()))) {
                String bonusType = bonus.getBonusType();
                bonus.setStatus(false);
                switch (bonusType) {
                    case "ballSizeUp":
                        //Ball Size Up Bonus
                        ball.sizeUp();
                        if (ballSecond != null) {
                            ballSecond.sizeUp();
                        }
                        if (ballThird != null) {
                            ballThird.sizeUp();
                        }
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
                        ball.speedUp();
                        if (ballSecond != null) {
                            ballSecond.speedUp();
                        }
                        if (ballThird != null) {
                            ballThird.speedUp();
                        }
                        break;

                    case "platformSpeedUp":
                        //Platform Speed Up Bonus
                        platform.speedUp();
                        break;
                    case "threeBalls":
                        //Three Ball Bonus
                        ballSecond = new Ball(
                                (int) ball.getCenterX(),
                                (int) ball.getCenterY(),
                                ball.getRadius(),
                                ball.getW(),
                                ball.getH(),
                                ball.getSpeedX(),
                                ball.getSpeedY() * -1,
                                platform, bricks, stones);
                        ballThird = new Ball(
                                (int) ball.getCenterX(),
                                (int) ball.getCenterY(),
                                ball.getRadius(),
                                ball.getW(), ball.getH(),
                                ball.getSpeedX() * -1,
                                ball.getSpeedY(),
                                platform, bricks, stones);
                        break;
                }
            }
        }

    }
}
