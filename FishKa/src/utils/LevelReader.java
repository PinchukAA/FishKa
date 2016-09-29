package utils;

import game.Game;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import Constants.ResourceConstants;
public class LevelReader {

    public Game game;

    public File XMLFile;

    public Level level;
    public int levelNumber;

    public LevelReader(Game game){
        this.game = game;
        levelNumber = 1;
        readLevel();
    }

    public void readLevel(){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            try {
                XMLFile = new File(ResourceConstants.PATH + ResourceConstants.FILE_NAME + levelNumber + ".xml");
            }catch (Exception e){
                game.gameWin();
                e.printStackTrace();
            }


            level = (Level) jaxbUnmarshaller.unmarshal(XMLFile);

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
}
