import greenfoot.*;
/**
 * Write a description of class ScoreBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBoard extends DisplayComponent
{
    private String result;
    /**
     * Constructor for objects of class ScoreBoardResult
     */
    public ScoreBoardResult(String result) {
        this.result = result;
        Color color = new Color(0,0,0,0);
        setImage(new GreenfootImage (this.result, 50, Color.ORANGE, color));
    }
}
