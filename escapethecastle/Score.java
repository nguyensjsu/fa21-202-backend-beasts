import greenfoot.*;

/**
 * Display component to show the score on the game over screen.
 */
public class Score extends DisplayComponent {

    /**
     * Constructor for objects of class Score
     */
    public Score(int score) {
        Color color = new Color(0, 0, 0, 0);
        String userScore = "Score " + score;
        setImage(new GreenfootImage(userScore, 28 + String.valueOf(score).length(), Color.ORANGE, color));
    }
}
