package game;

import constants.*;
import graphics.FishSpriteRenderer;
import input.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Player {

    private enum Heading{LEFT, RIGHT}

    private int x;
    private int y;

    private int speed;
    private int size;

    private boolean isGrow;

    private int spriteSize;

    private Heading heading;
    public FishSpriteRenderer fishSpriteRenderer;
    private Map<Heading, Integer> headingMap;

    private int index;


    public Player(){
        heading = heading.LEFT;
        fishSpriteRenderer = new FishSpriteRenderer(ImageNameConstants.PLAYER_LEFT_IMAGE_NAME_64, ImageNameConstants.PLAYER_RIGHT_IMAGE_NAME_64);
        headingMap = new HashMap<Heading, Integer>();

        index = 0;
        for (Heading h: Heading.values()){
            headingMap.put(h, index);
            index++;
        }

        initPlayer();
    }

    public void initPlayer(){
        isGrow = false;

        x = 350;
        y = 350;
        size = 1;
        speed = 5;
        spriteSize = 64;

        fishSpriteRenderer.setSprite(ImageNameConstants.PLAYER_LEFT_IMAGE_NAME_64, ImageNameConstants.PLAYER_RIGHT_IMAGE_NAME_64);
    }


    public void update(Input input) {

        int newX = x;
        int newY = y;

        if (input.getKey(KeyEvent.VK_UP)) {
            newY -= speed;
        }
        if (input.getKey(KeyEvent.VK_RIGHT)) {
            newX += speed;
            heading = Heading.RIGHT;
        }
        if (input.getKey(KeyEvent.VK_DOWN)) {
            newY += speed;
        }
        if (input.getKey(KeyEvent.VK_LEFT)) {
            newX -= speed;
            heading = Heading.LEFT;
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

    public void grow(){
        isGrow = true;

        size++;
        spriteSize = 96;
        fishSpriteRenderer.setSprite(ImageNameConstants.PLAYER_LEFT_IMAGE_NAME_96, ImageNameConstants.PLAYER_RIGHT_IMAGE_NAME_96);
    }


    public boolean isGrow(){
        return isGrow;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getSpriteSize(){
        return spriteSize;
    }

    public int getSize(){
        return size;
    }

    public void render(Graphics2D g) {
        fishSpriteRenderer.render(g, x, y, headingMap.get(heading));
    }
}
