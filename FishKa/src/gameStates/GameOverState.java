package gameStates;

import game.Game;
import game.Player;
import graphics.GameStateRenderer;
import input.Input;

import java.awt.event.KeyEvent;

public class GameOverState {
    private Game game;
    private GameStateRenderer gameStateRenderer;
    private Player player;
    public  GameOverState(Game game, GameStateRenderer gameStateRenderer, Player player){
        this.game = game;
        this.gameStateRenderer = gameStateRenderer;
        this.player = player;
    }

    public void update(Input input){
        if (input.getKey(KeyEvent.VK_ENTER)){
            game.newGame();
        }
    }

    public void render(){
        gameStateRenderer.renderGameOver(player.getX(), player.getY());
    }
}
