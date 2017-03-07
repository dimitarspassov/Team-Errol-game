package graphics;


import java.awt.*;

public class BackgroundLoader {

    public static void setBackgroundForLevel(byte level, Graphics graphics) {

        if (level <= 3) {
            graphics.drawImage(ImageLoader.loadImage("/backgroundPic1.png"), 0, 0, 800, 600, null);
        } else if (level <= 7) {
            graphics.drawImage(ImageLoader.loadImage("/backgroundPic2.png"), 0, 0, 800, 600, null);
        } else {
            graphics.drawImage(ImageLoader.loadImage("/backgroundPic3.png"), 0, 0, 800, 600, null);
        }
    }
}
