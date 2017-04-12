package levels;


import units.bricks.BonusHolder;
import units.bricks.Brick;
import units.bricks.Stone;

import java.util.Random;

//The pattern for all levels
public abstract class LevelImpl implements Level {

    private static final int BONUS_AMOUNT_DIVIDER = 2;
    //We initialize an array with all bonus types
    private static final String[] BONUS_TYPES = {"ballSizeUp", "platformSizeUp", "threeBalls", "ballSpeedUp", "platformSizeDown", "platformSpeedUp"};
    private Brick[] bricks;
    private Stone[] stones;

    public Brick[] getBricks() {
        return this.bricks;
    }

    public Stone[] getStones() {
        return this.stones;
    }


    //All bricks and stones will be generated in the corresponding class
    protected void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    protected void setStones(Stone[] stones) {
        this.stones = stones;
    }


    //Here we set the bonuses using Random selection.
    //First we get the amount of bonuses, corresponding to the amount of bricks for current level.
    //Then for each iteration of the loop we add a random bonus to a random bricks.

    protected void setBonuses(Brick[] bricks) {

        Random rnd = new Random();

        int bonusAmount = bricks.length / BONUS_AMOUNT_DIVIDER;
        for (int i = 0; i < bonusAmount; i++) {
            int n = rnd.nextInt(BONUS_TYPES.length);
            String currentBonusType = BONUS_TYPES[n];
            boolean bonusAssigned = false;

            while (!bonusAssigned) {

                n = rnd.nextInt(bricks.length);

                if (((BonusHolder) bricks[n]).getBonus() == null) {
                    ((BonusHolder) bricks[n]).addBonus(currentBonusType);
                    bonusAssigned = true;
                }
            }
        }
    }
}
