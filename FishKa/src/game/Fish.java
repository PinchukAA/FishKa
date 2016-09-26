package game;

import graphics.FishSprite;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Fish {
    private enum Heading{LEFT, RIGHT}

    private int x;
    private int y;

    private int speed;
    private int type;

    private int size;

    private Heading heading;
    private int direction;
    private Map<Heading, Integer> headingMap;

    private FishSprite fishSprite;
    private int spriteSize;

    public Fish(int x, int y, int speed, int type){
        this.x = x;
        this.y = y;

        this.speed = speed;

        this.type = type;

        initFish(type);
    }

    private void initFish(int type){

        heading = heading.LEFT;
        headingMap = new HashMap<Heading, Integer>();
        int i = 0;
        for (Heading h: Heading.values()) {
            headingMap.put(h, i);
            i++;
        }

        switch (type){
            case 0:
                size = 1;
                fishSprite = new FishSprite(Constants.YELLOW_LEFT_IMAGE_NAME, Constants.YELLOW_RIGHT_IMAGE_NAME);
                spriteSize = Constants.YELLOW_SPRITE_SIZE;
                break;
            case 1:
                size = 4;
                fishSprite = new FishSprite(Constants.GREEN_LEFT_IMAGE_NAME, Constants.GREEN_RIGHT_IMAGE_NAME);
                spriteSize = Constants.GREEN_SPRITE_SIZE;
                break;
            case 2:
                size = 8;
                fishSprite = new FishSprite(Constants.SHARK_LEFT_IMAGE_NAME, Constants.SHARK_RIGHT_IMAGE_NAME);
                spriteSize = Constants.SHARK_SPRITE_SIZE;
                break;
        }

        chooseDirection();
    }

    public void chooseDirection(){
        direction = (int)(Math.random()*8);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public int getType(){
        return type;
    }

    public int getSpriteSize(){
        return spriteSize;
    }

    public void update() {

        int newX = x;
        int newY = y;

        switch(direction){
            case Constants.UP:
                newY -= speed;
                break;
            case Constants.DOWN:
                newY += speed;
                break;
            case Constants.LEFT:
                newX -= speed;
                heading = Heading.LEFT;
                break;
            case Constants.RIGHT:
                newX += speed;
                heading = Heading.RIGHT;
                break;
            case Constants.UP_LEFT:
                newY -= speed;
                newX -= speed;
                heading = Heading.LEFT;
                break;
            case Constants.UP_RIGHT:
                newY -= speed;
                newX += speed;
                heading = Heading.RIGHT;
                break;
            case Constants.DOWN_LEFT:
                newY += speed;
                newX -= speed;
                heading = Heading.LEFT;
                break;
            case Constants.DOWN_RIGHT:
                newY += speed;
                newX += speed;
                heading = Heading.RIGHT;
                break;
        }

        if (newX < 0) {
            newX = 0;
        } else if (newX >= Constants.WIDTH - spriteSize) {
            newX = Constants.WIDTH - spriteSize;
        }

        if (newY < 0) {
            newY = 0;
        } else if (newY >= Constants.HEIGHT - spriteSize) {
            newY = Constants.HEIGHT - spriteSize;
        }

        x = newX;
        y = newY;
    }

    public void render(Graphics2D g) {
        fishSprite.render(g, x, y, Constants.FISH_SCALE, headingMap.get(heading));
    }

}
