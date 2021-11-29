import greenfoot.Color;
import greenfoot.GreenfootImage;

/**
 * Display component to show the score board result.
 */
public class ScoreBoardResult extends DisplayComponent {

    public ScoreBoardResult(String result) {
        Color color = new Color(0, 0, 0, 0);
        setImage(new GreenfootImage(result, 31, Color.ORANGE, color));
    }
}
