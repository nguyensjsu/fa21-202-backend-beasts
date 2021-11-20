import greenfoot.*;

/**
 * Write a description of class ScoreBoardBackground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBoardBackground  extends DisplayComponent {
    /**
     * Constructor for objects of class ScoreBoardBackground
     */
    public ScoreBoardBackground()
    {
        GreenfootImage backgroundImage = new GreenfootImage("brick.jpg");
        backgroundImage.scale(1000, 1000);
        setImage(backgroundImage);
    }
}
