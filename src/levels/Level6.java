package levels;

import annotations.LevelClass;
import units.brick.Brick;
import units.brick.Stone;

import java.util.ArrayList;
import java.util.List;
@LevelClass
public class Level6 extends Level implements ILevel {

    public Level6() {
        super.setBricks(this.generateBricks());
        super.setStones(this.generateStones());
    }

    @Override
    public Brick[] generateBricks() {

        List<Brick> bricks = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                bricks.add(new Brick(50 + j * 50 * 3, 48 + i * 25 * 3));
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
        setBonuses(generatedStones);
        return generatedStones;
    }

    @Override
    public void setBonuses(Brick[] bricks) {

        super.setBonuses(bricks);

    }

}
