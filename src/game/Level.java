package game;


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


        return bricks;
    }

    public static Stone[] getStones(byte level) {

        Stone[] stones = null;
        if (level == 5) {
            stones = new Stone[3];
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 3; j++) {
                    stones[j] = new Stone(90 + j * 40 * 6, 280 + i * 12 * 3);
                }
            }
        } else if (level == 6) {
            stones = new Stone[5];

            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 5; j++) {
                    stones[j] = new Stone(50 + j * 50 * 3, 160 + i * 12 * 3);
                }

            }
        } else if (level == 7) {

            stones = new Stone[6];
            for (int i = 6; i < 7; i++) {
                for (int j = 0; j < 3; j++) {
                    stones[j] = new Stone(100 + (j * 40 * 3) * 2, 48 + i * 12 * 3);
                }
            }
            stones[3] = new Stone(100 + 40 * 3, 48 + 2 * 12 * 3);
            stones[4] = new Stone(100 + 3 * 40 * 3, 48 + 2 * 12 * 3);
            stones[5] = new Stone(100 + (40 * 3) * 2, 48 + 8 * 12 * 3);

        } else if (level == 8) {

            int stoneCounter = 0;
            stones = new Stone[6];

            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 1; j++) {
                    stones[stoneCounter++] = new Stone(335 + j * 50 * 3, 115 + i * 12 * 3);
                }
            }

            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 2; j++) {
                    stones[stoneCounter++] = new Stone(263 + j * 50 * 3, 205 + i * 12 * 3);
                }
            }

            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 3; j++) {
                    stones[stoneCounter++] = new Stone(185 + j * 50 * 3, 295 + i * 12 * 3);
                }
            }
        } else if (level == 9) {

            int stoneCounter = 0;
            stones = new Stone[10];

            for (int i = 10; i >= 1; i -= 2) {
                for (int j = (4 - i / 2); j >= 0; j--) {
                    stones[stoneCounter++] = new Stone(20 + j * 50 * 3, 48 + i * 12 * 3);

                }
            }
        } else if (level == 10) {
            stones = new Stone[9];
            int stoneCounter = 0;
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 6; j++) {
                    if ((i == 0 && j == 0) || (j == 1 && i == 2) || (j == 4 && i == 2) || (i == 0 && j == 5) || (j == 0 && i == 6) || (j == 1 && i == 4) || (j == 4 && i == 4) || (j == 5 && i == 6)) {
                        stones[stoneCounter++] = new Stone(22 + j * 42 * 3, 48 + i * 15 * 3);
                    } else if (j == 2 && i == 3) {
                        stones[stoneCounter++] = new Stone(85 + j * 42 * 3, 48 + i * 15 * 3);
                    }
                }
            }
        }
        return stones;
    }
}