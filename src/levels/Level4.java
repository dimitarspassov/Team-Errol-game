package levels;

import annotations.LevelClass;
import units.bricks.Brick;
import units.bricks.RectangleBrick;
import units.bricks.Stone;

import java.util.ArrayList;
import java.util.List;
@LevelClass
public class Level4 extends LevelImpl implements Level {

    public Level4() {
        super.setBricks(this.generateBricks());
        super.setStones(this.generateStones());
    }

    @Override
    public Brick[] generateBricks() {

        List<Brick> bricks = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 1 && i == 1 || j == 1 && i == 5 || j == 3 && i == 1 || j == 3 && i == 5 || i == 3 && j == 2) {
                    continue;
                }
                bricks.add(new RectangleBrick(100 + j * 40 * 3, 48 + i * 12 * 3));
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
