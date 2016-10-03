package game;

import graphics.GameStateRenderer;
import input.Input;
import window.Window;

import java.awt.*;
import java.awt.event.KeyEvent;

public class OptionChooser {

    private Game game;
    private GameStateRenderer gameStateRenderer;
    private Graphics2D graphics;
    private int curOption;

    public OptionChooser(Game game, GameStateRenderer gameStateRenderer, Graphics2D graphics){
        this.game = game;
        this.gameStateRenderer = gameStateRenderer;
        this.graphics = graphics;

        curOption = 0;
    }

    public boolean update(Input input) {

        if (input.getKey(KeyEvent.VK_UP)) {
            curOption = 0;
        }
        if (input.getKey(KeyEvent.VK_DOWN)) {
            curOption = 1;
        }
        if (input.getKey(KeyEvent.VK_ENTER)){
            switch (curOption){
                case 0: return true;
                case 1:
                    Window.destroy();
                    System.exit(0);
                    break;
            }
        }
        return false;
    }

    public void render(){
        gameStateRenderer.renderNewGame(graphics);
        gameStateRenderer.renderOptionChooser(graphics, curOption);
    }
}
