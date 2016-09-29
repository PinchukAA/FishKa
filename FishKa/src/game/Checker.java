package game;

import java.util.Iterator;
import java.util.List;

public class Checker {
    private Player player;
    private Game game;

    private int scoreWin;
    private int scoreReq;
    private boolean f;

    public Checker(Player player, Game game){
        this.player = player;
        this.game = game;
    }

    public void initChecker(int scoreWin){
        f = true;
        this.scoreWin = scoreWin;
        scoreReq = scoreWin / 2 + 5;
    }

    public boolean checkPlayer(Fish fish){
        int fishX = fish.getX();
        int fishY = fish.getY();
        int fishSpriteSize = fish.getSpriteSize();

        int playerX = player.getX();
        int playerY = player.getY();
        int playerSpriteSize = player.getSpriteSize();

        if(fish.getSize() <= player.getSize()){
            if(playerX < fishX + fishSpriteSize / 2
                    && fishX + fishSpriteSize / 2 < playerX + playerSpriteSize
                    && playerY < fishY + fishSpriteSize / 2
                    && fishY + fishSpriteSize / 2 < playerY + playerSpriteSize)
                return true;
        } else if(fishX < playerX + playerSpriteSize / 2
                    && playerX + playerSpriteSize / 2 < fishX + fishSpriteSize
                    && fishY < playerY + playerSpriteSize / 2
                    && playerY + playerSpriteSize / 2 < fishY + fishSpriteSize) {
                System.out.print(" fX " + fishX +" fY "+ fishY + " plX " + playerX + " plY " + playerY + " type " + fish.getType());
                game.gameOver();
            }
        return false;
    }

    public boolean checkFish(Fish fish, List<Fish> fishList){
        int fishX = fish.getX();
        int fishY = fish.getY();
        int fishSpriteSize = fish.getSpriteSize();

        Iterator<Fish> iteratorFish = fishList.iterator();
        while (iteratorFish.hasNext()){
            Fish fishFromList = iteratorFish.next();

            if(!player.isGrow())
                if (fishFromList.getType() == Constants.SHARK_TYPE) continue;

            int fishFromListX = fishFromList.getX();
            int fishFromListY = fishFromList.getY();
            int fishFromListSpriteSize = fishFromList.getSpriteSize();

            if(fish.getSize() < fishFromList.getSize()){
                if(fishFromListX < fishX + fishSpriteSize / 2
                        && fishX + fishSpriteSize / 2 < fishFromListX + fishFromListSpriteSize
                        && fishFromListY < fishY + fishSpriteSize / 2
                        && fishY + fishSpriteSize / 2 < fishFromListY + fishFromListSpriteSize)
                    return true;
            }
        }
        return false;
    }


    public int getScoreReq(){
        return scoreReq;
    }

    public void checkScore(int score){
        if(score >= scoreWin){
            game.levelWin();
            return;
        }

        if(score >= scoreReq && f){
            player.grow();
            f = false;
 //           scoreReq *= 4;
        }
    }
}
