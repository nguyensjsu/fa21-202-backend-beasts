import greenfoot.Color;
import greenfoot.GreenfootImage;

/**
 * Button for hard level strategy selection.
 */
public class HardLevelButton extends GameStrategyButton {

    public HardLevelButton() {
        super(new GreenfootImage("HARD", 20, Color.BLACK, Color.RED), new HardGameStrategy());
    }
}