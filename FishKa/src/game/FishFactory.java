package game;

import java.util.ArrayList;
import java.util.List;

public class FishFactory {

    private List fishList;
    private Player player;

    public FishFactory(Player player){
        this.player = player;
    }

    public Fish createYellowFish(int speed) {
        return new Fish(generateX(Constants.YELLOW_SPRITE_SIZE), generateY(Constants.YELLOW_SPRITE_SIZE), speed, Constants.YELLOW_TYPE);
    }

    public Fish createGreenFish(int speed) {
        return new Fish(generateX(Constants.GREEN_SPRITE_SIZE), generateY(Constants.GREEN_SPRITE_SIZE), speed, Constants.GREEN_TYPE);
    }

    public Fish createSharkFish(int speed) {
        return new Fish(generateX(Constants.SHARK_SPRITE_SIZE), generateY(Constants.SHARK_SPRITE_SIZE), speed, Constants.SHARK_TYPE);
    }

    public List createFishList(int yellowNum, int yellowSpeed, int greenNum, int greenSpeed, int sharkNum, int sharkSpeed) {
        fishList = new ArrayList<Fish>();
        for(int i = 0; i < yellowNum; i++){
            fishList.add(createYellowFish(yellowSpeed));
        }

        for(int i = 0; i < greenNum; i++){
            fishList.add(createGreenFish(greenSpeed));
        }

        for(int i = 0; i < sharkNum; i++){
            fishList.add(createSharkFish(sharkSpeed));
        }

        return fishList;
    }

    public int generateX(int fishSpriteSize){
        int playerX = player.getX();
        int playerSpriteSize = player.getSpriteSize();

        int playerCenter = playerX + (int) playerSpriteSize / 2;

        int x = generateRandomNumber(0, Constants.WIDTH - fishSpriteSize);

        while (Math.abs(x + fishSpriteSize / 2 - playerCenter) < 100)  x = generateRandomNumber(0, Constants.WIDTH - fishSpriteSize);
        return x;
    }

    public int generateY(int fishSpriteSize){
        int playerY = player.getY();
        int playerSpriteSize = player.getSpriteSize();

        int playerCenter = playerY + (int) playerSpriteSize / 2;

        int y = generateRandomNumber(0, Constants.HEIGHT - fishSpriteSize);

        while (Math.abs(y + fishSpriteSize / 2 - playerCenter) < 100)  y = generateRandomNumber(0, Constants.HEIGHT - fishSpriteSize);
        return y;
    }

    public int generateRandomNumber(int min, int max) {
        return (int)Math.floor(min + Math.random()*(max + 1 - min));
    }

}
