import greenfoot.*;

/**
 * Display component for the player name.
 */
public class PlayerName extends DisplayComponent {
    private final String playerName;

    /**
     * Constructor for objects of class ScoreBoardResult
     */
    public PlayerName(String playerName) {
        this.playerName = playerName;
        Color color = new Color(0, 0, 0, 0);
        setImage(new GreenfootImage("Hi " + this.playerName, 25, Color.ORANGE, color));
    }
}
