package gameStates;

import constants.gameStateConstants.LevelNumberConstants;
import game.FishUpdater;
import game.Game;
import game.Player;
import graphics.GameStateRenderer;
import input.Input;
import utils.Level;
import utils.LevelReader;

import java.awt.event.KeyEvent;

public class NewLevelState {

    private Game game;
    private GameStateRenderer gameStateRenderer;
    private LevelReader levelReader;
    private Player player;
    private FishUpdater fishUpdater;
    private Level curLevel;

    public int curLevelNumber;

    public NewLevelState(Game game, GameStateRenderer gameStateRenderer, LevelReader levelReader, Player player, FishUpdater fishUpdater){
        this.game = game;
        this.gameStateRenderer = gameStateRenderer;
        this.levelReader = levelReader;
        this.player = player;
        this.fishUpdater = fishUpdater;

        curLevelNumber = 0;
    }

    public void setCurLevelNumber(int curLevelNumber){
        this.curLevelNumber = curLevelNumber;
    }

    public void beginNewLevel(){
        curLevelNumber++;

        if(curLevelNumber > LevelNumberConstants.NUM_OF_LEVELS) {
            game.gameWin();
            return;
        }

        player.initPlayer();

        levelReader.readLevel(curLevelNumber);
        curLevel = levelReader.getLevel();
        fishUpdater.initFishUpdater(curLevel.getScoreWin(),
                curLevel.getYellowSpeed(), curLevel.getYellowNum(),
                curLevel.getGreenSpeed(), curLevel.getGreenNum(),
                curLevel.getSharkSpeed(), curLevel.getSharkNum());

    }

    public void update(Input input){
        if (input.getKey(KeyEvent.VK_ENTER)){
            game.runGame();
        }
    }

    public void render(){
        gameStateRenderer.renderLevelNumber(curLevelNumber);
    }
}
