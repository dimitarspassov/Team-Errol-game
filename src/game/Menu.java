package game;

import graphics.ImageLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Menu {

    public void render(Graphics g, byte level) {


        if (Game.State == Game.STATE.MENU) {

            if (Game.getCurrentLevel() > 1) {
                g.drawImage(ImageLoader.loadImage("/button_resume-game.png"), 300, 100, 200, 50, null);
            }
            g.drawImage(ImageLoader.loadImage("/button_start-game.png"), 300, 200, 200, 50, null);
            g.drawImage(ImageLoader.loadImage("/button_highscores.png"), 300, 300, 200, 50, null);
            g.drawImage(ImageLoader.loadImage("/button_exit.png"), 300, 400, 200, 50, null);

        } else if (Game.State == Game.STATE.MID_LEVEL_PAUSE) {

            g.setColor(Color.WHITE);
            Font f1 = new Font("arial", Font.BOLD, 30);
            g.setFont(f1);
            g.drawString(String.format("Level %d completed!", --level), 250, 200);
            g.drawImage(ImageLoader.loadImage("/button_next-level.png"), 300, 300, 200, 50, null);
            g.drawImage(ImageLoader.loadImage("/button_back-to-menu.png"), 300, 400, 200, 50, null);

        } else if (Game.State == Game.STATE.GAME_OVER) {


            g.drawImage(ImageLoader.loadImage("/button_game-over.png"), 250, 100, 300, 50, null);

            if (Game.highScores.sortScores().size() < 10 || (Game.highScores.sortScores().size() == 10 && Game.lastResult > Game.highScores.getMinResult())) {
                if (Game.lastResult > 0) {
                    g.drawImage(ImageLoader.loadImage("/button_add-your-score.png"), 300, 200, 200, 50, null);
                    g.drawImage(ImageLoader.loadImage("/button_new-highscore.png"), 550, 200, 282, 204, null);
                }
            }
            g.drawImage(ImageLoader.loadImage("/button_back-to-menu.png"), 300, 300, 200, 50, null);


        } else if (Game.State == Game.STATE.WIN) {
            g.drawImage(ImageLoader.loadImage("/button_winner.png"), 250, 100, 300, 50, null);

            if (Game.highScores.sortScores().size() < 10 || (Game.highScores.sortScores().size() == 10 && Game.lastResult > Game.highScores.getMinResult())) {
                if (Game.lastResult > 0) {
                    g.drawImage(ImageLoader.loadImage("/button_add-your-score.png"), 300, 200, 200, 50, null);
                    g.drawImage(ImageLoader.loadImage("/button_new-highscore.png"), 550, 200, 282, 204, null);
                }
            }
            g.drawImage(ImageLoader.loadImage("/button_back-to-menu.png"), 300, 300, 200, 50, null);


        } else if (Game.State == Game.STATE.PLAYER_INIT) {

            g.drawImage(ImageLoader.loadImage("/inputBgr.png"), 300, 200, 200, 50, null);
            g.setColor(Color.WHITE);
            Font f1 = new Font("arial", Font.ITALIC, 30);
            g.setFont(f1);
            g.drawString(Game.playerName.toString(), 315, 235);
            g.drawImage(ImageLoader.loadImage("/button_add-your-score.png"), 300, 400, 200, 50, null);
            g.drawImage(ImageLoader.loadImage("/button_back-to-menu.png"), 300, 500, 200, 50, null);

        } else if (Game.State == Game.STATE.HIGHSCORES) {

            ArrayList<String> table = Game.highScores.sortScores();

            if (table.isEmpty()) {

                g.drawImage(ImageLoader.loadImage("/button_no-highscores-yet.png"), 175, 200, 450, 50, null);

            } else {

                g.drawImage(ImageLoader.loadImage("/results_backgr.png"), 175, 100, 450, 350, null);
                g.drawImage(ImageLoader.loadImage("/button_name-score.png"), 175, 100, 450, 50, null);

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
            g.drawImage(ImageLoader.loadImage("/button_back-to-menu.png"), 300, 500, 200, 50, null);
        }

    }
}
