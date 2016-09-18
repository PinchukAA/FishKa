package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FishUpdater {

    Player player;
    Game game;

    private List<SharkFish> sharkFishList;
    private List<YellowFish> yellowFishList;
    private List<GreenFish> greenFishList;

    int delta;
    int beta;
    int alpha;
    int gamma;

    int score;
    int scoreReq;

    public FishUpdater(Player player, Game game){

        this.player = player;
        this.game = game;

        delta = 0;
        beta = 0;
        alpha = 0;
        gamma = 0;

        score = 0;
        scoreReq = 20;

        sharkFishList = new ArrayList<SharkFish>();
        for (int i = 0; i < 3; i++){
            delta++;
            sharkFishList.add(new SharkFish(getRandomNumber(0, 1000 - 128),getRandomNumber(0, 800 - 128)));
        }

        yellowFishList = new ArrayList<YellowFish>();
        for(int i = 0; i < 15; i++){
            beta++;
            yellowFishList.add(new YellowFish(getRandomNumber(0, 1000 - 48), getRandomNumber(0, 800 - 48)));
        }

        greenFishList = new ArrayList<GreenFish>();
        for(int i = 0; i < 5; i++){
            alpha++;
            greenFishList.add(new GreenFish(getRandomNumber(0, 1000 - 48), getRandomNumber(0, 800 - 48)));
        }
    }

    public YellowFish generateYellowFish( ){
        return new YellowFish(getRandomNumber(0, 1000 - 48) , getRandomNumber(0, 800 - 48));
    }

    public GreenFish generateGreenFish(){
        return new GreenFish(getRandomNumber(0, 1000 - 80), getRandomNumber(0, 800 - 48));
    }

    public int getRandomNumber(int min, int max){
        return (int)Math.floor(min + Math.random()*(max + 1 - min));
    }

    public void update(){
        for(SharkFish sharkFish: sharkFishList) {
            beta++;
            if (beta > 70){
                sharkFish.chooseDirection();
                beta = 0;
            }

            sharkFish.update();
            check(sharkFish);
        }

        YellowFish yellowFishForRemove = null;

        for(YellowFish yellowFish: yellowFishList) {
            delta++;
            if (delta > 50){
                yellowFish.chooseDirection();
                delta = 0;
            }

            yellowFish.update();
            yellowFishForRemove = check(yellowFish);
            if (yellowFishForRemove != null) break;
        }

        if (yellowFishForRemove!= null) {
            score += yellowFishForRemove.getSize();
            yellowFishList.remove(yellowFishForRemove);
        }

        GreenFish greenFishForRemove = null;

        for (GreenFish greenFish: greenFishList){
            alpha++;
            if(alpha > 90){
                greenFish.chooseDirection();
                alpha = 0;
            }

            greenFish.update();
            greenFishForRemove = check(greenFish);
            if (greenFishForRemove != null) break;
        }

        if (greenFishForRemove != null){
            score += greenFishForRemove.getSize();
            greenFishList.remove(greenFishForRemove);
        }

        if(score >= scoreReq){
            player.sizeExp();
            scoreReq *= 4;
        }

        gamma++;
        if (gamma > 30){
            if(greenFishList.size() < 5) greenFishList.add(generateGreenFish());
            if(yellowFishList.size() < 15) yellowFishList.add(generateYellowFish());
            gamma = 0;
        }

    }


    public void check(SharkFish sharkFish){

        if(sharkFish.getX() < player.getX() + player.getSpriteSize()/2
                && player.getX() + player.getSpriteSize()/2 < sharkFish.getX() + 128
                && sharkFish.getY() < player.getY() + player.getSpriteSize()/2
                && player.getY() + player.getSpriteSize()/2 < sharkFish.getY() + 128){
            game.gameOver();
        }

        GreenFish greenFishForRemove = null;

        for(GreenFish greenFish: greenFishList){

            if(sharkFish.getX() < greenFish.getX() + 40
                    && greenFish.getX() + 40 < sharkFish.getX() + 128
                    && sharkFish.getY() < greenFish.getY() + 40
                    && greenFish.getY() + 40 < sharkFish.getY() + 128){
                greenFishForRemove = greenFish;
                break;
            }
        }

        greenFishList.remove(greenFishForRemove);

        YellowFish yellowFishForRemove = null;

        for(YellowFish yellowFish: yellowFishList){

            if(sharkFish.getX() < yellowFish.getX() + 24
                    && yellowFish.getX() + 24 < sharkFish.getX() + 128
                    && sharkFish.getY() < yellowFish.getY() + 24
                    && yellowFish.getY() + 24 < sharkFish.getY() + 128){
                yellowFishForRemove = yellowFish;
                break;
            }
        }

        yellowFishList.remove(yellowFishForRemove);
    }

    public GreenFish check(GreenFish greenFish){
        if (player.getSize() < greenFish.getSize()) {
            if (greenFish.getX() < player.getX() + player.getSpriteSize() / 2
                    && player.getX() + player.getSpriteSize() / 2 < greenFish.getX() + 80
                    && greenFish.getY() < player.getY() + player.getSpriteSize() / 2
                    && player.getY() + player.getSpriteSize() / 2 < greenFish.getY() + 80) {
                game.gameOver();
            }
        } else {
            if (player.getX() < greenFish.getX() + 40
                    && greenFish.getX() + 40 < player.getX() + player.getSpriteSize()
                    && player.getY() < greenFish.getY() + 40
                    && greenFish.getY() + 40 < player.getY() + player.getSpriteSize()) {
                return greenFish;
            }
        }

        YellowFish yellowFishForRemove = null;

        for(YellowFish yellowFish: yellowFishList){

            if(greenFish.getX() < yellowFish.getX() + 24
                    && yellowFish.getX() + 24 < greenFish.getX() + 128
                    && greenFish.getY() < yellowFish.getY() + 24
                    && yellowFish.getY() + 24 < greenFish.getY() + 128){
                yellowFishForRemove = yellowFish;
                break;
            }
        }

        yellowFishList.remove(yellowFishForRemove);

        return null;
    }

    public YellowFish check(YellowFish yellowFish){

        if(player.getX() < yellowFish.getX() + 24
                && yellowFish.getX() + 24 < player.getX() + player.getSpriteSize()
                && player.getY() < yellowFish.getY() + 24
                && yellowFish.getY() + 24 < player.getY() + player.getSpriteSize()) {
            return yellowFish;
        }

        return null;
    }


    public void render(Graphics2D graphics){
        for(SharkFish sharkFish: sharkFishList) {
            sharkFish.render(graphics);
        }

        for(YellowFish yellowFish: yellowFishList) {
            yellowFish.render(graphics);
        }
        for (GreenFish greenFish: greenFishList){
            greenFish.render(graphics);
        }
    }

    public Integer getScore(){
        return score;
    }

}
