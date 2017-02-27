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

        // Resume game Button when user is pressed ESC
        if (mX >= 300 && mX <= 500) {
            if (mY >= 250 && mY <= 300) {
                Game.isGamePaused = false;
                Game.playSound(this,"/sounds/button.wav");
            }
        }

        //Exit Button when user is pressed ESC
        if (mX >= 300 && mX <= 500) {
            if (mY >= 350 && mY <= 400) {
                Game.playSound(this,"/sounds/button.wav");
                System.exit(1);
            }
        }

        if (Game.State == Game.STATE.MENU) {

            //Resume game Button
            if (Game.currentLevel > 1 && mX >= 300 && mX <= 500) {
                if (mY >= 100 && mY <= 150) {
                    Game.State = Game.STATE.GAME;
                    Game.playSound(this,"/sounds/button.wav");

                }
            }

            //StartGame Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 200 && mY <= 250) {
                    Game.State = Game.STATE.GAME;
                    Game.currentLevel = 1;
                    Game.levelSwitched = true;
                    Game.playSound(this,"/sounds/button.wav");
                }
            }

            //HighScores Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 300 && mY <= 350) {
                    Game.State = Game.STATE.HIGHSCORES;
                    Game.playSound(this,"/sounds/button.wav");
                }
            }

            //Exit Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 400 && mY <= 450) {
                    Game.playSound(this,"/sounds/button.wav");
                    System.exit(1);

                }
            }
        }

        if (Game.State == Game.STATE.PAUSE) {
            //Next Level Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 300 && mY <= 350) {
                    Game.State = Game.STATE.GAME;
                    Game.playSound(this,"/sounds/button.wav");
                }
            }

            //Back to Menu Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 400 && mY <= 450) {
                    Game.levelSwitched = true;
                    Game.State = Game.STATE.MENU;
                    Game.playSound(this,"/sounds/button.wav");

                }
            }
        }

        if (Game.State == Game.STATE.GAME_OVER || Game.State == Game.STATE.WIN) {


            if ((Game.highScores.sortScores().size() < 10 || Game.lastResult > Game.highScores.getMinResult()) && Game.lastResult > 0) {
                //Add player name
                if (mX >= 300 && mX <= 500) {
                    if (mY >= 200 && mY <= 250) {

                        Game.State = Game.STATE.PLAYER_INIT;
                        Game.playSound(this,"/sounds/button.wav");
                    }
                }

            }
            //Back to Menu Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 300 && mY <= 350) {
                    Game.currentLevel = 1;
                    Game.levelSwitched = true;
                    Game.State = Game.STATE.MENU;
                    Game.playSound(this,"/sounds/button.wav");
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
                    Game.playSound(this,"/sounds/button.wav");
                }
            }

            //Set player username & go to highscores
            if (mX >= 300 && mX <= 500) {
                if (mY >= 400 && mY <= 450) {
                    if (Game.playerName.length() > 0) {

                        Game.highScores.insertPlayer(Game.playerName.toString(), Game.lastResult);
                        Game.State = Game.STATE.HIGHSCORES;
                        Game.playSound(this,"/sounds/button.wav");
                    }
                }
            }
        }

        if (Game.State == Game.STATE.HIGHSCORES) {

            //Back to Menu Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 500 && mY <= 550) {
                    Game.State = Game.STATE.MENU;
                    Game.playSound(this,"/sounds/button.wav");

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
