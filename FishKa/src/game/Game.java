package game;

import java.awt.*;

import graphics.GameSprite;
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

    private GameSprite gameSprite;

    public Game() {
        running = false;
        Window.create(Constants.WIDTH, Constants.HEIGHT, Constants.TITLE, Constants.CLEAR_COLOR, Constants.NUM_BUFFERS);
        graphics = Window.getGraphics();

        input = new Input();
        gameSprite = new GameSprite();

        Window.addInputListener(input);

        player = new Player();

        levelReader = new LevelReader(this);
        fishUpdater = new FishUpdater(player, this, levelReader);
    }

    public synchronized void start() {

        if (running)
            return;

        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {

        if (!running)
            return;

        running = false;
    }

    public void gameWin(){
        running = false;
        Window.clear();
        gameSprite.renderGameWin(graphics);
        Window.swapBuffers();
    }

    public void levelWin() {
        running = false;
        Window.clear();
        gameSprite.renderLevelWin(graphics);
        scoreRender();
        Window.swapBuffers();

        nextLevel();
    }

    public void gameOver() {
        gameSprite.renderGameOver(graphics, player.getX() - player.getSpriteSize(), player.getY() - player.getSpriteSize());
        Window.swapBuffers();
        stop();
    }

    public void nextLevel(){
        Window.swapBuffers();

        player.initPlayer();
        levelReader.nextLevel();
        fishUpdater.initFishUpdater();

        pauseGame();
    }

    public void pauseGame(){
        long lastTime = Time.get();
        while (Time.get() < lastTime + 2000000000l)
            running = false;
        running = true;
    }

    private void update() {
        player.update(input);
        fishUpdater.update();
    }

    private void render() {
        Window.clear();

        scoreRender();

        player.render(graphics);
        fishUpdater.render(graphics);

        Window.swapBuffers();
    }

    private void scoreRender(){
        graphics.setFont(new Font("TimesRoman", Font.BOLD, 20));
        graphics.setColor(Color.BLACK);
        graphics.drawString("Score: " + fishUpdater.getScore() + " / " + fishUpdater.getScoreWin(), 20, 30);
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
            delta += (elapsedTime / Constants.UPDATE_INTERVAL);
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
                    Thread.sleep(Constants.IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (count >= Time.SECOND) {
                Window.setTitle(Constants.TITLE + " || Fps: " + fps + " | Upd: " + upd + " | Updl: " + updl);
                upd = 0;
                fps = 0;
                updl = 0;
                count = 0;
            }

        }

    }

}
