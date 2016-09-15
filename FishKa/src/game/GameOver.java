package game;

import input.Input;
import utils.ResourceLoader;
import window.*;
import window.Window;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOver {
    private static final String GAME_OVER_IMAGE_NAME = "game_over.png";
    private static final String AGAIN_IMAGE_NAME = "again.png";
    private static final String DEATH_IMAGE_NAME = "death.png";

    private BufferedImage gameOverImage;
    private BufferedImage againImage;
    private BufferedImage deathImage;

    public GameOver(){
        gameOverImage = ResourceLoader.loadImage(GAME_OVER_IMAGE_NAME);
        againImage = ResourceLoader.loadImage(AGAIN_IMAGE_NAME);
        deathImage = ResourceLoader.loadImage(DEATH_IMAGE_NAME);

    }

    public void render(Graphics2D g, int x, int y){
        g.drawImage(deathImage, x, y, deathImage.getWidth(), deathImage.getHeight(), null);
        g.drawImage(gameOverImage, 500, 300, gameOverImage.getWidth(), gameOverImage.getHeight(), null);
    }
}
