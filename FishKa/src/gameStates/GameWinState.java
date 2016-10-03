package gameStates;

import game.Game;
import game.Player;
import graphics.GameStateRenderer;
import input.Input;

import java.awt.event.KeyEvent;

public class GameWinState {
    private Game game;
    private GameStateRenderer gameStateRenderer;
    public  GameWinState(Game game, GameStateRenderer gameStateRenderer){
        this.game = game;
        this.gameStateRenderer = gameStateRenderer;
    }

    public void update(Input input){
        if (input.getKey(KeyEvent.VK_ENTER)){
            game.newGame();
        }
    }

    public void render(){
        gameStateRenderer.renderGameWin();
    }
}
