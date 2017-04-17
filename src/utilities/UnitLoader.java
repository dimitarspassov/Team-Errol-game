package utilities;


import annotations.LevelClass;
import enumerations.BonusType;
import game.Game;
import levels.Level;
import units.bullets.Bullet;
import units.bonuses.Bonus;
import units.bricks.Brick;
import units.platform.Platform;
import units.bricks.Stone;
import units.ballsAndBullets.Ball;
import units.ballsAndBullets.SimpleBall;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UnitLoader {

    public static void renderBalls(List<Ball> balls, Graphics graphics) {

        balls.stream().forEach(ball -> {
            graphics.drawImage(ball.getImage(),
                    ball.getX(),
                    ball.getY(),
                    ball.getWidth(),
                    ball.getHeight(), null);
        });

    }

    public static void renderBonuses(ArrayList<Bonus> bonuses, List<Ball> balls, Brick[] bricks, Stone[] stones, Platform platform, Graphics graphics, Game game) {


        for (Bonus bonus : bonuses) {
            if (bonus.getStatus()) {
                bonus.setY(bonus.getY() + 3);
                graphics.drawImage(bonus.getImage(), bonus.getX(), bonus.getY(),
                        bonus.getWidth(), bonus.getHeight(), null);
            }
            if (bonus.getRect().intersects(new Rectangle(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight()))) {
                BonusType bonusType = bonus.getBonusType();
                bonus.setStatus(false);
                switch (bonusType) {
                    case BALL_SIZE_UP:
                        //SimpleBall Size Up Bonus
                        balls.stream().forEach(ball -> ball.sizeUp());
                        break;
                    case PLATFORM_SIZE_UP:
                        //Platform Size Up Bonus
                        platform.sizeUp();
                        break;
                    case PLATFORM_SIZE_DOWN:
                        //Platform Size Down Bonus
                        platform.sizeDown();
                        break;

                    case BALL_SPEED_UP:
                        //SimpleBall Speed Up Bonus
                        balls.stream().forEach(ball -> ball.speedUp());
                        break;

                    case PLATFORM_SPEED_UP:
                        //Platform Speed Up Bonus
                        platform.speedUp();
                        break;
                    case PLATFORM_FIRE:
                        //Platform Fire
                        platform.canFire(true);
                        break;
                    case LIVE_UP:
                        //Live up
                        game.liveUp();
                        break;
                    case THREE_BALLS:
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


    private static Level currentLevelClass(byte level) {


        Level currentLevel = null;
        File levelsFile = new File(StaticData.LEVEL_FOLDER);

        for (File file : levelsFile.listFiles()) {

            if (!file.isFile() || !file.getName().endsWith(".java")) {
                continue;
            }

            String fileName = file.getName();
            if (fileName.equals("Level" + level + ".java")) {

                try {
                    Class<Level> currentLevelClass = (Class<Level>) Class.forName(StaticData.LEVEL_CLASS_LOCATION + "Level" + level);
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

    public static void renderBullets(List<Bullet> bullets, Graphics graphics) {
        bullets.stream().forEach(bullet -> {
            graphics.drawImage(bullet.getImage(),
                    bullet.getX(),
                    bullet.getY(),
                    bullet.getWidth(),
                    bullet.getHeight(), null);
        });
    }
}
