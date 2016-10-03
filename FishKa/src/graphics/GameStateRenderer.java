package graphics;

import utils.ResourceLoader;
import java.awt.*;
import java.awt.image.BufferedImage;
import constants.ImageNameConstants;

public class GameStateRenderer {

    private BufferedImage gameWinImage;
    private BufferedImage winImage;
    private BufferedImage gameOverImage;
    private BufferedImage againImage;
    private BufferedImage deathImage;

    private BufferedImage gameLogoImage;
    private BufferedImage newGameOptionImage;
    private BufferedImage quitGameOptionImage;
    private BufferedImage chooseOptionImage;

    public GameStateRenderer(){

        gameLogoImage = ResourceLoader.loadImage(ImageNameConstants.GAME_LOGO_IMAGE);
        newGameOptionImage = ResourceLoader.loadImage(ImageNameConstants.NEW_GAME_OPTION_IMAGE);
        quitGameOptionImage = ResourceLoader.loadImage(ImageNameConstants.QUIT_OPTION_IMAGE);
        chooseOptionImage = ResourceLoader.loadImage(ImageNameConstants.CHOOSE_OPTION_IMAGE);

        gameWinImage = ResourceLoader.loadImage(ImageNameConstants.GAME_WIN_IMAGE);
        winImage = ResourceLoader.loadImage(ImageNameConstants.WIN_IMAGE_NAME);
        gameOverImage = ResourceLoader.loadImage(ImageNameConstants.GAME_OVER_IMAGE_NAME);
        againImage = ResourceLoader.loadImage(ImageNameConstants.AGAIN_IMAGE_NAME);
        deathImage = ResourceLoader.loadImage(ImageNameConstants.DEATH_IMAGE_NAME);
    }

    public void renderNewGame(Graphics2D g){
        g.drawImage(gameLogoImage, 150, 100, gameLogoImage.getWidth(), gameLogoImage.getHeight(), null);
        g.drawImage(newGameOptionImage, 490, 396, newGameOptionImage.getWidth(), newGameOptionImage.getHeight(), null);
        g.drawImage(quitGameOptionImage, 490, 516, quitGameOptionImage.getWidth(), quitGameOptionImage.getHeight(), null);
    }

    public void renderOptionChooser(Graphics2D g, int c){
        switch (c){
            case 0:
                g.drawImage(chooseOptionImage, 390, 416, chooseOptionImage.getWidth(),chooseOptionImage.getHeight(), null);
                break;
            case 1:
                g.drawImage(chooseOptionImage, 390, 536, chooseOptionImage.getWidth(), chooseOptionImage.getHeight(), null);

        }
    }

    public void renderLevelWin(Graphics2D g){
        g.drawImage(winImage, 440, 250, winImage.getWidth(), winImage.getHeight(), null);
    }

    public void renderGameWin(Graphics2D g){
        g.drawImage(winImage, 440, 250, winImage.getWidth(), winImage.getHeight(), null);
    }

    public void renderGameOver(Graphics2D g, int x, int y){
        g.drawImage(deathImage, x, y, deathImage.getWidth(), deathImage.getHeight(), null);
        g.drawImage(gameOverImage, 500, 300, gameOverImage.getWidth(), gameOverImage.getHeight(), null);
    }
}
