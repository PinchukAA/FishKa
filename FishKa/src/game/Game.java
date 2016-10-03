package game;

import constants.TimeConstants;
import constants.WindowConstants;
import constants.gameStateConstants.GameStateConstants;
import gameStates.*;
import graphics.GameInformationRenderer;
import graphics.GameStateRenderer;
import input.Input;
import utils.LevelReader;
import utils.Time;
import window.Window;

import java.awt.*;

public class Game implements Runnable {

    private boolean running;

    private Thread gameThread;

    private Input input;

    private Player player;
    private FishUpdater fishUpdater;
    private LevelReader levelReader;

    private GameStateRenderer gameStateRenderer;
    private GameInformationRenderer gameInformationRenderer;

    private int gameState;
    private NewGameState newGameState;
    private NewLevelState newLevelState;
    private GameOverState gameOverState;
    private GameWinState gameWinState;
    private LevelChooseState levelChooseState;

    private int delay;

    public Game(){
        running = false;

        Window.create(WindowConstants.WIDTH, WindowConstants.HEIGHT, WindowConstants.TITLE, WindowConstants.CLEAR_COLOR, WindowConstants.NUM_BUFFERS);

        input = new Input();
        gameStateRenderer = new GameStateRenderer();

        Window.addInputListener(input);

        player = new Player();

        levelReader = new LevelReader();
        fishUpdater = new FishUpdater(player, this);

        gameStateRenderer = new GameStateRenderer();
        gameInformationRenderer = new GameInformationRenderer();

        gameState = GameStateConstants.NEW_GAME_STATE;

        newGameState = new NewGameState(this, gameStateRenderer);
        newLevelState = new NewLevelState(this, gameStateRenderer, levelReader, player, fishUpdater);
        gameOverState = new GameOverState(this, gameStateRenderer, player);
        gameWinState = new GameWinState(this, gameStateRenderer);
        levelChooseState = new LevelChooseState(this, gameStateRenderer, newLevelState);
        delay = 0;
    }

    public synchronized void start(){
        if (running)
            return;

        play();
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop(){
        if (!running)
            return;
        running = false;
    }

    public synchronized void play(){
        if (running)
            return;
        running = true;
    }

    public void newGame(){
        gameState = GameStateConstants.NEW_GAME_STATE;
    }

    public void chooseLevel(){
        gameState = GameStateConstants.LEVEL_CHOOSE_STATE;
    }

    public void runGame(){
        gameState = GameStateConstants.GAME_RUN_STATE;
    }

    public void newLevel(){
        gameState = GameStateConstants.NEW_LEVEL_STATE;
        newLevelState.beginNewLevel();
    }

    public void gameWin(){
        gameState = GameStateConstants.GAME_WIN_STATE;
    }

    public void gameOver(){
        gameState = GameStateConstants.GAME_OVER_STATE;
    }

    public void quitGame(){
        stop();
        Window.destroy();
        System.exit(0);
    }

    public void update(){
        switch (gameState){
            case GameStateConstants.NEW_GAME_STATE:
                if(delay > 3) {
                    newGameState.update(input);
                    delay = 0;
                } delay++;
                break;

            case GameStateConstants.LEVEL_CHOOSE_STATE:
                if(delay > 3) {
                    levelChooseState.update(input);
                    delay = 0;
                } delay++;
                break;

            case GameStateConstants.GAME_RUN_STATE:
                player.update(input);
                fishUpdater.update();
                break;

            case GameStateConstants.NEW_LEVEL_STATE:
                newLevelState.update(input);
                break;

            case GameStateConstants.GAME_WIN_STATE:
                gameWinState.update(input);
                break;

            case GameStateConstants.GAME_OVER_STATE:
                gameOverState.update(input);
                break;
        }
    }

    public void render(){
        Window.clear();

        switch (gameState){
            case GameStateConstants.NEW_GAME_STATE:
                newGameState.render();
                break;

            case GameStateConstants.LEVEL_CHOOSE_STATE:
                levelChooseState.render();
                break;

            case GameStateConstants.GAME_RUN_STATE:
                gameInformationRenderer.scoreRender(fishUpdater.getScore(), fishUpdater.getScoreWin());
                player.render();
                fishUpdater.render();
                break;

            case GameStateConstants.NEW_LEVEL_STATE:
                newLevelState.render();
                break;

            case GameStateConstants.GAME_WIN_STATE:
                gameInformationRenderer.scoreRender(fishUpdater.getScore(), fishUpdater.getScoreWin());
                player.render();
                fishUpdater.render();
                gameWinState.render();
                break;

            case GameStateConstants.GAME_OVER_STATE:
                gameInformationRenderer.scoreRender(fishUpdater.getScore(), fishUpdater.getScoreWin());
                fishUpdater.render();
                gameOverState.render();
                break;
        }

        Window.swapBuffers();
    }

    public void run() {

        int fps = 0;
        int upd = 0;
        int updl = 0;

        long count = 0;

        float delta = 0;

        long lastTime = Time.get();
        while (running) {
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;

            count += elapsedTime;

            boolean render = false;
            delta += (elapsedTime / TimeConstants.UPDATE_INTERVAL);
            while (delta > 1) {
                update();
                upd++;
                delta--;
                if (render) {
                    updl++;
                } else {
                    render = true;
                }
            }

            if (render) {
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(TimeConstants.IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (count >= TimeConstants.SECOND) {
                gameInformationRenderer.setTitle(fps, upd, updl);
                upd = 0;
                fps = 0;
                updl = 0;
                count = 0;
            }

        }

    }

}
