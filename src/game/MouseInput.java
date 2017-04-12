package game;


import enumerations.State;
import utilities.StaticData;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

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

        if (Game.isPaused()) {
            // Resume game Button when user presses ESC
            if (buttonClicked(mX, mY, 300, 500, 250, 300)) {
                Game.turnPauseOnOff(false);
                game.playSound(StaticData.SOUND_BUTTON);
            }

            //Exit Button when user presses ESC
            if (buttonClicked(mX, mY, 300, 500, 350, 400)) {
                game.playSound(StaticData.SOUND_BUTTON);
                System.exit(1);
            }
        }

        // Play/mute sound
        if (buttonClicked(mX, mY, 740, 780, 50, 90)) {
            Game.turnSoundOnOff();
            game.playSound(StaticData.SOUND_BUTTON);
        }

        if (this.game.getGameState() == State.MENU) {

            //Resume game Button

            if (Game.getCurrentLevel() > 1 && buttonClicked(mX, mY, 300, 500, 100, 150)) {
                this.game.setState(State.GAME);
                game.playSound(StaticData.SOUND_BUTTON);
            }


            //StartGame Button
            if (buttonClicked(mX, mY, 300, 500, 200, 250)) {

                this.game.setState(State.GAME);
                Game.setCurrentLevel((byte) 1);
                Game.levelSwitched = true;
                game.playSound(StaticData.SOUND_BUTTON);

            }

            //HighScores Button
            if (buttonClicked(mX, mY, 300, 500, 300, 350)) {
                this.game.setState(State.HIGHSCORES);
                game.playSound(StaticData.SOUND_BUTTON);
            }

            //Exit Button
            if (buttonClicked(mX, mY, 300, 500, 400, 450)) {
                game.playSound(StaticData.SOUND_BUTTON);
                System.exit(1);
            }
        }

        if (this.game.getGameState() == State.MID_LEVEL_PAUSE) {
            //Next Level Button
            if (buttonClicked(mX, mY, 300, 500, 300, 350)) {

                this.game.setState(State.GAME);
                game.playSound(StaticData.SOUND_BUTTON);
            }

            //Back to Menu Button
            if (buttonClicked(mX, mY, 300, 500, 400, 450)) {

                Game.levelSwitched = true;
                this.game.setState(State.MENU);
                game.playSound(StaticData.SOUND_BUTTON);
            }
        }

        if (this.game.getGameState() == State.GAME_OVER || this.game.getGameState() == State.WIN) {


            if ((Game.highScores.sortScores().size() < 10 || Game.lastResult > Game.highScores.getMinResult()) && Game.lastResult > 0) {
                //Add player name
                if (buttonClicked(mX, mY, 300, 500, 200, 250)) {
                    this.game.setState(State.PLAYER_INIT);
                    game.playSound(StaticData.SOUND_BUTTON);
                }
            }

            //Back to Menu Button
            if (buttonClicked(mX, mY, 300, 500, 300, 350)) {

                Game.setCurrentLevel((byte) 1);
                Game.levelSwitched = true;
                this.game.setState(State.MENU);
                game.playSound(StaticData.SOUND_BUTTON);
            }
        }

        if (this.game.getGameState() == State.PLAYER_INIT) {

            //Back to Menu Button
            if (buttonClicked(mX, mY, 300, 500, 500, 550)) {

                this.game.setState(State.MENU);
                Game.setCurrentLevel((byte) 1);
                Game.levelSwitched = true;
                game.playSound(StaticData.SOUND_BUTTON);

            }

            //Set player username & go to highscores
            if (buttonClicked(mX, mY, 300, 500, 400, 450)) {

                if (game.getPlayerName().length() > 0) {

                    Game.highScores.insertPlayer(game.getPlayerName().toString(), Game.lastResult);
                    this.game.setState(State.HIGHSCORES);
                    game.playSound(StaticData.SOUND_BUTTON);
                }
            }

        }

        if (this.game.getGameState() == State.HIGHSCORES) {

            //Back to Menu Button
            if (buttonClicked(mX, mY, 300, 500, 500, 550)) {
                this.game.setState(State.MENU);
                game.playSound(StaticData.SOUND_BUTTON);
            }
        }

    }


    private boolean buttonClicked(int mX, int mY, int leftBound, int rightBound, int upperBound, int lowerBound) {

        return mX >= leftBound && mX <= rightBound && mY >= upperBound && mY < lowerBound;
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
