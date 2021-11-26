import greenfoot.Color;
import greenfoot.GreenfootImage;

public class Rank extends DisplayComponent{
    private int rank;

    /**
     * Constructor for objects of class Score
     */
    public Rank(int rank) {
        this.rank = rank;
        Color color = new Color(0,0,0,0);
        String userRank = "Your rank is " + this.rank;
        setImage(new GreenfootImage(userRank, 28, Color.ORANGE, color));
    }
}
