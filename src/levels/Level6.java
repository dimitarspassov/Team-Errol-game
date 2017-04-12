package levels;

import annotations.LevelClass;
import units.bricks.Brick;
import units.bricks.RectangleBrick;
import units.bricks.Stone;

import java.util.ArrayList;
import java.util.List;

@LevelClass
public class Level6 extends LevelImpl implements Level {

    public Level6() {
        super.setBricks(this.generateBricks());
        super.setStones(this.generateStones());
    }

    @Override
    public Brick[] generateBricks() {

        List<Brick> bricks = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                bricks.add(new RectangleBrick(50 + j * 50 * 3, 48 + i * 25 * 3));
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

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 5; j++) {
                stones.add(new Stone(50 + j * 50 * 3, 160 + i * 12 * 3));
            }
        }

        Stone[] generatedStones = new Stone[stones.size()];
        generatedStones = stones.toArray(generatedStones);
        super.setBonuses(generatedStones);
        return generatedStones;
    }


}
