package levels;

import annotations.LevelClass;
import units.bricks.Brick;
import units.bricks.RectangleBrick;
import units.bricks.Stone;

import java.util.ArrayList;
import java.util.List;
@LevelClass
public class Level7 extends LevelImpl implements Level {

    public Level7() {
        super.setBricks(this.generateBricks());
        super.setStones(this.generateStones());
    }

    @Override
    public Brick[] generateBricks() {

        List<Brick> bricks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 2 && j == 1 || i == 2 && j == 3) {
                    continue;
                }
                bricks.add(new RectangleBrick(100 + j * 40 * 3, 48 + i * 12 * 3));
            }
        }

        Brick[] generatedBricks = new Brick[bricks.size()];
        generatedBricks = bricks.toArray(generatedBricks);
        setBonuses(generatedBricks);
        return generatedBricks;
    }

    @Override
    public Stone[] generateStones() {

        List<Stone> stones = new ArrayList<>();

        for (int i = 6; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                stones.add(new Stone(100 + (j * 40 * 3) * 2, 48 + i * 12 * 3));
            }
        }
        stones.add(new Stone(100 + 40 * 3, 48 + 2 * 12 * 3));
        stones.add(new Stone(100 + 3 * 40 * 3, 48 + 2 * 12 * 3));
        stones.add(new Stone(100 + (40 * 3) * 2, 48 + 8 * 12 * 3));

        Stone[] generatedStones = new Stone[stones.size()];
        generatedStones = stones.toArray(generatedStones);
        super.setBonuses(generatedStones);
        return generatedStones;
    }
}
