package gameState;


import units.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UnitLoader {

    public static void renderBalls(List<Ball> balls, Graphics graphics) {

        balls.stream().forEach(ball -> {
            ball.render(graphics);
            graphics.setColor(Color.WHITE);
            graphics.fillOval((int) ball.getCenterX(), (int) ball.getCenterY(), ball.getH(), ball.getW());
        });

    }

    public static void renderBonuses(ArrayList<Bonus> bonuses, List<Ball> balls, Brick[] bricks, Stone[] stones, Platform platform, Graphics graphics) {


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
                        balls.add(new Ball(
                                (int) balls.get(0).getCenterX()+15,
                                (int) balls.get(0).getCenterY()-15,
                                balls.get(0).getRadius(),
                                balls.get(0).getW(),
                                balls.get(0).getH(),
                                balls.get(0).getSpeedX(),
                                balls.get(0).getSpeedY() * -1,
                                platform, bricks, stones));

                        balls.add(new Ball(
                                (int) balls.get(0).getCenterX()-15,
                                (int) balls.get(0).getCenterY()+15,
                                balls.get(0).getRadius(),
                                balls.get(0).getW(),
                                balls.get(0).getH(),
                                balls.get(0).getSpeedX(),
                                balls.get(0).getSpeedY() * -1,
                                platform, bricks, stones));
                        break;
                }
            }
        }

    }
}
