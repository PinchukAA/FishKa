package utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "Level")
@XmlAccessorType (XmlAccessType.FIELD)
public class Level implements java.io.Serializable{

    public int scoreWin;
    public int yellowSpeed;
    public int yellowNum;
    public int greenSpeed;
    public int greenNum;
    public int sharkSpeed;
    public int sharkNum;

    public void setScoreWin(int scoreWin){
        this.scoreWin = scoreWin;
    }

    public int getScoreWin(){
        return scoreWin;
    }


    public void setYellowSpeed(int yellowSpeed){
        this.yellowSpeed = yellowSpeed;
    }

    public int getYellowSpeed(){
        return yellowSpeed;
    }


    public void setYellowNum(int yellowNum){
        this.yellowNum= yellowNum;
    }

    public int getYellowNum(){
        return yellowNum;
    }


    public void setGreenSpeed(int greenSpeed){
        this.greenSpeed = greenSpeed;
    }

    public int getGreenSpeed(){
        return greenSpeed;
    }


    public void setGreenNum(int greenNum){
        this.greenNum = greenNum;
    }

    public int getGreenNum(){
        return greenNum;
    }


    public void setSharkSpeed(int sharkSpeed){
        this.sharkSpeed = sharkSpeed;
    }

    public int getSharkSpeed(){
        return sharkSpeed;
    }

    public void setSharkNum(int sharkNum){
        this.sharkNum = sharkNum;
    }

    public int getSharkNum(){
        return sharkNum;
    }

}
