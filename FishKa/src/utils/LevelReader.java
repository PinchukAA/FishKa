package utils;

import constants.ResourceConstants;
import game.Game;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class LevelReader {

    public Level level;

    public File XMLFile;
    public String fileName;

    public void readLevel(int levelNumber){
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

    public Level getLevel(){
        return level;
    }
}
