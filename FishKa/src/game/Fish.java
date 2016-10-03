package game;

import constants.FishTypeConstants;
import constants.FishDirectionConstants;
import constants.ImageNameConstants;
import constants.WindowConstants;
import graphics.FishSpriteRenderer;

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

    private FishSpriteRenderer fishSpriteRenderer;
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
                fishSpriteRenderer = new FishSpriteRenderer(ImageNameConstants.YELLOW_LEFT_IMAGE_NAME, ImageNameConstants.YELLOW_RIGHT_IMAGE_NAME);
                spriteSize = FishTypeConstants.YELLOW_SPRITE_SIZE;
                break;
            case 1:
                size = 2;
                fishSpriteRenderer = new FishSpriteRenderer(ImageNameConstants.GREEN_LEFT_IMAGE_NAME, ImageNameConstants.GREEN_RIGHT_IMAGE_NAME);
                spriteSize = FishTypeConstants.GREEN_SPRITE_SIZE;
                break;
            case 2:
                size = 3;
                fishSpriteRenderer = new FishSpriteRenderer(ImageNameConstants.SHARK_LEFT_IMAGE_NAME, ImageNameConstants.SHARK_RIGHT_IMAGE_NAME);
                spriteSize = FishTypeConstants.SHARK_SPRITE_SIZE;
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
            case FishDirectionConstants.UP:
                newY -= speed;
                break;
            case FishDirectionConstants.DOWN:
                newY += speed;
                break;
            case FishDirectionConstants.LEFT:
                newX -= speed;
                heading = Heading.LEFT;
                break;
            case FishDirectionConstants.RIGHT:
                newX += speed;
                heading = Heading.RIGHT;
                break;
            case FishDirectionConstants.UP_LEFT:
                newY -= speed;
                newX -= speed;
                heading = Heading.LEFT;
                break;
            case FishDirectionConstants.UP_RIGHT:
                newY -= speed;
                newX += speed;
                heading = Heading.RIGHT;
                break;
            case FishDirectionConstants.DOWN_LEFT:
                newY += speed;
                newX -= speed;
                heading = Heading.LEFT;
                break;
            case FishDirectionConstants.DOWN_RIGHT:
                newY += speed;
                newX += speed;
                heading = Heading.RIGHT;
                break;
        }

        if (newX < 0) {
            newX = 0;
        } else if (newX >= WindowConstants.WIDTH - spriteSize) {
            newX = WindowConstants.WIDTH - spriteSize;
        }

        if (newY < 0) {
            newY = 0;
        } else if (newY >= WindowConstants.HEIGHT - spriteSize) {
            newY = WindowConstants.HEIGHT - spriteSize;
        }

        x = newX;
        y = newY;
    }

    public void render(Graphics2D g) {
        fishSpriteRenderer.render(g, x, y, headingMap.get(heading));
    }

}
