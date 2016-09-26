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

    public void render(Graphics2D g, int x, int y, double scale, int i) {

        switch (i){
            case 0:
                g.drawImage(leftImage, x, y, (int)(leftImage.getWidth() * scale), (int)(leftImage.getHeight() * scale), null);
                break;
            case 1:
                g.drawImage(rightImage, x, y, (int)(rightImage.getWidth() * scale), (int)(rightImage.getHeight() * scale), null);
                break;
        }
    }
}