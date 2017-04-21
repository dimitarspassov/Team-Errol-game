package utilities;


import annotations.LevelClass;
import enumerations.BonusType;
import factories.UnitFactory;
import game.Game;
import levels.Level;
import units.GameUnit;
import units.Movable;
import units.balls.Ball;
import units.balls.SimpleBall;
import units.bonuses.Bonus;
import units.bricks.Brick;
import units.bricks.Stone;
import units.platform.Platform;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UnitLoader {


    public <T extends Movable> void renderMovableObjects(List<T> moveableCollection, Graphics graphics) {
        moveableCollection.stream().forEach(movable -> prepareUnitForDrawing(graphics, movable));
    }

    public void renderBonuses(ArrayList<Bonus> bonuses, List<Ball> balls, Brick[] bricks, Stone[] stones, Platform platform, Graphics graphics, Game game) {
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
                        balls.forEach(Ball::sizeUp);
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
                        balls.forEach(Ball::speedUp);
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
//                        List<SimpleBall> ballsNew = new ArrayList<>();
                        List<Ball> ballsNew = new ArrayList<>();

                        for (Ball ball : balls) {
                            ballsNew.add(UnitFactory.makeTwoMoreBalls(ball, "BallOne", platform, bricks, stones));
                        }

                        for (Ball ball : balls) {
                            ballsNew.add(UnitFactory.makeTwoMoreBalls(ball, "BallTwo", platform, bricks, stones));

                        }
                        balls.addAll(ballsNew);
                        balls.forEach(b -> b.pressSpace(true));
                        break;
                    case FIRE_BALL: {
                        game.getPlayer().setFireBalls(bricks, stones);
                    }
                    break;
                    case FROST_BALL: {
                        game.getPlayer().setFrostBalls(bricks, stones);
                    }
                    break;
                    case KILL: {
                        game.getPlayer().decreaseLives();
                        game.getPlayer().init(bricks, stones);
                    }
                    break;
                }
            }
        }
    }


    public Brick[] getBricks(byte level) {

        return currentLevelClass(level).generateBricks();
    }

    public Stone[] getStones(byte level) {

        return currentLevelClass(level).generateStones();
    }


    private Level currentLevelClass(byte level) {


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

    public static void prepareUnitForDrawing(Graphics graphics, GameUnit unit) {
        graphics.drawImage(unit.getImage(),
                unit.getX(),
                unit.getY(),
                unit.getWidth(),
                unit.getHeight(), null);
    }
}