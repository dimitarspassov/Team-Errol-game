package game;


import graphics.ImageLoader;

import java.awt.*;

public class Menu {

    public void render(Graphics g){

        g.drawImage(ImageLoader.loadImage("/button_start-game.png"), 300, 100, 200,50,null);
        g.drawImage(ImageLoader.loadImage("/button_highscores.png"), 300, 200, 200,50,null);
        g.drawImage(ImageLoader.loadImage("/button_exit.png"), 300, 300, 200,50,null);
    }
}
