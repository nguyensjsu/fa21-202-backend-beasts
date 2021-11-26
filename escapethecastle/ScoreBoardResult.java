import greenfoot.Color;
import greenfoot.GreenfootImage;

/**
 * Write a description of class ScoreBoardResult here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ScoreBoardResult extends DisplayComponent {
    private final String result;

    /**
     * Constructor for objects of class ScoreBoardResult
     */
    public ScoreBoardResult(String result) {
        this.result = result;
        Color color = new Color(0, 0, 0, 0);
        setImage(new GreenfootImage(this.result, 31, Color.ORANGE, color));
    }
}
