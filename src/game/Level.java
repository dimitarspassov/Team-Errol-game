package game;


import units.Brick;
import units.Stone;

public class Level {


    public static Brick[] getLevel(byte level) {


        int bricksRemaining = 0;
        Brick[] bricks = new Brick[0];
        if (level == 1) {bricks = new Brick[8];

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    bricks[bricksRemaining++] = new Brick(157 + j * 40 * 3, 48 + i * 12 * 3,1);
                }
            }

        } else if (level == 2) {


            bricks = new Brick[8];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if ((i + j) % 2 == 0) {
                        if (i < 3){
                            bricks[bricksRemaining++] = new Brick(157 + j * 40 * 3, 48 + i * 12 * 3, 2);
                        } else {
                            bricks[bricksRemaining++] = new Brick(157 + j * 40 * 3, 48 + i * 12 * 3, 1);
                        }
                    }
                }
            }
        } else if (level == 3) {
            bricks = new Brick[30];

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    if (i <= 1){
                        bricks[bricksRemaining++] = new Brick(40 + j * 40 * 3, 48 + i * 12 * 3);
                    } else {
                        bricks[bricksRemaining++] = new Brick(40 + j * 40 * 3, 48 + i * 12 * 3, 2);
                    }
                }
            }

        } else if (level == 4) {

            bricks = new Brick[30];
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 5; j++) {
                    if (j == 1 && i == 1 || j == 1 && i == 5 || j == 3 && i == 1 || j == 3 && i == 5 || i == 3 && j == 2) {
                        continue;
                    }
                    bricks[bricksRemaining++] = new Brick(100 + j * 40 * 3, 48 + i * 12 * 3);
                }
            }
        } else if (level == 5) {


            bricks = new Brick[30];

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    bricks[bricksRemaining++] = new Brick(40 + j * 40 * 3, 48 + i * 12 * 3);
                }
            }

        } else if (level == 6) {
            bricks = new Brick[20];

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 5; j++) {
                    bricks[bricksRemaining++] = new Brick(50 + j * 50 * 3, 48 + i * 25 * 3);
                }
            }
        } else if (level == 7) {
            bricks = new Brick[23];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (i == 2 && j == 1 || i == 2 && j == 3) {
                        continue;
                    }
                    bricks[bricksRemaining++] = new Brick(100 + j * 40 * 3, 48 + i * 12 * 3);
                }
            }
        } else if (level == 8) {
            bricks = new Brick[18];

            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 6; j++) {
                    bricks[bricksRemaining++] = new Brick(22 + j * 42 * 3, 48 + i * 15 * 3);
                }
            }

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    bricks[bricksRemaining++] = new Brick(22 + j * 42 * 3, 93 + i * 15 * 3);
                }

                for (int j = 0; j < 2; j++) {
                    bricks[bricksRemaining++] = new Brick(526 + j * 42 * 3, 93 + i * 15 * 3);
                }
            }

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    bricks[bricksRemaining++] = new Brick(22 + j * 210 * 3, 183 + i * 15 * 3);
                }
            }
        } else if (level == 9) {
            bricks = new Brick[10];

            for (int i = 1; i < 10; i += 2) {
                for (int j = 0; j < (4 - i / 2); j++) {
                    bricks[bricksRemaining++] = new Brick(20 + j * 60 * 3, 48 + i * 15 * 3);
                }

            }
        } else if (level == 10){
            bricks = new Brick[32];
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 6; j++) {
                    if ((i == 0 && j == 0) || (j == 1 && i == 2) || (j == 2 && i == 3) || (j == 3 && i == 3) || (j == 4 && i == 2) || (i == 0 && j == 5) || (j == 0 && i == 6) || (j == 1 && i == 4) || (j == 4 && i == 4) || (j == 5 && i == 6)){
                        continue;
                    }
                    bricks[bricksRemaining++] = new Brick(22 + j * 42 * 3, 48 + i * 15 * 3);
                }
            }
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
                    stones[stoneCounter++] = new Stone(20 + j * 60 * 3, 48 + i * 15 * 3);

                }
            }
        } else if (level == 10){
            stones = new Stone[9];
            int stoneCounter = 0;
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 6; j++) {
                    if ((i == 0 && j == 0) || (j == 1 && i == 2) || (j == 4 && i == 2) || (i == 0 && j == 5) || (j == 0 && i == 6) || (j == 1 && i == 4) || (j == 4 && i == 4) || (j == 5 && i == 6)){
                        stones[stoneCounter++] = new Stone(22 + j * 42 * 3, 48 + i * 15 * 3);
                    } else if (j == 2 && i == 3){
                        stones[stoneCounter++] = new Stone(85 + j * 42 * 3, 48 + i * 15 * 3);
                    }
                }
            }
        }
        return stones;
    }
}