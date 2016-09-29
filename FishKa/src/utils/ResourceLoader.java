package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Constants.ResourceConstants;

public class ResourceLoader{

    public static BufferedImage loadImage(String fileName){

        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(ResourceConstants.PATH + fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
