package levels;

import annotations.LevelClass;
import units.bricks.Brick;
import units.bricks.RectangleBrick;
import units.bricks.Stone;

import java.util.ArrayList;
import java.util.List;
@LevelClass
public class Level5 extends LevelImpl implements Level {

    public Level5() {
        super.setBricks(this.generateBricks());
        super.setStones(this.generateStones());
    }

    @Override
    public Brick[] generateBricks() {

        List<Brick> bricks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                bricks.add(new RectangleBrick(40 + j * 40 * 3, 48 + i * 12 * 3));
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
            for (int j = 0; j < 3; j++) {
                stones.add(new Stone(90 + j * 40 * 6, 280 + i * 12 * 3));
            }
        }
        Stone[] generatedStones = new Stone[stones.size()];
        generatedStones = stones.toArray(generatedStones);
        super.setBonuses(generatedStones);
        return generatedStones;
    }

}
