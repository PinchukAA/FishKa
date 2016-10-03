package utils;

import constants.ResourceConstants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceLoader{

    public static BufferedImage loadImage(String fileName){

        BufferedImage image = null;

        try {
            image = ImageIO.read(loadFile(fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public static File loadFile(String fileName){
        File file = new File(ResourceConstants.PATH + fileName);
        return file;

    }
}
