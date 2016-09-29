package graphics;

import utils.ResourceLoader;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameSprite {
    private static final String GAME_WIN_IMAGE = "game_win.png";
    private static final String WIN_IMAGE_NAME = "win.png";
    private static final String GAME_OVER_IMAGE_NAME = "game_over.png";
    private static final String AGAIN_IMAGE_NAME = "again.png";
    private static final String DEATH_IMAGE_NAME = "death.png";

    private BufferedImage gameWinImage;
    private BufferedImage winImage;
    private BufferedImage gameOverImage;
    private BufferedImage againImage;
    private BufferedImage deathImage;

    public GameSprite(){

        gameWinImage = ResourceLoader.loadImage(GAME_WIN_IMAGE);
        winImage = ResourceLoader.loadImage(WIN_IMAGE_NAME);
        gameOverImage = ResourceLoader.loadImage(GAME_OVER_IMAGE_NAME);
        againImage = ResourceLoader.loadImage(AGAIN_IMAGE_NAME);
        deathImage = ResourceLoader.loadImage(DEATH_IMAGE_NAME);
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
