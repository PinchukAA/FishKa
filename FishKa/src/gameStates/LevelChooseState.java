package gameStates;

import constants.gameStateConstants.LevelNumberConstants;
import game.Game;
import graphics.GameStateRenderer;
import input.Input;

import java.awt.event.KeyEvent;

public class LevelChooseState {
    private Game game;
    private GameStateRenderer gameStateRenderer;
    private NewLevelState newLevelState;
    private int selectedLevel;

    public LevelChooseState(Game game, GameStateRenderer gameStateRenderer, NewLevelState newLevelState){
        this.game = game;
        this.gameStateRenderer = gameStateRenderer;
        this.newLevelState = newLevelState;

        selectedLevel = 1;
    }

    public void update(Input input) {

        if (input.getKey(KeyEvent.VK_UP)) {
            if(selectedLevel != 1) selectedLevel--;
        }
        if (input.getKey(KeyEvent.VK_DOWN)) {
            if(selectedLevel != LevelNumberConstants.NUM_OF_LEVELS) selectedLevel++;
        }
        if (input.getKey(KeyEvent.VK_ENTER)){
            newLevelState.setCurLevelNumber(selectedLevel - 1);
            game.newLevel();
        }
    }

    public void render(){
        gameStateRenderer.renderChooseLevelState();
        gameStateRenderer.renderLevelChooser(selectedLevel);
    }
}
