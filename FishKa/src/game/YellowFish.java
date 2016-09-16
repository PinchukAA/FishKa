package game;

import graphics.Sprite;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class YellowFish implements Fish {

    private static final String LEFT_IMAGE_NAME = "y48L.png";
    private static final String RIGHT_IMAGE_NAME = "y48R.png";

    private static final int SPRITE_SIZE = 48;

    private enum Heading {LEFT, RIGHT}

    private int x;
    private int y;
    private int speed;
    private Heading heading;
    private Sprite sprite;
    private int index;
    private Map<Heading, Integer> headingMap;
    private Integer direction;

    public YellowFish(int x, int y) {
        this.x = x;
        this.y = y;
        speed = 3;
        heading = heading.RIGHT;
        sprite = new Sprite(LEFT_IMAGE_NAME, RIGHT_IMAGE_NAME);
        headingMap = new HashMap<Heading, Integer>();
        direction = 0;

        index = 0;
        for (Heading h : Heading.values()) {
            headingMap.put(h, index);
            index++;
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void chooseDirection() {
        direction = (int) (Math.random() * 8);
    }

    @Override
    public void update() {
        int newX = x;
        int newY = y;

        switch (direction) {
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


    @Override
    public void render(Graphics2D g) {
        sprite.render(g, x, y, headingMap.get(heading));
    }
}
