package gameState;


import units.Brick;
import units.Stone;

public class Level {


    public static Brick[] getLevel(byte level) {

        LevelGenerator generator = new LevelGenerator(level);
        Brick[] bricks = new Brick[0];

        switch (level) {
            case 1: {

                generator.setX(157, 40, 3);
                generator.setY(48, 12, 3);
                bricks = generator.generateBricksForCurrentLevel(2, 4, 1);
            }
            break;
            case 2: {

                generator.setX(157, 40, 3);
                generator.setY(48, 12, 3);
                bricks = generator.generateBricksForCurrentLevel(4, 4, 0);

            }
            break;
            case 3: {

                generator.setX(40, 40, 3);
                generator.setY(48, 12, 3);
                bricks = generator.generateBricksForCurrentLevel(5, 6, 0);
            }
            break;
            case 4: {

                generator.setX(100, 40, 3);
                generator.setY(48, 12, 3);
                bricks = generator.generateBricksForCurrentLevel(7, 5, 0);
            }
            break;
            case 5: {

                generator.setX(40, 40, 3);
                generator.setY(48, 12, 3);
                bricks = generator.generateBricksForCurrentLevel(5, 6, 3);
            }
            break;
            case 6: {

                generator.setX(50, 50, 3);
                generator.setY(48, 25, 3);
                bricks = generator.generateBricksForCurrentLevel(4, 5, 3);
            }
            break;
            case 7: {
                generator.setX(100, 40, 3);
                generator.setY(48, 12, 3);
                bricks = generator.generateBricksForCurrentLevel(5, 5, 3);
            }
            break;
            case 8: {

                generator.setX(22, 42, 3);
                generator.setY(48, 15, 3);
                bricks = generator.generateBricksForCurrentLevel(1, 6, 3);
            }
            break;
            case 9: {

                generator.setX(20, 50, 3);
                generator.setY(48, 12, 3);
                bricks = generator.generateBricksForCurrentLevel(10, 0, 3);
            }
            break;
            case 10: {

                generator.setX(22, 42, 3);
                generator.setY(48, 15, 3);
                bricks = generator.generateBricksForCurrentLevel(7, 6, 3);
            }
            break;

        }

        generator.setBonusesForCurrentLevel(bricks);
        return bricks;
    }

    public static Stone[] getStones(byte level) {

        Stone[] stones = null;
        LevelGenerator generator = new LevelGenerator(level);

        switch (level) {
            case 5: {
                generator.setX(90, 40, 6);
                generator.setY(280, 12, 3);
                stones = generator.generateStonesForCurrentLevel(1, 3, 0, 0);
            }
            break;
            case 6: {
                generator.setX(50, 50, 3);
                generator.setY(160, 12, 3);
                stones = generator.generateStonesForCurrentLevel(1, 5, 0, 0);
            }
            break;
            case 7: {
                generator.setX(100, 40, 3);
                generator.setY(48, 12, 3);
                stones = generator.generateStonesForCurrentLevel(7, 3, 6, 0);
            }
            break;
            case 8: {
                generator.setX(335, 50, 3);
                generator.setY(115, 12, 3);
                stones = generator.generateStonesForCurrentLevel(1, 1, 0, 0);
            }
            break;
            case 9: {
                generator.setX(20, 50, 3);
                generator.setY(48, 12, 3);
                stones = generator.generateStonesForCurrentLevel(1, 0, 10, 0);
            }
            break;
            case 10: {
                generator.setX(22, 42, 3);
                generator.setY(48, 15, 3);
                stones = generator.generateStonesForCurrentLevel(7, 6, 0, 0);
            }
            break;
        }
        return stones;
    }
}