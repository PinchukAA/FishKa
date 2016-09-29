package graphics;

import utils.ResourceLoader;
import java.awt.*;
import java.awt.image.BufferedImage;
import Constants.ImageNameConstants;

public class GameSprite {

    private BufferedImage gameWinImage;
    private BufferedImage winImage;
    private BufferedImage gameOverImage;
    private BufferedImage againImage;
    private BufferedImage deathImage;

    public GameSprite(){

        gameWinImage = ResourceLoader.loadImage(ImageNameConstants.GAME_WIN_IMAGE);
        winImage = ResourceLoader.loadImage(ImageNameConstants.WIN_IMAGE_NAME);
        gameOverImage = ResourceLoader.loadImage(ImageNameConstants.GAME_OVER_IMAGE_NAME);
        againImage = ResourceLoader.loadImage(ImageNameConstants.AGAIN_IMAGE_NAME);
        deathImage = ResourceLoader.loadImage(ImageNameConstants.DEATH_IMAGE_NAME);
    }

    public void renderGameOver(Graphics2D g, int x, int y){
        g.drawImage(deathImage, x, y, deathImage.getWidth(), deathImage.getHeight(), null);
        g.drawImage(gameOverImage, 500, 300, gameOverImage.getWidth(), gameOverImage.getHeight(), null);

    }

    public void renderLevelWin(Graphics2D g){
        g.drawImage(winImage, 440, 250, winImage.getWidth(), winImage.getHeight(), null);
    }

    public void renderGameWin(Graphics2D g){
        g.drawImage(gameWinImage, 440, 250, gameWinImage.getWidth(), gameWinImage.getHeight(), null);
    }
}
