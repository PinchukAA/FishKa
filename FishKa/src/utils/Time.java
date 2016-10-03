package utils;


import constants.TimeConstants;
import game.Game;

public class Time {

    public static long get() {
        return System.nanoTime();
    }

    public static void pauseGame(int sec, Game game){
        long lastTime = get();
        while (get() < lastTime + sec * TimeConstants.SECOND)
            game.stop();
        game.play();
    }
}

