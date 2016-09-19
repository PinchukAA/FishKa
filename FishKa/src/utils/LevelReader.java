package utils;

import java.io.*;

public class LevelReader {
    private static String FILE_PATH = "res/";
    private static String FILE_NAME = "level";

    private BufferedReader reader;
    public Integer[] array;
    public int levelNumber;

    public LevelReader() {
        levelNumber = 1;
        array = new Integer[8];
        readLevel();
    }

    public void readLevel(){
        String line;
        int i = 0;

        try {
            reader =  new BufferedReader(new FileReader(new File(FILE_PATH + FILE_NAME + levelNumber +  ".txt")));
            while ((line = reader.readLine()) != null){
                array[i] = Integer.parseInt(line);
                i++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void nextLevel(){
        levelNumber++;
        readLevel();
    }


    public int getScoreWin(){
        return array[0];
    }
    public int getScoreReq(){
        return array[1];
    }
    public int getSpeedGreen(){
        return array[2];
    }
    public int getSpeedYellow(){
        return array[3];
    }
    public int getSpeedShark(){
        return array[4];
    }
    public int getAmountGreen(){
        return array[5];
    }
    public int getAmountYellow(){
        return array[6];
    }
    public int getAmountShark(){
        return array[7];
    }
}
