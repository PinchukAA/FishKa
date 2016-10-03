package graphics;

import constants.FishTypeConstants;
import constants.FishDirectionConstants;
import game.Fish;
import utils.ResourceLoader;
import window.*;
import window.Window;

import java.awt.*;
import java.awt.image.BufferedImage;


public class FishSpriteRenderer {
    private Graphics2D graphics;
    private BufferedImage leftImage;
    private BufferedImage rightImage;

    public FishSpriteRenderer(String LEFT_IMAGE_NAME, String RIGHT_IMAGE_NAME){
        this.graphics = Window.getGraphics();

        leftImage = ResourceLoader.loadImage(LEFT_IMAGE_NAME);
        rightImage = ResourceLoader.loadImage(RIGHT_IMAGE_NAME);
    }

    public void setPlayersSprite(String LEFT_IMAGE_NAME, String RIGHT_IMAGE_NAME){
        leftImage = ResourceLoader.loadImage(LEFT_IMAGE_NAME);
        rightImage = ResourceLoader.loadImage(RIGHT_IMAGE_NAME);
    }

    public void renderSharkWarning(Fish fish){
        Color color = new Color(128, 56, 54, 150);
        graphics.setColor(color);
        graphics.fillRoundRect(fish.getX(), fish.getY(), FishTypeConstants.SHARK_SPRITE_SIZE, FishTypeConstants.SHARK_SPRITE_SIZE, 50, 50);

        graphics.setFont(new Font("TimesRoman", Font.BOLD, 40));
        graphics.drawString("SHARKS ARE COMING!!!", 380, 380);
    }

    public void render(int x, int y, int i) {

        switch (i){
            case FishDirectionConstants.LEFT:
                graphics.drawImage(leftImage, x, y, leftImage.getWidth(),  leftImage.getHeight(), null);
                break;
            case FishDirectionConstants.RIGHT:
                graphics.drawImage(rightImage, x, y, rightImage.getWidth(), rightImage.getHeight(), null);
                break;
        }
    }
}