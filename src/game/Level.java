package game;


import units.Brick;

public class Level {


    public static Brick[] getLevel(byte level) {


        int bricksRemaining=0;
        Brick[]bricks=new Brick[0];
        if (level ==1) {

        bricks = new Brick[8];

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    bricks[bricksRemaining++] = new Brick(40 + j * 40 * 3, 48 + i * 12 * 3);
                }
            }

        } else if (level == 2) {

             bricks = new Brick[30];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    bricks[bricksRemaining++] = new Brick(40 + j * 40 * 3, 48 + i * 12 * 3);
                }
            }
        }

        return bricks;
    }
}
