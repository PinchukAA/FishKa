package game;

import constants.FishTypeConstants;
import utils.LevelReader;

import java.util.Iterator;
import java.util.List;

public class FishUpdater {

    private Player player;
    private Game game;
    private FishFactory fishFactory;
    private Checker checker;

    public List<Fish> fishList;

    private int choosingDirectionDelay;
    private int fishCreationDelay;

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


    public FishUpdater(Player player, Game game){

        this.player = player;
        this.game = game;

        fishFactory = new FishFactory(player);
        checker = new Checker(player, game);
    }

    public void initFishUpdater(int scoreWin, int yellowSpeed, int yellowNum, int greenSpeed, int greenNum, int sharkSpeed, int sharkNum){
        choosingDirectionDelay = 0;
        fishCreationDelay = 0;

        score = 0;

        this.scoreWin = scoreWin;

        this.yellowSpeed = yellowSpeed;
        this.yellowNum = yellowNum;
        curYellowNum = yellowNum;

        this.greenSpeed = greenSpeed;
        this.greenNum = greenNum;
        curGreenNum = greenNum;

        this.sharkSpeed = sharkSpeed;
        this.sharkNum = sharkNum;

        fishList = fishFactory.createFishList(yellowNum, yellowSpeed, greenNum, greenSpeed, sharkNum, sharkSpeed);

        checker.initChecker(scoreWin);
    }


    public void update(){

        Iterator<Fish> iteratorFish = fishList.iterator();
        while (iteratorFish.hasNext()){
            Fish fish = iteratorFish.next();
            if(!player.isGrow())
                if(fish.getType() == FishTypeConstants.SHARK_TYPE ) continue;

            choosingDirectionDelay++;
            if (choosingDirectionDelay > 70){
                fish.chooseDirection();
                choosingDirectionDelay = 0;
            }

            fish.update();
            if(checker.checkPlayer(fish)) {
                if(checker.isGameOver()) return;

                score += fish.getSize();
                switch (fish.getType()){
                    case FishTypeConstants.YELLOW_TYPE:
                        curYellowNum--;
                        break;
                    case FishTypeConstants.GREEN_TYPE:
                        curGreenNum--;
                        break;
                }
                iteratorFish.remove();

            } else if(checker.checkFish(fish, fishList)){
                switch (fish.getType()){
                    case FishTypeConstants.YELLOW_TYPE:
                        curYellowNum--;
                        break;
                    case FishTypeConstants.GREEN_TYPE:
                        curGreenNum--;
                        break;
                }
                iteratorFish.remove();
            }
        }

        fishCreationDelay++;
        if (fishCreationDelay > 30){
            if(curYellowNum < yellowNum) {
                fishList.add(fishFactory.createYellowFish(yellowSpeed));
                curYellowNum++;
            }

            if(curGreenNum < greenNum) {
                fishList.add(fishFactory.createGreenFish(greenSpeed));
                curGreenNum++;
            }
            fishCreationDelay = 0;
        }

        checker.checkScore(score);
    }

    public void render(){
        for(Fish fish: fishList){
            if(!player.isGrow())
                if(fish.getType() == FishTypeConstants.SHARK_TYPE) {
                    if(checker.getScoreReq() <= score + 3 && checker.getScoreReq() >= score)
                        fish.renderSharkWarning();
                    continue;
                }

            fish.render();
        }
    }

    public Integer getScore(){
        return score;
    }

    public Integer getScoreWin(){
        return scoreWin;
    }
}
