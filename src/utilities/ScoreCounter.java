package utilities;


import units.bricks.Brick;
import units.bricks.Stone;

import java.awt.*;

public class ScoreCounter {

    private int score;
    private int lastResult;
    private int lastBonusPoints;
    private int levelScore;

    public ScoreCounter() {
        this.score = 0;
        this.levelScore = 0;
        this.lastBonusPoints = 0;
        this.lastResult = 0;
    }

    public void resetLevelScore() {
        this.levelScore = 0;
    }

    public int getRemainingBricks(Brick[] bricks, Stone[] stones, Graphics graphics) {
        score -= levelScore;
        levelScore = 0;
        int bricksRemaining = bricks.length;
        for (Brick brick : bricks) {

            // If bricks is destroyed, continue to next bricks.
            if (brick.isDestroyed()) {
                // Increment player scores
                levelScore += 5;
                bricksRemaining--;
            } else {
                // Else, draw the bricks.
                if (bricksRemaining != 0) {
                    UnitLoader.prepareUnitForDrawing(graphics, brick);
                }
            }
        }
        if (stones != null) {
            for (Stone stone : stones) {
                if (stone.isDestroyed()) {
                    levelScore += 10;
                }
            }
        }

        score += levelScore;
        return bricksRemaining;
    }

    public int getLastResult() {
        return lastResult;
    }

    public void setLastResult(int lastResult) {
        this.lastResult = lastResult;
    }

    public int getLastBonusPoints() {
        return lastBonusPoints;
    }

    public void setLastBonusPoints(int lastBonusPoints) {
        this.lastBonusPoints = lastBonusPoints;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
