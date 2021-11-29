import greenfoot.Color;
import greenfoot.GreenfootImage;

/**
 * Display component to show the rank.
 */
public class Rank extends DisplayComponent {

    public Rank(int rank) {
        Color color = new Color(0, 0, 0, 0);
        String userRank = "Your rank is " + rank;
        setImage(new GreenfootImage(userRank, 28, Color.ORANGE, color));
    }
}
