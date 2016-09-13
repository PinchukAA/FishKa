package game;

import utils.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOver {
    private static final String GAME_OVER_IMAGE_NAME = "game_over.png";

    private BufferedImage gameOverImage;

    public GameOver(){
        gameOverImage = ResourceLoader.loadImage(GAME_OVER_IMAGE_NAME);
    }

    public void render(Graphics2D g){
        g.drawImage(gameOverImage, 500, 300, gameOverImage.getWidth(), gameOverImage.getHeight(), null);
    }
}
