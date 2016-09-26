package game;

import graphics.FishSprite;
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

    private double scale;
    private int spriteSize;

    private Heading heading;
    public FishSprite fishSprite;
    private Map<Heading, Integer> headingMap;

    private int index;


    public Player(){
        x = 350;
        y = 350;

        speed = 5;
        size = 2;

        scale = 1;
        spriteSize = 64;

        heading = heading.LEFT;
        fishSprite = new FishSprite(Constants.PLAYER_LEFT_IMAGE_NAME, Constants.PLAYER_RIGHT_IMAGE_NAME);
        headingMap = new HashMap<Heading, Integer>();

        index = 0;
        for (Heading h: Heading.values()){
            headingMap.put(h, index);
            index++;
        }
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

    public void sizeExp(){
        size *= 2;
        scale *= 1.4;
        spriteSize *= (int) scale;
    }

    public void resetSize(){
        x = 350;
        y = 350;
        size = 2;
        spriteSize = 64;
        scale = 1;
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
        fishSprite.render(g, x, y, scale, headingMap.get(heading));
    }
}
