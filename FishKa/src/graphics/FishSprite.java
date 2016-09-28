package graphics;

import utils.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;


public class FishSprite {
    private BufferedImage leftImage;
    private BufferedImage rightImage;

    public FishSprite(String LEFT_IMAGE_NAME, String RIGHT_IMAGE_NAME){
        leftImage = ResourceLoader.loadImage(LEFT_IMAGE_NAME);
        rightImage = ResourceLoader.loadImage(RIGHT_IMAGE_NAME);
    }

    public void setSprite(String LEFT_IMAGE_NAME, String RIGHT_IMAGE_NAME){
        leftImage = ResourceLoader.loadImage(LEFT_IMAGE_NAME);
        rightImage = ResourceLoader.loadImage(RIGHT_IMAGE_NAME);
    }

    public void render(Graphics2D g, int x, int y, int i) {

        switch (i){
            case 0:
                g.drawImage(leftImage, x, y, leftImage.getWidth(),  leftImage.getHeight(), null);
                break;
            case 1:
                g.drawImage(rightImage, x, y, rightImage.getWidth(), rightImage.getHeight(), null);
                break;
        }
    }
}