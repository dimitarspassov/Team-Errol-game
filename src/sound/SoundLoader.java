package sound;


import game.Commons;
import game.Game;

import javax.sound.sampled.*;
import java.io.IOException;

public class SoundLoader implements Commons {

    private AudioInputStream ais;
    private Clip clip;
    private String currentState;

    public SoundLoader() {
        currentState = "menu";
    }

    public void playBackgroundMusic(boolean soundOff) {


        if ((soundOff && clip != null) || (this.stateChanged() && clip != null)) {

            this.clip.stop();
            clip = null;

        } else if (!soundOff && clip == null) {

            try {
                if (currentState.equals("menu")) {
                    this.ais = AudioSystem.getAudioInputStream(this.getClass().getResource(SOUND_MENU));
                } else {
                    this.ais = AudioSystem.getAudioInputStream(this.getClass().getResource(SOUND_BACKGROUND));
                }

                this.clip = AudioSystem.getClip();
                clip.open(ais);
                clip.loop(50);

            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean stateChanged() {

        if (currentState.equals("menu") && Game.State != Game.STATE.GAME) {

            currentState = "menu";
            return false;

        } else if (currentState.equals("menu") && Game.State == Game.STATE.GAME) {

            currentState = "game";
            return true;
        } else if (currentState.equals("game") && Game.State == Game.STATE.GAME) {
            currentState = "game";
            return false;
        } else {

            currentState = "menu";
            return true;
        }
    }
}
