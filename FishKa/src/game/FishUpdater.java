package game;

import utils.LevelReader;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class FishUpdater {

    private Player player;
    private Game game;
    private LevelReader levelReader;
    private FishFactory fishFactory;
    private Checker checker;

    public List<Fish> fishList;

    private int delta;
    private int gamma;

    private int score;
    private int scoreWin;

    private int yellowSpeed;
    private int greenSpeed;
    private int sharkSpeed;

    private int yellowNum;
    private int greenNum;
    private int sharkNum;

    private int curYellowNum;
    private int curGreenNum;


    public FishUpdater(Player player, Game game, LevelReader levelReader){

        this.player = player;
        this.game = game;

        this.levelReader = levelReader;

        fishFactory = new FishFactory(player);
        checker = new Checker(player, game);

        initFishUpdater();
    }

    public void initFishUpdater(){
        delta = 0;
        gamma = 0;

        score = 0;
        scoreWin = levelReader.getLevel().getScoreWin();

        yellowSpeed = levelReader.getLevel().getYellowSpeed();
        greenSpeed = levelReader.getLevel().getGreenSpeed();
        sharkSpeed = levelReader.getLevel().getSharkSpeed();

        yellowNum = levelReader.getLevel().getYellowNum();
        curYellowNum = yellowNum;
        greenNum = levelReader.getLevel().getGreenNum();
        curGreenNum = greenNum;
        sharkNum = levelReader.getLevel().getSharkNum();

        fishList = fishFactory.createFishList(yellowNum, yellowSpeed, greenNum, greenSpeed, sharkNum, sharkSpeed);

        checker.initChecker(scoreWin);
    }
    public void update(){

        Iterator<Fish> iteratorFish = fishList.iterator();
        while (iteratorFish.hasNext()){
            Fish fish = iteratorFish.next();

            delta++;
            if (delta > 70){

                fish.chooseDirection();
                delta = 0;
            }

            fish.update();
            if(checker.checkPlayer(fish)) {

                score += fish.getSize();
                switch (fish.getType()){
                    case Constants.YELLOW_TYPE:
                        curYellowNum--;
                        break;
                    case Constants.GREEN_TYPE:
                        curGreenNum--;
                        break;
                }
                iteratorFish.remove();

            } else if(checker.checkFish(fish, fishList)){
                switch (fish.getType()){
                    case Constants.YELLOW_TYPE:
                        curYellowNum--;
                        break;
                    case Constants.GREEN_TYPE:
                        curGreenNum--;
                        break;
                }
                iteratorFish.remove();
            }

            checker.checkScore(score);
        }

        gamma++;
        if (gamma > 30){
            if(curYellowNum < yellowNum) {
                fishList.add(fishFactory.createYellowFish(yellowSpeed));
                curYellowNum++;
            }
            if(curGreenNum < greenNum) {
                fishList.add(fishFactory.createGreenFish(greenSpeed));
                curGreenNum++;
            }
            gamma = 0;
        }

    }

    public void render(Graphics2D graphics){
        for(Fish fish: fishList){
            fish.render(graphics);
        }

    }


    public Integer getScore(){
        return score;
    }
    public Integer getScoreWin(){
        return scoreWin;
    }
}
