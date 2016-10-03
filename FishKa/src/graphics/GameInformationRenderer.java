package graphics;

import window.Window;

import java.awt.*;

public class GameInformationRenderer {
    private Graphics2D graphics;

    public GameInformationRenderer(){
        this.graphics = Window.getGraphics();
    }

    public void setTitle(int fps, int upd, int updl) {
        Window.setTitle(constants.WindowConstants.TITLE + " || Fps: " + fps + " | Upd: " + upd + " | Updl: " + updl);
    }

    public void scoreRender(int score, int scoreWin){
        graphics.setFont(new Font("TimesRoman", Font.BOLD, 20));
        graphics.setColor(Color.BLACK);
        graphics.drawString("Score: " + score + " / " + scoreWin, 20, 30);
    }
}
