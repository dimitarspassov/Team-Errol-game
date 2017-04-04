package sound;


import game.Game;
import game.State;
import gameState.StaticData;

import javax.sound.sampled.*;
import java.io.IOException;

public class SoundLoader {

    private Game game;
    private AudioInputStream ais;
    private Clip clip;
    private State currentState;

    public SoundLoader(Game game) {

        currentState = State.MENU;
        this.game = game;
    }

    public void playBackgroundMusic(boolean soundOff) {


        if ((soundOff && clip != null) || (this.stateChanged() && clip != null)) {

            this.clip.stop();
            clip = null;

        } else if (!soundOff && clip == null) {

            try {
                if (this.currentState == State.MENU) {
                    this.ais = AudioSystem.getAudioInputStream(this.getClass().getResource(StaticData.SOUND_MENU));
                } else {
                    this.ais = AudioSystem.getAudioInputStream(this.getClass().getResource(StaticData.SOUND_BACKGROUND));
                }

                this.clip = AudioSystem.getClip();
                clip.open(ais);
                clip.loop(50);

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean stateChanged() {

        if (this.currentState == State.MENU && this.game.getGameState() != State.GAME) {
            currentState = State.MENU;
            return false;

        } else if (this.currentState == State.MENU && this.game.getGameState() == State.GAME) {
            currentState = State.GAME;
            return true;
        } else if (currentState == State.GAME && this.game.getGameState() == State.GAME) {
            currentState = State.GAME;
            return false;
        } else {

            currentState = State.MENU;
            return true;
        }
    }

    public void playSound(String filename) {

        if (!Game.isSoundMuted()) {
            try {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource(filename));
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
