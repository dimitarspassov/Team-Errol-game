package graphics;


import utilities.StaticData;

import java.awt.*;

public class BackgroundLoader {

    public static void setBackgroundForLevel(byte level, Graphics graphics) {

        switch (level) {
            case 1:
                graphics.drawImage(ImageLoader.loadImage(StaticData.BACKGROUND_PIC_ONE), 0, 0, 800, 600, null);
                break;
            case 2:
                graphics.drawImage(ImageLoader.loadImage(StaticData.BACKGROUND_PIC_TWO), 0, 0, 800, 600, null);
                break;
            case 3:
                graphics.drawImage(ImageLoader.loadImage(StaticData.BACKGROUND_PIC_THREE), 0, 0, 800, 600, null);
                break;
            case 4:
                graphics.drawImage(ImageLoader.loadImage(StaticData.BACKGROUND_PIC_FOUR), 0, 0, 800, 600, null);
                break;
            case 5:
                graphics.drawImage(ImageLoader.loadImage(StaticData.BACKGROUND_PIC_FIVE), 0, 0, 800, 600, null);
                break;
            case 6:
                graphics.drawImage(ImageLoader.loadImage(StaticData.BACKGROUND_PIC_SIX), 0, 0, 800, 600, null);
                break;
            case 7:
                graphics.drawImage(ImageLoader.loadImage(StaticData.BACKGROUND_PIC_SEVEN), 0, 0, 800, 600, null);
                break;
            case 8:
                graphics.drawImage(ImageLoader.loadImage(StaticData.BACKGROUND_PIC_EIGHT), 0, 0, 800, 600, null);
                break;
            case 9:
                graphics.drawImage(ImageLoader.loadImage(StaticData.BACKGROUND_PIC_NINE), 0, 0, 800, 600, null);
                break;
            case 10:
                graphics.drawImage(ImageLoader.loadImage(StaticData.BACKGROUND_PIC_TEN), 0, 0, 800, 600, null);
                break;

        }
    }
}
