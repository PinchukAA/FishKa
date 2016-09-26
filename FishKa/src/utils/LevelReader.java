package utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class LevelReader {
    private static String FILE_PATH = "res/";
    private static String FILE_NAME = "level";


    public Level level;
    public int levelNumber;

    public LevelReader(){
        levelNumber = 1;
        readLevel();
    }

    public void readLevel(){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File XMLFile = new File(FILE_PATH + FILE_NAME + levelNumber + ".xml");

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
