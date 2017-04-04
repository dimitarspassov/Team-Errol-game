package levels;

import annotations.LevelClass;
import units.brick.Brick;
import units.brick.Stone;

import java.util.ArrayList;
import java.util.List;

@LevelClass
public class Level10 extends Level implements ILevel {

    public Level10() {
        super.setBricks(this.generateBricks());
        super.setStones(this.generateStones());
    }

    @Override
    public Brick[] generateBricks() {

        List<Brick> bricks = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if ((i == 0 && j == 0) || (j == 1 && i == 2) || (j == 2 && i == 3) ||
                        (j == 3 && i == 3) || (j == 4 && i == 2) || (i == 0 && j == 5) ||
                        (j == 0 && i == 6) || (j == 1 && i == 4) || (j == 4 && i == 4) || (j == 5 && i == 6)) {
                    continue;
                }
                bricks.add(new Brick(22 + j * 42 * 3, 48 + i * 15 * 3));
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

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if ((i == 0 && j == 0) || (j == 1 && i == 2) || (j == 4 && i == 2) || (i == 0 && j == 5) || (j == 0 && i == 6) || (j == 1 && i == 4) || (j == 4 && i == 4) || (j == 5 && i == 6)) {
                    stones.add(new Stone(22 + j * 42 * 3, 48 + i * 15 * 3));
                } else if (j == 2 && i == 3) {
                    stones.add(new Stone(85 + j * 42 * 3, 48 + i * 15 * 3));
                }
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
