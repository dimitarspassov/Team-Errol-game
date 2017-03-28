package game;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener, Commons {

    private Game game;

    public MouseInput(Canvas canvas, Game game) {
        canvas.addMouseListener(this);
        this.game = game;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mX = e.getX();
        int mY = e.getY();

        if (Game.getPauseState()) {
            // Resume game Button when user is pressed ESC
            if (mX >= 300 && mX <= 500) {
                if (mY >= 250 && mY <= 300) {
                    Game.turnPauseOnOff(false);
                    game.playSound(SOUND_BUTTON);
                }
            }

            //Exit Button when user is pressed ESC
            if (mX >= 300 && mX <= 500) {
                if (mY >= 350 && mY <= 400) {
                    game.playSound(SOUND_BUTTON);
                    System.exit(1);
                }
            }
        }

        // Play/mute sound
        if (mX >= 740 && mX <= 780) {
            if (mY >= 50 && mY <= 90) {
                Game.turnSoundOnOff();
                game.playSound(SOUND_BUTTON);
            }
        }

        if (this.game.getGameState() == State.MENU) {

            //Resume game Button
            if (Game.getCurrentLevel() > 1 && mX >= 300 && mX <= 500) {
                if (mY >= 100 && mY <= 150) {
                    this.game.setState(State.GAME);
                    game.playSound(SOUND_BUTTON);

                }
            }

            //StartGame Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 200 && mY <= 250) {
                    this.game.setState(State.GAME);
                    Game.setCurrentLevel((byte) 1);
                    Game.levelSwitched = true;
                    game.playSound(SOUND_BUTTON);
                }
            }

            //HighScores Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 300 && mY <= 350) {
                    this.game.setState(State.HIGHSCORES);
                    game.playSound(SOUND_BUTTON);
                }
            }

            //Exit Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 400 && mY <= 450) {
                    game.playSound(SOUND_BUTTON);
                    System.exit(1);

                }
            }
        }

        if (this.game.getGameState() == State.MID_LEVEL_PAUSE) {
            //Next Level Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 300 && mY <= 350) {
                    this.game.setState(State.GAME);
                    game.playSound(SOUND_BUTTON);
                }
            }

            //Back to Menu Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 400 && mY <= 450) {
                    Game.levelSwitched = true;
                    this.game.setState(State.MENU);
                    game.playSound(SOUND_BUTTON);

                }
            }
        }

        if (this.game.getGameState() == State.GAME_OVER || this.game.getGameState() == State.WIN) {


            if ((Game.highScores.sortScores().size() < 10 || Game.lastResult > Game.highScores.getMinResult()) && Game.lastResult > 0) {
                //Add player name
                if (mX >= 300 && mX <= 500) {
                    if (mY >= 200 && mY <= 250) {

                        this.game.setState(State.PLAYER_INIT);
                        game.playSound(SOUND_BUTTON);
                    }
                }

            }
            //Back to Menu Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 300 && mY <= 350) {
                    Game.setCurrentLevel((byte) 1);
                    Game.levelSwitched = true;
                    this.game.setState(State.MENU);
                    game.playSound(SOUND_BUTTON);
                }
            }
        }

        if (this.game.getGameState() == State.PLAYER_INIT) {

            //Back to Menu Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 500 && mY <= 550) {
                    this.game.setState(State.MENU);
                    Game.setCurrentLevel((byte) 1);
                    Game.levelSwitched = true;
                    game.playSound(SOUND_BUTTON);
                }
            }

            //Set player username & go to highscores
            if (mX >= 300 && mX <= 500) {
                if (mY >= 400 && mY <= 450) {
                    if (Game.playerName.length() > 0) {

                        Game.highScores.insertPlayer(Game.playerName.toString(), Game.lastResult);
                        this.game.setState(State.HIGHSCORES);
                        game.playSound(SOUND_BUTTON);
                    }
                }
            }
        }

        if (this.game.getGameState() == State.HIGHSCORES) {

            //Back to Menu Button
            if (mX >= 300 && mX <= 500) {
                if (mY >= 500 && mY <= 550) {
                    this.game.setState(State.MENU);
                    game.playSound(SOUND_BUTTON);

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
