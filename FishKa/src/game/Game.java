package game;

import java.awt.*;

import constants.*;
import graphics.GameStateRenderer;
import input.Input;
import utils.LevelReader;
import window.Window;
import utils.Time;

public class Game implements Runnable {

    private boolean running;
    private Thread gameThread;
    private Graphics2D graphics;
    private Input input;

    private Player player;
    private FishUpdater fishUpdater;
    private LevelReader levelReader;

    private GameStateRenderer gameStateRenderer;

    private OptionChooser optionChooser;

    private boolean isOptionSelected;

    public Game(){
        running = false;
        Window.create(WindowConstants.WIDTH, WindowConstants.HEIGHT, WindowConstants.TITLE, WindowConstants.CLEAR_COLOR, WindowConstants.NUM_BUFFERS);
        graphics = Window.getGraphics();

        input = new Input();
        gameStateRenderer = new GameStateRenderer();

        Window.addInputListener(input);

        player = new Player();

        levelReader = new LevelReader(this);
        fishUpdater = new FishUpdater(player, this, levelReader);

        optionChooser = new OptionChooser(this, gameStateRenderer, graphics);
        isOptionSelected = false;
    }

    public synchronized void start(){

        if (running)
            return;

        running = true;
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

    public void gameWin(){
        Window.clear();
        gameStateRenderer.renderGameWin(graphics);
        Window.swapBuffers();
        Time.pauseGame(2, this);
        isOptionSelected = false;
    }

    public void levelWin(){
        Window.clear();
        Window.scoreRender(graphics, fishUpdater.getScore(), fishUpdater.getScoreWin());
        Window.swapBuffers();

        if(levelReader.getLevelNumber() < levelReader.numLevels) nextLevel();
        else {
            stop();
            gameWin();
        }
    }

    public void gameOver(){
        gameStateRenderer.renderGameOver(graphics, player.getX() - player.getSpriteSize(), player.getY() - player.getSpriteSize());
        Window.swapBuffers();
    }

    public void nextLevel(){
        Window.clear();
        Window.renderLevelNumber(levelReader.getLevelNumber() + 1, graphics);
        Window.swapBuffers();

        player.initPlayer();
        levelReader.nextLevel();
        fishUpdater.initFishUpdater();

        Time.pauseGame(3, this);
    }

    private void update() {
        if (!isOptionSelected){
            isOptionSelected = optionChooser.update(input);
        } else {
            player.update(input);
            fishUpdater.update();
        }
    }

    private void render() {
        Window.clear();
        if (!isOptionSelected){
            optionChooser.render();
        } else {

            Window.scoreRender(graphics, fishUpdater.getScore(), fishUpdater.getScoreWin());

            player.render(graphics);
            fishUpdater.render(graphics);
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
                Window.setTitle(fps, upd, updl);
                upd = 0;
                fps = 0;
                updl = 0;
                count = 0;
            }

        }

    }

}
