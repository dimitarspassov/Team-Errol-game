package game;


import graphics.ImageLoader;

import java.awt.*;

public class Menu {

    public void render(Graphics g, byte level) {


        if (Game.State == Game.STATE.MENU) {

            g.drawImage(ImageLoader.loadImage("/button_start-game.png"), 300, 100, 200, 50, null);
            g.drawImage(ImageLoader.loadImage("/button_highscores.png"), 300, 200, 200, 50, null);
            g.drawImage(ImageLoader.loadImage("/button_exit.png"), 300, 300, 200, 50, null);

        } else if (Game.State == Game.STATE.PAUSE) {
            g.setColor(Color.WHITE);
            Font f1 = new Font("arial", Font.BOLD, 30);
            g.setFont(f1);
            g.drawString(String.format("Level %d completed!", --level), 250, 200);
            g.drawImage(ImageLoader.loadImage("/button_next-level.png"), 300, 300, 200, 50, null);

        }

    }
}
