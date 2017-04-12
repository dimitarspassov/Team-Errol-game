package levels;

import annotations.LevelClass;
import units.bricks.Brick;
import units.bricks.RectangleBrick;
import units.bricks.Stone;

import java.util.ArrayList;
import java.util.List;

@LevelClass
public class Level1 extends LevelImpl implements Level {

    public Level1() {
        super.setBricks(this.generateBricks());
        super.setStones(this.generateStones());
    }

    @Override
    public Brick[] generateBricks() {

        List<Brick> bricks = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                bricks.add(new RectangleBrick(157 + j * 40 * 3, 48 + i * 12 * 3, 1));
            }
        }

        Brick[] generatedBricks = new Brick[bricks.size()];
        generatedBricks = bricks.toArray(generatedBricks);
        super.setBonuses(generatedBricks);
        return generatedBricks;
    }

    @Override
    public Stone[] generateStones() {

        return null;
    }

}
