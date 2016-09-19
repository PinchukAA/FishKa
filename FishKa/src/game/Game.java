package game;

import java.awt.*;

import graphics.GameSprite;
import input.Input;
import utils.LevelReader;
import window.Window;
import utils.Time;

public class Game implements Runnable {

    public static final int	WIDTH = 1200;
    public static final int	HEIGHT = 800;
    public static final String TITLE = "FishKa";
    public static final int	CLEAR_COLOR = 0xFF66CCFF;
    public static final int	NUM_BUFFERS	= 3;

    public static final float UPDATE_RATE = 60.0f;
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final long IDLE_TIME = 1;

    private boolean running;
    private Thread gameThread;
    private Graphics2D graphics;
    private Input input;

    private Player player;
    private FishUpdater fishUpdater;
    private LevelReader levelReader;

    private int scoreReq;
    private int scoreWin;

    private GameSprite gameSprite;

    public Game() {
        running = false;
        Window.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Window.getGraphics();

        input = new Input() ;
        gameSprite = new GameSprite();

        Window.addInputListener(input);

        player = new Player();

        levelReader = new LevelReader();
        fishUpdater = new FishUpdater(player, this, levelReader);

        scoreWin = levelReader.getScoreWin();
        scoreReq = levelReader.getScoreReq();
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

    public void gameWin() {
        render();
        gameSprite.renderGameWin(graphics);
        Window.swapBuffers();

        try {
            gameThread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        levelReader.nextLevel();
        scoreWin = levelReader.getScoreWin();
        scoreReq = levelReader.getScoreReq();

        fishUpdater.initFishUpdater();
    }

    public void gameOver() {
        gameSprite.renderGameOver(graphics, player.getX() - 64, player.getY() - 64);
        Window.swapBuffers();
        stop();
    }

    private void update() {
        player.update(input);
        fishUpdater.update();

        if(fishUpdater.getScore() >= scoreReq){
            player.sizeExp();
            scoreReq *= 4;
        }

        if(fishUpdater.getScore() >= scoreWin){
            gameWin();
        }
    }

    private void render() {
        Window.clear();

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("TimesRoman", Font.BOLD, 20));
        graphics.drawString("Score: " + fishUpdater.getScore().toString(), 20, 30);

        player.render(graphics);
        fishUpdater.render(graphics);

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
            delta += (elapsedTime / UPDATE_INTERVAL);
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
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (count >= Time.SECOND) {
                Window.setTitle(TITLE + " || Fps: " + fps + " | Upd: " + upd + " | Updl: " + updl);
                upd = 0;
                fps = 0;
                updl = 0;
                count = 0;
            }

        }

    }

}
