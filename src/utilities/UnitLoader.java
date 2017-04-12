package utilities;


import annotations.LevelClass;
import graphics.ImageLoader;
import levels.ILevel;
import units.bonuses.Bonus;
import units.bricks.Brick;
import units.platform.Platform;
import units.bricks.Stone;
import units.balls.Ball;
import units.balls.SimpleBall;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UnitLoader {

    public static void renderBalls(List<Ball> balls, Graphics graphics) {

        balls.stream().forEach(ball -> {
            graphics.drawImage(ImageLoader.loadImage(ball.getImage()),
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
                        //SimpleBall Size Up Bonus
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
                        //SimpleBall Speed Up Bonus
                        balls.stream().forEach(ball -> ball.speedUp());
                        break;

                    case "platformSpeedUp":
                        //Platform Speed Up Bonus
                        platform.speedUp();
                        break;
                    case "threeBalls":
                        //Three SimpleBall Bonus
                        List<SimpleBall> ballsNew = new ArrayList<>();
                        balls.stream().forEach(ball -> {
                            ballsNew.add(new SimpleBall(
                                    ball.getX() + 15,
                                    ball.getY() - 15,
                                    ball.getRadius(),
                                    ball.getWidth(),
                                    ball.getHeight(),
                                    ball.getSpeedX(),
                                    ball.getSpeedY() * -1,
                                    platform, bricks, stones));

                            ballsNew.add(new SimpleBall(
                                    ball.getX() - 15,
                                    ball.getY() + 15,
                                    ball.getRadius(),
                                    ball.getWidth(),
                                    ball.getHeight(),
                                    ball.getSpeedX() * -2,
                                    ball.getSpeedY(),
                                    platform, bricks, stones));
                        });
                        balls.addAll(ballsNew);
                        balls.forEach(b -> b.pressSpace(true));
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


        ILevel currentLevel = null;
        File levelsFile = new File(StaticData.LEVEL_FOLDER);

        for (File file : levelsFile.listFiles()) {

            if (!file.isFile() || !file.getName().endsWith(".java")) {
                continue;
            }

            String fileName = file.getName();
            if (fileName.equals("Level" + level + ".java")) {

                try {
                    Class<ILevel> currentLevelClass = (Class<ILevel>) Class.forName(StaticData.LEVEL_CLASS_LOCATION + "Level" + level);
                    if (!currentLevelClass.isAnnotationPresent(LevelClass.class)) {
                        continue;
                    }
                    currentLevel = currentLevelClass.newInstance();

                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
        return currentLevel;

    }
}
