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
            //StartGame Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 100 && mY <= 150) {
                    Game.State = Game.STATE.GAME;
                }
            }

            //Exit Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 300 && mY <= 350) {
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

            //Exit Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 400 && mY <= 450) {
                    Game.currentLevel = 1;
                    Game.levelSwitched = true;
                    Game.State = Game.STATE.MENU;

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
