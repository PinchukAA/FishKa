package game;

import graphics.FishSprite;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SharkFish implements Fish{
    private static final String LEFT_IMAGE_NAME = "sh128L.png";
    private static final String RIGHT_IMAGE_NAME = "sh128R.png";

    private static final int SPRITE_SIZE = 128;

    private enum Heading{LEFT, RIGHT}

    private int x;
    private int y;

    private int speed;
    private int size;

    private Heading heading;
    private FishSprite fishSprite;
    private Map<Heading, Integer> headingMap;

    private int index;

    private Integer direction;

    public SharkFish(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;

        size = 8;

        heading = heading.LEFT;
        fishSprite = new FishSprite(LEFT_IMAGE_NAME, RIGHT_IMAGE_NAME);
        headingMap = new HashMap<Heading, Integer>();

        direction = 0;

        index = 0;
        for (Heading h: Heading.values()) {
            headingMap.put(h, index);
            index++;
        }
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

    @Override
    public int getSize() {
        return size;
    }

    public void update() {

        int newX = x;
        int newY = y;

        switch(direction){
            case 0:
                newY -= speed;
                break;
            case 1:
                newY += speed;
                break;
            case 2:
                newX -= speed;
                heading = Heading.LEFT;
                break;
            case 3:
                newX += speed;
                heading = Heading.RIGHT;
                break;
            case 4:
                newY -= speed;
                newX -= speed;
                heading = Heading.LEFT;
                break;
            case 5:
                newY -= speed;
                newX += speed;
                heading = Heading.RIGHT;
                break;
            case 6:
                newY += speed;
                newX -= speed;
                heading = Heading.LEFT;
                break;
            case 7:
                newY += speed;
                newX += speed;
                heading = Heading.RIGHT;
                break;
        }

        if (newX < 0) {
            newX = 0;
        } else if (newX >= Game.WIDTH - SPRITE_SIZE) {
            newX = Game.WIDTH - SPRITE_SIZE;
        }

        if (newY < 0) {
            newY = 0;
        } else if (newY >= Game.HEIGHT - SPRITE_SIZE) {
            newY = Game.HEIGHT - SPRITE_SIZE;
        }

        x = newX;
        y = newY;
    }

    public void render(Graphics2D g) {
        fishSprite.render(g, x, y, 1, headingMap.get(heading));
    }
}
