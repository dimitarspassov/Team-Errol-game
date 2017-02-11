package graphics;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

// We use it for easier image loading.
public class ImageLoader {

    public static BufferedImage loadImage(String path) {

        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException ex) {
            System.out.println();
        }
        return null;
    }
}
