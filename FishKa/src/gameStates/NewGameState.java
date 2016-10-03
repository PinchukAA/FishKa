package gameStates;

import constants.gameStateConstants.*;
import game.Game;
import graphics.GameStateRenderer;
import input.Input;
import java.awt.event.KeyEvent;

public class NewGameState {

    private Game game;
    private GameStateRenderer gameStateRenderer;
    private int curOption;
    private int optionLength;

    public NewGameState(Game game, GameStateRenderer gameStateRenderer){
        this.game = game;
        this.gameStateRenderer = gameStateRenderer;

        curOption = 0;
        optionLength = 2;
    }

    public void update(Input input) {

        if (input.getKey(KeyEvent.VK_UP)) {
            if(curOption != 0) curOption--;
        }
        if (input.getKey(KeyEvent.VK_DOWN)) {
            if(curOption != optionLength) curOption++;

        }
        if (input.getKey(KeyEvent.VK_ENTER)){
            switch (curOption){
                case GameOptionConstants.NEW_GAME_OPTION:
                    game.newLevel();
                    break;
                case GameOptionConstants.CHOOSE_LEVEL_OPTION:
                    game.chooseLevel();
                    break;
                case GameOptionConstants.QUIT_OPTION:
                    game.quitGame();
                    break;
            }
        }
    }

    public void render(){
        gameStateRenderer.renderNewGameState();
        gameStateRenderer.renderOptionChooser(curOption);
    }
}
