import greenfoot.*;

/**
 * Write a description of class Score here.
 */
public class Score extends DisplayComponent {

    /**
     * Constructor for objects of class Score
     */
    public Score(int score) {
        Color color = new Color(0, 0, 0, 0);
        String userScore = "Score " + score;
        setImage(new GreenfootImage(userScore, String.valueOf(score).length() * 4, Color.ORANGE, color));
    }
}
