package game;

import graphics.PlayerSprite;
import input.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Player {

    private static final int SPRITE_SIZE = 64;

    private enum Heading{LEFT, RIGHT}

    private int x;
    private int y;
    private final int speed;
    private Heading heading;
    private PlayerSprite sprite;
    private int index;
    private Map<Heading, Integer> headingMap;

    Player(){

        x = 300;
        y = 300;
        speed = 5;
        heading = heading.LEFT;
        sprite = new PlayerSprite();
        headingMap = new HashMap<Heading, Integer>();

        index = 1;

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
        int z =  headingMap.get(heading);
        sprite.render(g, x, y, z);
    }
}
