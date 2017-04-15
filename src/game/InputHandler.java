package game;

import enumerations.State;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//This is the input handler class. We will extend it later with movement commands for the platform.
public class InputHandler implements KeyListener {

    private Game game;

    public InputHandler(Canvas canvas, Game game) {
        canvas.addKeyListener(this);
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (this.game.getGameState() == State.PLAYER_INIT) {

            if (code == 8) {
                if (game.getPlayerName().length() > 0) {
                    game.getPlayerName().deleteCharAt(game.getPlayerName().length() - 1);
                }
            } else if ((code >= 48 && code <= 57) || (code >= 65 && code <= 90) || (code >= 97 && code <= 122)) {

                if (game.getPlayerName().length() < 11) {
                    game.getPlayerName().append(e.getKeyChar());
                }

            }
        }

        // Press Space to start balls's moving
        if (code == KeyEvent.VK_SPACE) {
            game.pressSpace(true);
        }
        // Press "Z" to start fire
        if (code == KeyEvent.VK_Z) {
            game.pressFire(true);
        }

        // Press Escape to pause the game
        if (this.game.getGameState() == State.GAME && code == KeyEvent.VK_ESCAPE) {
            game.turnPauseOnOff(true);
        }

        //Implementing platform's moving
        if (code == KeyEvent.VK_RIGHT) {
            game.getPlatform().moveRight(true);
            game.getPlatform().moveLeft(false);
        } else if (code == KeyEvent.VK_LEFT) {
            game.getPlatform().moveRight(false);
            game.getPlatform().moveLeft(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_RIGHT) {
            game.getPlatform().moveRight(false);
            game.getPlatform().moveLeft(false);
        } else if (code == KeyEvent.VK_LEFT) {
            game.getPlatform().moveRight(false);
            game.getPlatform().moveLeft(false);
        }
    }


}