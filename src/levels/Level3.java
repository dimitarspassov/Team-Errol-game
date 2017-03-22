package levels;

import units.Brick;
import units.Stone;

import java.util.ArrayList;
import java.util.List;

public class Level3 extends Level implements ILevel {

    public Level3() {
        super.setBricks(this.generateBricks());
        super.setStones(this.generateStones());
    }

    @Override
    public Brick[] generateBricks() {

        List<Brick> bricks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (i <= 1) {
                    bricks.add(new Brick(40 + j * 40 * 3, 48 + i * 12 * 3));
                } else {
                    bricks.add(new Brick(40 + j * 40 * 3, 48 + i * 12 * 3, 2));
                }
            }
        }

        Brick[] generatedBricks = new Brick[bricks.size()];
        generatedBricks = bricks.toArray(generatedBricks);
        setBonuses(generatedBricks);
        return generatedBricks;
    }

    @Override
    public Stone[] generateStones() {

        return null;
    }

    @Override
    public void setBonuses(Brick[] bricks) {

        super.setBonuses(bricks);

    }

}
