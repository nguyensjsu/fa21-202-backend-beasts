import greenfoot.*;
/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score extends DisplayComponent
{
    private final int score;
    
    /**
     * Constructor for objects of class Score
     */
    public Score(int score) {
        this.score = score;
        Color color = new Color(0,0,0,0);
        String userScore = "Your score is " + this.score;
        setImage(new GreenfootImage (userScore, 30, Color.ORANGE, color));
    }
}
