package graphics;


import java.awt.*;

public class BackgroundLoader {

    public static void setBackgroundForLevel(byte level, Graphics graphics) {

        switch (level) {
            case 1:
                graphics.drawImage(ImageLoader.loadImage("/backgroundPic1.png"), 0, 0, 800, 600, null);
                break;
            case 2:
                graphics.drawImage(ImageLoader.loadImage("/backgroundPic2.png"), 0, 0, 800, 600, null);
                break;
            case 3:
                graphics.drawImage(ImageLoader.loadImage("/backgroundPic3.png"), 0, 0, 800, 600, null);
                break;
            case 4:
                graphics.drawImage(ImageLoader.loadImage("/backgroundPic4.png"), 0, 0, 800, 600, null);
                break;
            case 5:
                graphics.drawImage(ImageLoader.loadImage("/backgroundPic5.png"), 0, 0, 800, 600, null);
                break;
            case 6:
                graphics.drawImage(ImageLoader.loadImage("/backgroundPic6.png"), 0, 0, 800, 600, null);
                break;
            case 7:
                graphics.drawImage(ImageLoader.loadImage("/backgroundPic7.png"), 0, 0, 800, 600, null);
                break;
            case 8:
                graphics.drawImage(ImageLoader.loadImage("/backgroundPic8.png"), 0, 0, 800, 600, null);
                break;
            case 9:
                graphics.drawImage(ImageLoader.loadImage("/backgroundPic9.png"), 0, 0, 800, 600, null);
                break;
            case 10:
                graphics.drawImage(ImageLoader.loadImage("/backgroundPic10.png"), 0, 0, 800, 600, null);
                break;

        }
    }
}
