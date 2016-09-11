package graphics;

import utils.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;


public class PlayerSprite {
    private static final String LEFT_IMAGE_NAME = "pl64L.png";
    private static final String RIGHT_IMAGE_NAME = "pl64R.png";

    private BufferedImage leftImage;
    private BufferedImage rightImage;

    public PlayerSprite() {
        leftImage = ResourceLoader.loadImage(LEFT_IMAGE_NAME);
        rightImage = ResourceLoader.loadImage(RIGHT_IMAGE_NAME);
    }

    public void render(Graphics2D g, int x, int y, int i) {

        switch (i){
            case 1:
                g.drawImage(leftImage, x, y, leftImage.getWidth(), leftImage.getHeight(), null);
                break;

            case 2:
                g.drawImage(rightImage, x, y, rightImage.getWidth(), rightImage.getHeight(), null);
                break;
        }
    }
}