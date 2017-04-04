package gameState;


import graphics.ImageLoader;
import levels.*;
import units.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UnitLoader {

    public static void renderBalls(List<Ball> balls, Graphics graphics) {

        balls.stream().forEach(ball -> {
            graphics.drawImage(ImageLoader.loadImage(StaticData.PIC_BALL),
                    ball.getX(),
                    ball.getY(),
                    ball.getWidth(),
                    ball.getHeight(), null);
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
                        List<Ball> ballsNew = new ArrayList<>();
                        balls.stream().forEach(ball -> {
                            ballsNew.add(new Ball(
                                    ball.getX() + 15,
                                    ball.getY() - 15,
                                    ball.getRadius(),
                                    ball.getWidth(),
                                    ball.getHeight(),
                                    ball.getDx(),
                                    ball.getDy() * -1,
                                    platform, bricks, stones));

                            ballsNew.add(new Ball(
                                    ball.getX() - 15,
                                    ball.getY() + 15,
                                    ball.getRadius(),
                                    ball.getWidth(),
                                    ball.getHeight(),
                                    ball.getDx() * -2,
                                    ball.getDy(),
                                    platform, bricks, stones));
                        });
                        balls.addAll(ballsNew);
                        break;
                }
            }
        }
    }

    public static Brick[] getBricks(byte level) {

        return currentLevelClass(level).generateBricks();
    }

    public static Stone[] getStones(byte level) {

        return currentLevelClass(level).generateStones();
    }

    private static ILevel currentLevelClass(byte level) {

        //TODO: Generate new Level via reflection!
        ILevel currentLevel = new Level1();
        switch (level) {
            case 2:
                currentLevel = new Level2();
                break;
            case 3:
                currentLevel = new Level3();
                break;
            case 4:
                currentLevel = new Level4();
                break;
            case 5:
                currentLevel = new Level5();
                break;
            case 6:
                currentLevel = new Level6();
                break;
            case 7:
                currentLevel = new Level7();
                break;
            case 8:
                currentLevel = new Level8();
                break;
            case 9:
                currentLevel = new Level9();
                break;
            case 10:
                currentLevel = new Level10();
                break;
        }

        return currentLevel;

    }
}
