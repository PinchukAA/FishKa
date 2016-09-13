package game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import input.Input;
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
    private List<SharkFish> sharkFishList;
    private GameOver gameOver;

    public Game() {
        running = false;
        Window.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Window.getGraphics();

        input = new Input() ;
        gameOver = new GameOver();

        Window.addInputListener(input);

        player = new Player();
        sharkFishList = new ArrayList<SharkFish>();
        for (int i = 0; i < 10; i++){
            sharkFishList.add(new SharkFish());
        }
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

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cleanUp();

    }

    float delta = 0;
    float beta = 0;
    private void update() {
     //   delta++;
     //   if (delta > 70){
            for(SharkFish sharkFish: sharkFishList) {
                beta++;
                if (beta > 80){
                    sharkFish.chooseDirection();
                    beta = 0;
                }

            }
      //      delta = 0;
      //  }

        player.update(input);
        for(SharkFish sharkFish: sharkFishList) {
            sharkFish.update();
            check(sharkFish);
        }
    }

    private void render() {
        Window.clear();
        for(SharkFish sharkFish: sharkFishList) {
            sharkFish.render(graphics);
        }
        player.render(graphics);

        Window.swapBuffers();
    }

    public void check(SharkFish sharkFish){
        int plX = player.getX();
        int plY = player.getY();
        int shX = sharkFish.getX();
        int shY = sharkFish.getY();

        if(plX <= shX + 64 && plX >= shX && plY <= shY + 64 && plY >= shY ){
            Window.clear();
            player.getSprite().render(graphics, player.getX(), player.getY(), 2);
            gameOver.render(graphics);
            Window.swapBuffers();
            running = false;
        }
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

    private void cleanUp() {
        Window.destroy();
    }

}
