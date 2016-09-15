package game;

import java.awt.*;

public interface Fish {
    int getX();
    int getY();

    void chooseDirection();
    void update();
    void render(Graphics2D g);
}
