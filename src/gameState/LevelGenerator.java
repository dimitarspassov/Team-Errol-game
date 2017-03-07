package gameState;

import units.Brick;
import units.Stone;

import java.util.ArrayList;
import java.util.List;

//This is a specific class for generating the levels. We will use it in order to avoid duplicated code.

public class LevelGenerator {


    private byte level;

    private int firstLoopMaxVal;
    private int secondLoopMaxVal;

    //These fields correspond to the arguments passed for brick positioning for each level.
    private int xA;
    private int xB;
    private int xC;
    private int yA;
    private int yB;
    private int yC;

    public LevelGenerator(byte level) {
        this.level = level;
    }

    public void setX(int xA, int xB, int xC) {
        this.xA = xA;
        this.xB = xB;
        this.xC = xC;
    }

    public void setY(int yA, int yB, int yC) {
        this.yA = yA;
        this.yB = yB;
        this.yC = yC;
    }


    public Brick[] generateBricksForCurrentLevel(int firstLoopMaxVal, int secondLoopMaxVal, int hitCount) {

        this.firstLoopMaxVal = firstLoopMaxVal;
        this.secondLoopMaxVal = secondLoopMaxVal;
        List<Brick> bricks = new ArrayList<>();

        //Level 8 is with a bit more complicated structure. Here we use our fillBricks method multiple times, because
        // we find a repeating pattern in this level.
        if (level == 8) {

            fillBricks(bricks, hitCount);
            this.yA = 93;
            this.firstLoopMaxVal = 2;
            fillBricks(bricks, 3);
            this.xB = 210;
            this.yA = 183;
            this.secondLoopMaxVal = 2;
            fillBricks(bricks, 3);

        } else {
            fillBricks(bricks, hitCount);
        }


        Brick[] generatedBricks = new Brick[bricks.size()];
        generatedBricks = bricks.toArray(generatedBricks);
        return generatedBricks;
    }


    private void fillBricks(List<Brick> bricks, int hitCount) {

        //This is the first loop. We check for any specific level, which would
        //require different from the standard logic. Otherwise we run the inner loop.
        //However, inside of it we have to check again for special cases.
        for (int i = 0; i < this.firstLoopMaxVal; i++) {

            if (level == 2) {

                for (int j = 0; j < this.secondLoopMaxVal; j++) {

                    if ((i + j) % 2 == 0) {
                        if (i < 3) {
                            bricks.add(new Brick(xA + j * xB * xC, yA + i * yB * yC, 2));
                        } else {
                            bricks.add(new Brick(xA + j * xB * xC, yA + i * yB * yC, 1));
                        }
                    }
                    if(bricks.size()==8){
                        bricks.get(3).addBonus("ballSpeedUp");
                        bricks.get(7).addBonus("ballSpeedUp");
                        bricks.get(1).addBonus("ballSpeedUp");
                    }
                }
            } else if (level == 8 && yA == 93) {

                for (int j = 0; j < 2; j++) {
                    bricks.add(new Brick(xA + j * xB * xC, yA + i * yB * yC));
                }

                for (int j = 0; j < 2; j++) {
                    bricks.add(new Brick(526 + j * xB * xC, yA + i * yB * yC));
                }

            } else if (level == 9) {

                for (int j = 0; j < (4 - i / 2); j++) {
                    bricks.add(new Brick(xA + j * xB * xC, yA + i * yB * yC, 3));
                }

            } else {

                for (int j = 0; j < this.secondLoopMaxVal; j++) {

                    if (level == 3) {

                        if (i <= 1) {
                            bricks.add(new Brick(xA + j * xB * xC, yA + i * yB * yC));
                        } else {
                            bricks.add(new Brick(xA + j * xB * xC, yA + i * yB * yC, 2));
                        }

                    } else if (level == 4) {

                        if (j == 1 && i == 1 || j == 1 && i == 5 || j == 3 && i == 1 || j == 3 && i == 5 || i == 3 && j == 2) {
                            continue;
                        }
                        bricks.add(new Brick(xA + j * xB * xC, yA + i * yB * yC));

                    } else if (level == 7) {

                        if (i == 2 && j == 1 || i == 2 && j == 3) {
                            continue;
                        }
                        bricks.add(new Brick(xA + j * xB * xC, yA + i * yB * yC));

                    } else if (level == 10) {

                        if ((i == 0 && j == 0) || (j == 1 && i == 2) || (j == 2 && i == 3) || (j == 3 && i == 3) || (j == 4 && i == 2) || (i == 0 && j == 5) || (j == 0 && i == 6) || (j == 1 && i == 4) || (j == 4 && i == 4) || (j == 5 && i == 6)) {
                            continue;
                        }
                        bricks.add(new Brick(xA + j * xB * xC, yA + i * yB * yC));

                    } else {
                        bricks.add(new Brick(xA + j * xB * xC, yA + i * yB * yC, hitCount));
                        if(bricks.size()==8){
                          //  bricks.get(3).addBonus("ballSizeUp");
                          //  bricks.get(7).addBonus("threeBalls");
                          //  bricks.get(1).addBonus("platformSizeUp");
                           // bricks.get(3).addBonus("ballSizeUp");
                           // bricks.get(5).addBonus("ballSpeedUp");
                           // bricks.get(7).addBonus("threeBalls");
                            bricks.get(3).addBonus("threeBalls");
                             bricks.get(5).addBonus("threeBalls");
                             bricks.get(7).addBonus("threeBalls");
                            bricks.get(1).addBonus("threeBalls");
                        }

                    }

                }
            }
        }
    }

    public Stone[] generateStonesForCurrentLevel(int firstLoopMaxVal, int secondLoopMaxVal, int firstLoopStart, int secondLoopStart) {

        this.firstLoopMaxVal = firstLoopMaxVal;
        this.secondLoopMaxVal = secondLoopMaxVal;

        List<Stone> stones = new ArrayList<>();

        fillStones(stones, firstLoopStart, secondLoopStart);

        if (level == 8) {

            this.firstLoopMaxVal = 1;
            this.secondLoopMaxVal = 2;
            this.xA = 263;
            this.yA = 205;
            fillStones(stones, firstLoopStart, secondLoopStart);

            this.firstLoopMaxVal = 1;
            this.secondLoopMaxVal = 3;
            this.xA = 185;
            this.yA = 295;
            fillStones(stones, firstLoopStart, secondLoopStart);
        }

        Stone[] generatedStones = new Stone[stones.size()];
        generatedStones = stones.toArray(generatedStones);
        return generatedStones;
    }

    private void fillStones(List<Stone> stones, int firstLoopStart, int secondLoopStart) {

        if (level == 9) {

            for (int i = firstLoopStart; i >= this.firstLoopMaxVal; i -= 2) {
                for (int j = (4 - i / 2); j >= 0; j--) {
                    stones.add(new Stone(xA + j * xB * xC, yA + i * yB * yC));
                }
            }

        } else {

            for (int i = firstLoopStart; i < this.firstLoopMaxVal; i++) {
                for (int j = secondLoopStart; j < this.secondLoopMaxVal; j++) {

                    if (level == 10) {

                        if ((i == 0 && j == 0) || (j == 1 && i == 2) || (j == 4 && i == 2) || (i == 0 && j == 5) || (j == 0 && i == 6) || (j == 1 && i == 4) || (j == 4 && i == 4) || (j == 5 && i == 6)) {
                            stones.add(new Stone(xA + j * xB * xC, yA + i * yB * yC));
                        } else if (j == 2 && i == 3) {
                            stones.add(new Stone(85 + j * xB * xC, yA + i * yB * yC));
                        }
                    }else if(level==7){

                        stones.add(new Stone(xA + j * xB * xC*2, yA + i * yB * yC));

                    } else{
                        stones.add(new Stone(xA + j * xB * xC, yA + i * yB * yC));
                    }
                }
            }

            if (level == 7) {

                stones.add(new Stone(100 + 40 * 3, 48 + 2 * 12 * 3));
                stones.add(new Stone(100 + 3 * 40 * 3, 48 + 2 * 12 * 3));
                stones.add(new Stone(100 + (40 * 3) * 2, 48 + 8 * 12 * 3));
            }
        }
    }
}
