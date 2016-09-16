package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FishUpdater {

    Player player;

    private List<SharkFish> sharkFishList;
    private List<YellowFish> yellowFishList;

    float delta;
    float beta;
    int score;

    public FishUpdater(Player player){

        this.player = player;

        delta = 0;
        beta = 0;
        score = 0;

        sharkFishList = new ArrayList<SharkFish>();
        for (int i = 0; i < 6; i++){
            delta++;
            sharkFishList.add(new SharkFish(1000 - 128,getRandomNumber(0, 800 - 128)));
        }

        yellowFishList = new ArrayList<YellowFish>();
        for(int i = 0; i < 40; i++){
            beta++;
            yellowFishList.add(new YellowFish(0, getRandomNumber(0, 800 - 48)));
        }
    }

    public int getRandomNumber(int min, int max){
        return (int)Math.floor(min + Math.random()*(max + 1 - min));
    }

    public boolean update(){
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

        for(SharkFish sharkFish: sharkFishList) {
            sharkFish.update();
            if (check(sharkFish)){
                return true;
            }
        }

        YellowFish yellowFishForRemove = null;
        for(YellowFish yellowFish: yellowFishList) {
            yellowFish.update();
            yellowFishForRemove = check(yellowFish);
            if (yellowFishForRemove != null) break;
        }
        if (yellowFishForRemove!= null) {
            yellowFishList.remove(yellowFishForRemove);
            score++;
        }

        return false;
    }


    public boolean check(SharkFish sharkFish){

        if(player.getX() > sharkFish.getX()- 14 &&
                player.getX() < sharkFish.getX() + 78 &&
                    player.getY() > sharkFish.getY() - 14 &&
                        player.getY() < sharkFish.getY() + 78 ){
            return true;
        }

        YellowFish yellowFishForRemove = null;

        for(YellowFish yellowFish: yellowFishList){

            if(yellowFish.getX() < sharkFish.getX() + 48 &&
                    yellowFish.getX() > sharkFish.getX() &&
                        yellowFish.getY() < sharkFish.getY() + 48 &&
                            yellowFish.getY() > sharkFish.getY()){
                yellowFishForRemove = yellowFish;
                break;
            }
        }

        yellowFishList.remove(yellowFishForRemove);
        return false;
    }

    public YellowFish check(YellowFish yellowFish){

        if(yellowFish.getX() > player.getX() - 28 &&
                yellowFish.getX() < player.getX() + 44 &&
                    yellowFish.getY() > player.getY() - 28 &&
                        yellowFish.getY() < player.getY() + 44 ){

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
    }

    public Integer getScore(){
        return score;
    }

}
