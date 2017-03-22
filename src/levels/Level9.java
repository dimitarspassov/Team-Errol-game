package levels;

import units.Brick;
import units.Stone;

import java.util.ArrayList;
import java.util.List;

public class Level9 extends Level implements ILevel {

    public Level9() {
        super.setBricks(this.generateBricks());
        super.setStones(this.generateStones());
    }

    @Override
    public Brick[] generateBricks() {

        List<Brick> bricks = new ArrayList<>();

        for (int i = 1; i < 10; i += 2) {
            for (int j = 0; j < (4 - i / 2); j++) {
                bricks.add(new Brick(20 + j * 50 * 3, 48 + i * 12 * 3));
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

        for (int i = 10; i >= 1; i -= 2) {
            for (int j = (4 - i / 2); j >= 0; j--) {
                stones.add(new Stone(20 + j * 50 * 3, 48 + i * 12 * 3));
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
