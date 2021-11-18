import greenfoot.Color;
import greenfoot.GreenfootImage;

public class MediumLevelButton extends GameStrategyButton {

    public MediumLevelButton() {
        super(new GreenfootImage("MEDIUM", 20, Color.BLACK, Color.YELLOW), new MediumGameStrategy());
    }
}