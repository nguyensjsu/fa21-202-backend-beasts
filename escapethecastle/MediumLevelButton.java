import greenfoot.Color;
import greenfoot.GreenfootImage;

/**
 * Button to select the medium strategy.
 */
public class MediumLevelButton extends GameStrategyButton {

    public MediumLevelButton() {
        super(new GreenfootImage("MEDIUM", 20, Color.BLACK, Color.YELLOW), new MediumGameStrategy());
    }
}