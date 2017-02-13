package game;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    public MouseInput(Canvas canvas) {
        canvas.addMouseListener(this);

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mX = e.getX();
        int mY = e.getY();

        if (Game.State == Game.STATE.MENU) {

            //Resume game Button
            if (Game.currentLevel > 1 && mX >= 300 && mX <= 500) {
                if (mY >= 100 && mY <= 150) {
                    Game.State = Game.STATE.GAME;

                }
            }

            //StartGame Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 200 && mY <= 250) {
                    Game.State = Game.STATE.GAME;
                    Game.currentLevel = 1;
                    Game.levelSwitched = true;
                }
            }

            //Exit Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 400 && mY <= 450) {
                    System.exit(1);
                }
            }
        }

        if (Game.State == Game.STATE.PAUSE) {
            //Next Level Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 300 && mY <= 350) {
                    Game.State = Game.STATE.GAME;
                }
            }

            //Back to Menu Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 400 && mY <= 450) {
                    Game.levelSwitched = true;
                    Game.State = Game.STATE.MENU;

                }
            }
        }

        if (Game.State == Game.STATE.WIN) {


            //Add player name
            if (mX >= 300 && mX <= 500) {
                if (mY >= 200 && mY <= 250) {

                    Game.State = Game.STATE.PLAYER_INIT;
                }
            }

            //Back to Menu Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 300 && mY <= 350) {
                    Game.currentLevel = 1;
                    Game.levelSwitched = true;
                    Game.State = Game.STATE.MENU;
                }
            }
        }

        if (Game.State == Game.STATE.PLAYER_INIT) {

            //Back to Menu Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 500 && mY <= 550) {
                    Game.State = Game.STATE.MENU;
                    Game.currentLevel = 1;
                    Game.levelSwitched = true;
                }
            }

            //Set player username & go to highscores
            if (mX >= 300 && mX <= 500) {
                if (mY >= 400 && mY <= 450) {
                    Game.State = Game.STATE.HIGHSCORES;
                }
            }
        }

        if (Game.State == Game.STATE.HIGHSCORES) {

            //Back to Menu Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 500 && mY <= 550) {
                    Game.State = Game.STATE.MENU;
                    Game.currentLevel = 1;
                    Game.levelSwitched = true;
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
