package utils;

import game.Game;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import constants.ResourceConstants;
public class LevelReader {

    public Game game;

    public File XMLFile;
    public String fileName;

    public Level level;
    public int levelNumber;
    public int numLevels;

    public LevelReader(Game game){
        this.game = game;
        levelNumber = 1;
        numLevels = 2;
        readLevel();
    }

    public void readLevel(){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            fileName = ResourceConstants.LEVEL_FILE_NAME + levelNumber + ".xml";
            XMLFile = ResourceLoader.loadFile(fileName);

            if (XMLFile.exists()) level = (Level) jaxbUnmarshaller.unmarshal(XMLFile);

        } catch (JAXBException e){
            e.printStackTrace();
        }
    }

    public void nextLevel(){
        levelNumber++;
        readLevel();
    }

    public Level getLevel(){
        return level;
    }

    public int getLevelNumber(){
        return levelNumber;
    }
}
