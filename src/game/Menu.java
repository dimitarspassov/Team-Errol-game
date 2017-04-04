package game;

import gameState.StaticData;
import graphics.ImageLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class Menu {


    private Game game;

    public Menu(Game game) {
        this.game = game;
    }

    public void render(Graphics g, byte level) {


        if (this.game.getGameState() == State.MENU) {

            if (Game.getCurrentLevel() > 1) {
                g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_RESUME_GAME), 300, 100, 200, 50, null);
            }
            g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_START_GAME), 300, 200, 200, 50, null);
            g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_HIGHSCORES), 300, 300, 200, 50, null);
            g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_EXIT), 300, 400, 200, 50, null);

        } else if (this.game.getGameState() == State.MID_LEVEL_PAUSE) {

            g.setColor(Color.WHITE);
            Font f1 = new Font("arial", Font.BOLD, 30);
            g.setFont(f1);
            g.drawString(String.format("Level %d completed!", --level), 250, 100);
            g.drawString(String.format("Bonus collected: %d", Math.max(Game.lastBonusPoints, 0)), 250, 200);

            g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_NEXT_LEVEL), 300, 300, 200, 50, null);
            g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_BACK_TO_MENU), 300, 400, 200, 50, null);

        } else if (this.game.getGameState() == State.GAME_OVER) {


            g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_GAME_OVER), 250, 100, 300, 50, null);

            if (Game.highScores.sortScores().size() < 10 || (Game.highScores.sortScores().size() == 10 && Game.lastResult > Game.highScores.getMinResult())) {
                if (Game.lastResult > 0) {
                    g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_ADD_SCORE), 300, 200, 200, 50, null);
                    g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_NEW_HIGHSCORE), 550, 200, 282, 204, null);
                }
            }
            g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_BACK_TO_MENU), 300, 300, 200, 50, null);


        } else if (this.game.getGameState() == State.WIN) {
            g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_WINNER), 250, 100, 300, 50, null);

            if (Game.highScores.sortScores().size() < 10 || (Game.highScores.sortScores().size() == 10 && Game.lastResult > Game.highScores.getMinResult())) {
                if (Game.lastResult > 0) {
                    g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_ADD_SCORE), 300, 200, 200, 50, null);
                    g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_NEW_HIGHSCORE), 550, 200, 282, 204, null);
                }
            }
            g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_BACK_TO_MENU), 300, 300, 200, 50, null);


        } else if (this.game.getGameState() == State.PLAYER_INIT) {

            g.drawImage(ImageLoader.loadImage(StaticData.PIC_INPUT_BACKGROUND), 300, 200, 200, 50, null);
            g.setColor(Color.WHITE);
            Font f1 = new Font("arial", Font.ITALIC, 30);
            g.setFont(f1);
            g.drawString(Game.playerName.toString(), 315, 235);
            g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_ADD_SCORE), 300, 400, 200, 50, null);
            g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_BACK_TO_MENU), 300, 500, 200, 50, null);

        } else if (this.game.getGameState() == State.HIGHSCORES) {

            ArrayList<String> table = Game.highScores.sortScores();

            if (table.isEmpty()) {

                g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_NO_HIGHSCORES), 175, 200, 450, 50, null);

            } else {

                g.drawImage(ImageLoader.loadImage(StaticData.PIC_RESULTS), 175, 100, 450, 350, null);
                g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_NAME_SCORE), 175, 100, 450, 50, null);

                int i = 0;
                Collections.reverse(table);
                for (String result : table) {

                    String[] data = result.split(":");
                    String name = data[0];
                    String score = data[1];
                    Font f1 = new Font("arial", Font.BOLD, 20);
                    g.setFont(f1);
                    g.setColor(Color.BLACK);
                    g.drawString(name, 230, 170 + i);
                    g.drawString(score, 540, 170 + i);
                    i += 30;
                }
            }
            g.drawImage(ImageLoader.loadImage(StaticData.BUTTON_BACK_TO_MENU), 300, 500, 200, 50, null);
        }

    }
}
