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
            if(!player.isGrow())
                if(fish.getType() == Constants.SHARK_TYPE ) continue;

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

        checker.checkScore(score);
    }

    public void render(Graphics2D graphics){
        for(Fish fish: fishList){
            if(!player.isGrow())
                if(fish.getType() == Constants.SHARK_TYPE ) {
                    if(checker.getScoreReq() <= score + 3 && checker.getScoreReq() >= score)
                        renderSharkWarning(graphics, fish);
                    continue;
                }

            fish.render(graphics);
        }

    }

    public void renderSharkWarning(Graphics2D graphics, Fish fish){
        Color color = new Color(128, 56, 54, 150);
        graphics.setColor(color);
        graphics.fillRect(fish.getX(), fish.getY(), Constants.SHARK_SPRITE_SIZE, Constants.SHARK_SPRITE_SIZE);

        graphics.setFont(new Font("TimesRoman", Font.BOLD, 40));
        graphics.drawString("SHARKS ARE COMING!!!", 380, 380);
    }


    public Integer getScore(){
        return score;
    }
    public Integer getScoreWin(){
        return scoreWin;
    }
}
