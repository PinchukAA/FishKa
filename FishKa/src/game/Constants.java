package game;

import utils.Time;

public class Constants {
    public static final int	WIDTH = 1200;
    public static final int	HEIGHT = 800;
    public static final String TITLE = "FishKa";
    public static final int	CLEAR_COLOR = 0xFF66CCFF;
    public static final int	NUM_BUFFERS	= 3;

    public static final float UPDATE_RATE = 60.0f;
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final long IDLE_TIME = 1;


    public static final int YELLOW_TYPE = 0;
    public static final int GREEN_TYPE = 1;
    public static final int SHARK_TYPE = 2;


    public static final String GREEN_LEFT_IMAGE_NAME = "gr80L.png";
    public static final String GREEN_RIGHT_IMAGE_NAME = "gr80R.png";

    public static final int GREEN_SPRITE_SIZE = 80;

    public static final String YELLOW_LEFT_IMAGE_NAME = "y48L.png";
    public static final String YELLOW_RIGHT_IMAGE_NAME = "y48R.png";

    public static final int YELLOW_SPRITE_SIZE = 48;

    public static final String SHARK_LEFT_IMAGE_NAME = "sh128L.png";
    public static final String SHARK_RIGHT_IMAGE_NAME = "sh128R.png";

    public static final int SHARK_SPRITE_SIZE = 128;
    public static final double FISH_SCALE = 1;

    public static final String PLAYER_LEFT_IMAGE_NAME_64 = "pl64L.png";
    public static final String PLAYER_RIGHT_IMAGE_NAME_64 = "pl64R.png";
    public static final String PLAYER_LEFT_IMAGE_NAME_96 = "pl96L.png";
    public static final String PLAYER_RIGHT_IMAGE_NAME_96 = "pl96R.png";

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int UP_LEFT = 4;
    public static final int UP_RIGHT = 5;
    public static final int DOWN_LEFT = 6;
    public static final int DOWN_RIGHT = 7;




}
