package game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
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
    private List<YellowFish> yellowFishList;

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
        for (int i = 0; i < 5; i++){
            sharkFishList.add(new SharkFish(getRandomNumber(),getRandomNumber()));
        }

        yellowFishList = new ArrayList<YellowFish>();
        for(int i = 0; i < 40; i++){
            yellowFishList.add(new YellowFish(getRandomNumber(), getRandomNumber()));
        }
    }

    public int getRandomNumber(){
       return (int)(Math.random()*800);
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
/*
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cleanUp();
*/
    }

    float delta = 0;
    float beta = 0;
    private void update() {
        for(SharkFish sharkFish: sharkFishList) {
            beta++;
            if (beta > 70){
                sharkFish.chooseDirection();
                beta = 0;
            }
        }

        for(YellowFish yellowFish: yellowFishList) {
            delta++;
            if (delta > 50){
                yellowFish.chooseDirection();
                delta = 0;
            }
        }

        player.update(input);
        for(SharkFish sharkFish: sharkFishList) {
            sharkFish.update();
            sharkFishCheck(sharkFish, yellowFishList);
        }

        YellowFish yellowFishForRemove = null;
        for(YellowFish yellowFish: yellowFishList) {
            yellowFish.update();
            yellowFishForRemove = yellowFishCheck(yellowFish);
            if (yellowFishForRemove != null) break;
        }
        if (yellowFishForRemove!= null)
        yellowFishList.remove(yellowFishForRemove);
    }

    private void render() {
        Window.clear();
        for(SharkFish sharkFish: sharkFishList) {
            sharkFish.render(graphics);
        }

        player.render(graphics);

        for(YellowFish yellowFish: yellowFishList) {
            yellowFish.render(graphics);
        }

        Window.swapBuffers();
    }

    public void sharkFishCheck(SharkFish sharkFish, List<YellowFish> yellowFishList){
        int plX = player.getX();
        int plY = player.getY();
        int shX = sharkFish.getX();
        int shY = sharkFish.getY();

        if(plX < shX + 64 && plX > shX && plY < shY + 64 && plY > shY ){
//        if(plX > shX - 34 && plX < shX + 98 && plY > shY - 34 && plY < shY + 98 ){
            Window.clear();
            gameOver.render(graphics, plX, plY);
            Window.swapBuffers();
            stop();
        }

        YellowFish yellowFishForRemove = null;
        for(YellowFish yellowFish: yellowFishList){
            int yX = yellowFish.getX();
            int yY = yellowFish.getY();

            if(yX < shX + 48 && yX > shX && yY < shY + 48 && yY > shY ){
                yellowFishForRemove = yellowFish;
                break;
            }
        }
        yellowFishList.remove(yellowFishForRemove);
    }

    public YellowFish yellowFishCheck(YellowFish yellowFish){
        int plX = player.getX();
        int plY = player.getY();
        int yX = yellowFish.getX();
        int yY = yellowFish.getY();


   //     if(yX <= plX + 16 && yX >= plX && yY <= plY + 16 && yY >= plY ){
        if(yX > plX - 28 && yX < plX + 44 && yY > plY - 28 && yY < plY + 44 ){
            return yellowFish;
        }

        if(yX > plX - 28 && yX < plX + 44 && yY > plY - 28 && yY < plY + 44 ){
            return yellowFish;
        }

        return null;
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
/*
    private void cleanUp() {
        Window.destroy();
    }
*/
}
