package graphics;

import constants.ImageNameConstants;
import constants.gameStateConstants.GameOptionConstants;
import constants.gameStateConstants.LevelNumberConstants;
import utils.ResourceLoader;

import window.Window;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameStateRenderer {

    private Graphics2D graphics;

    private BufferedImage gameWinImage;
    private BufferedImage winImage;
    private BufferedImage deathImage;

    private BufferedImage gameLogoImage;
    private BufferedImage chooseOptionImage;

    public GameStateRenderer(){
        this.graphics = Window.getGraphics();

        gameLogoImage = ResourceLoader.loadImage(ImageNameConstants.GAME_LOGO_IMAGE);
        chooseOptionImage = ResourceLoader.loadImage(ImageNameConstants.CHOOSE_OPTION_IMAGE);
        gameWinImage = ResourceLoader.loadImage(ImageNameConstants.GAME_WIN_IMAGE);
        winImage = ResourceLoader.loadImage(ImageNameConstants.WIN_IMAGE_NAME);
        deathImage = ResourceLoader.loadImage(ImageNameConstants.DEATH_IMAGE_NAME);
    }

    public void renderNewGameState(){
        graphics.drawImage(gameLogoImage, 150, 100, gameLogoImage.getWidth(), gameLogoImage.getHeight(), null);
        Color color = new Color(0x302F30);
        graphics.setColor(color);
        graphics.setFont(new Font("Ravie", Font.BOLD, 60));
        graphics.drawString("NewGame", 490, 470);
        graphics.setFont(new Font("Ravie", Font.BOLD, 60));
        graphics.drawString("ChooseLevel", 490, 590);
        graphics.setFont(new Font("Ravie", Font.BOLD, 60));
        graphics.drawString("Quit", 490, 710);
    }

    public void renderOptionChooser(int curOption){
        switch (curOption){
            case GameOptionConstants.NEW_GAME_OPTION:
                graphics.drawImage(chooseOptionImage, 390, 416, chooseOptionImage.getWidth(),chooseOptionImage.getHeight(), null);
                break;
            case GameOptionConstants.CHOOSE_LEVEL_OPTION:
                graphics.drawImage(chooseOptionImage, 390, 536, chooseOptionImage.getWidth(), chooseOptionImage.getHeight(), null);
                break;
            case GameOptionConstants.QUIT_OPTION:
                graphics.drawImage(chooseOptionImage, 390, 656, chooseOptionImage.getWidth(), chooseOptionImage.getHeight(), null);
                break;
        }
    }

    public void renderChooseLevelState(){
        Color color = new Color(0x302F30);
        graphics.setColor(color);
        graphics.setFont(new Font("Ravie", Font.BOLD, 60));
        graphics.drawString("Level 1", 420, 60);
        graphics.drawString("Level 2", 420, 140);
        graphics.drawString("Level 3", 420, 220);
        graphics.drawString("Level 4", 420, 300);
        graphics.drawString("Level 5", 420, 380);
        graphics.drawString("Level 6", 420, 460);
        graphics.drawString("Level 7", 420, 540);
        graphics.drawString("Level 8", 420, 620);
        graphics.drawString("Level 9", 420, 700);
        graphics.drawString("Level 10", 420, 780);
    }

    public void renderLevelChooser(int selectedLevel){
        switch (selectedLevel){
            case LevelNumberConstants.LEVEL_1:
                graphics.drawImage(chooseOptionImage, 300, 5, chooseOptionImage.getWidth(),chooseOptionImage.getHeight(), null);
                break;
            case LevelNumberConstants.LEVEL_2:
                graphics.drawImage(chooseOptionImage, 300, 85, chooseOptionImage.getWidth(),chooseOptionImage.getHeight(), null);
                break;
            case LevelNumberConstants.LEVEL_3:
                graphics.drawImage(chooseOptionImage, 300, 165, chooseOptionImage.getWidth(),chooseOptionImage.getHeight(), null);
                break;
            case LevelNumberConstants.LEVEL_4:
                graphics.drawImage(chooseOptionImage, 300, 245, chooseOptionImage.getWidth(),chooseOptionImage.getHeight(), null);
                break;
            case LevelNumberConstants.LEVEL_5:
                graphics.drawImage(chooseOptionImage, 300, 325, chooseOptionImage.getWidth(),chooseOptionImage.getHeight(), null);
                break;
            case LevelNumberConstants.LEVEL_6:
                graphics.drawImage(chooseOptionImage, 300, 405, chooseOptionImage.getWidth(),chooseOptionImage.getHeight(), null);
                break;
            case LevelNumberConstants.LEVEL_7:
                graphics.drawImage(chooseOptionImage, 300, 485, chooseOptionImage.getWidth(),chooseOptionImage.getHeight(), null);
                break;
            case LevelNumberConstants.LEVEL_8:
                graphics.drawImage(chooseOptionImage, 300, 565, chooseOptionImage.getWidth(),chooseOptionImage.getHeight(), null);
                break;
            case LevelNumberConstants.LEVEL_9:
                graphics.drawImage(chooseOptionImage, 300, 645, chooseOptionImage.getWidth(),chooseOptionImage.getHeight(), null);
                break;
            case LevelNumberConstants.LEVEL_10:
                graphics.drawImage(chooseOptionImage, 300, 725, chooseOptionImage.getWidth(),chooseOptionImage.getHeight(), null);
                break;
        }
    }

    public void renderLevelNumber(int levelNumber){
        Color color = new Color(128, 56, 54, 150);
        graphics.setColor(color);
        graphics.setFont(new Font("Ravie", Font.BOLD, 60));
        graphics.drawString("LEVEL " + levelNumber, 420, 380);

        graphics.setColor(color);
        graphics.setFont(new Font("Ravie", Font.BOLD, 30));
        graphics.drawString("Press ENTER ", 445, 480);
    }

    public void renderGameWin(){
        graphics.drawImage(winImage, 440, 250, winImage.getWidth(), winImage.getHeight(), null);

        Color color = new Color(253, 235, 1, 150);
        graphics.setColor(color);
        graphics.setFont(new Font("Ravie", Font.BOLD, 30));
        graphics.drawString("Press ENTER to return to the menu", 200, 760);
    }

    public void renderGameOver(int x, int y){
        graphics.drawImage(deathImage, x - 64, y - 64, deathImage.getWidth(), deathImage.getHeight(), null);

        Color color = new Color(0, 0, 0);
        graphics.setColor(color);
        graphics.setFont(new Font("Ravie", Font.BOLD, 80));
        graphics.drawString("GAME", 440, 380);
        graphics.drawString("OVER", 440, 450);

        color = new Color(0, 0, 0, 150);
        graphics.setColor(color);
        graphics.setFont(new Font("Ravie", Font.BOLD, 30));
        graphics.drawString("Press ENTER to return to the menu", 200, 760);

    }
}
