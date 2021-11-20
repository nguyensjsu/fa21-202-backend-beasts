import greenfoot.*;
/**
 * Write a description of class ScoreBoardButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBoardButton extends DisplayComponent {
    /**
     * Constructor for objects of class ScoreBoardButton
     */
    public ScoreBoardButton()
    {
        GreenfootImage buttonImage = new GreenfootImage("button_replay.png");
        buttonImage.scale(200,50);
        setImage(buttonImage);
    }
}
