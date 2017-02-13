package game;

import units.Ball;
import units.Platform;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//This is the input handler class. We will extend it later with movement commands for the platform.
public class InputHandler implements KeyListener {


    public InputHandler(Canvas canvas) {
        canvas.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (Game.State == Game.STATE.PLAYER_INIT) {

            if (code == 8) {
                if (Game.playerName.length() > 0) {
                    Game.playerName.deleteCharAt(Game.playerName.length() - 1);
                }
            } else if ((code >= 48 && code <= 57) || (code >= 65 && code <= 90) || (code >= 97 && code <= 122)) {

                if (Game.playerName.length() < 11) {
                    Game.playerName.append(e.getKeyChar());
                }

            }
        }

        // Press Space to start ball's moving
        if (code == KeyEvent.VK_SPACE) {
            Ball.isSpacePressed = true;
        }

        //Implementing platform's moving
        if (code == KeyEvent.VK_RIGHT) {
            Platform.isMovingRight = true;
            Platform.isMovingLeft = false;
        } else if (code == KeyEvent.VK_LEFT) {
            Platform.isMovingRight = false;
            Platform.isMovingLeft = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_RIGHT) {
            Platform.isMovingRight = false;
            Platform.isMovingLeft = false;
        } else if (code == KeyEvent.VK_LEFT) {
            Platform.isMovingRight = false;
            Platform.isMovingLeft = false;
        }
    }


}