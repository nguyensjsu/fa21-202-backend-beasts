import greenfoot.*;

/**
 * Display component for the score on the game over screen.
 */
public class ScoreDisplay extends DisplayComponent implements IScoreUpdateObserver {
    private int score;

    public int getScore() {
        return this.score;
    }

    /**
     * Constructor for objects of class ScoreDisplay
     */
    public ScoreDisplay() {
        this.score = 0;
        setImage(new GreenfootImage("Score: " + this.score, 20, Color.WHITE, Color.BLACK));
    }

    /**
     * Update the current score by updateScore
     */
    @Override
    public void updateScore(int updateScore) {
        this.score = updateScore;
        setImage(new GreenfootImage("Score: " + this.score, 20, Color.WHITE, Color.BLACK));
    }
}
