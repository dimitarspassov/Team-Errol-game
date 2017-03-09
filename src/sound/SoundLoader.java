package sound;


import game.Commons;

import javax.sound.sampled.*;
import java.io.IOException;

public class SoundLoader implements Commons {

    private AudioInputStream ais;
    private Clip clip;


    public void playBackgroundMusic(boolean soundOff) {

        if (soundOff && clip != null) {

            this.clip.stop();
            clip = null;

        } else if (!soundOff && clip == null) {
            try {

                this.ais = AudioSystem.getAudioInputStream(this.getClass().getResource(SOUND_BACKGROUND));
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
}
