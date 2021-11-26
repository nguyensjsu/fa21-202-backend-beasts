import greenfoot.*;
/**
 * Write a description of class ScoreBoardResult here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerName extends DisplayComponent
{
    private final String playerName;
    /**
     * Constructor for objects of class ScoreBoardResult
     */
    public PlayerName(String playerName) {
        this.playerName = playerName;
        Color color = new Color(0,0,0,0);
        setImage(new GreenfootImage ("Hi " + this.playerName, 25, Color.ORANGE, color));
    }
}
