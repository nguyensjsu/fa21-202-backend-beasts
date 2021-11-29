import greenfoot.Color;
import greenfoot.GreenfootImage;

/**
 * Button for selecting the easy level game.
 */
public class EasyLevelButton extends GameStrategyButton {

    public EasyLevelButton() {
        super(new GreenfootImage("EASY", 20, Color.BLACK, Color.GREEN), new EasyGameStrategy());
    }
}
