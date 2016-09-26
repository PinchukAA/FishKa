package utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class LevelWriter {

    private static String FILE_PATH = "res/";
    private static String FILE_NAME = "level";


    public Level level;
    public int levelNumber;

    public LevelWriter(){
        levelNumber = 10;
        level = new Level();

        level.setScoreWin(200);
        level.setYellowSpeed(1);
        level.setYellowNum(30);
        level.setGreenSpeed(5);
        level.setGreenNum(5);
        level.setSharkSpeed(6);
        level.setSharkNum(6);
    }

    public void writeLevel(){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            File XMLFile = new File(FILE_PATH + FILE_NAME + levelNumber + ".xml");

            jaxbMarshaller.marshal(level, XMLFile);


        } catch (JAXBException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        LevelWriter levelWriter = new LevelWriter();
        levelWriter.writeLevel();
    }
}
