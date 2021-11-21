import greenfoot.*;
/**
 * Write a description of class ScoreDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreDisplay extends DisplayComponent implements IScoreUpdateObserver {
    private int score;

    /**
     * Constructor for objects of class ScoreDisplay
     */
    public ScoreDisplay() {
        this.score = 0;
        setImage(new GreenfootImage("Score: " + this.score, 20, Color.WHITE, Color.BLACK));
    }

    public void updateScore(int updateScore) {
        this.score += updateScore;
        setImage(new GreenfootImage("Score: " + this.score, 20, Color.WHITE, Color.BLACK));
    }
}
