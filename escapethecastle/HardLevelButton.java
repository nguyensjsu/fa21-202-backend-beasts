import greenfoot.Color;
import greenfoot.GreenfootImage;

public class HardLevelButton extends GameStrategyButton {

    public HardLevelButton() {
        super(new GreenfootImage("HARD", 20, Color.BLACK, Color.RED), new HardGameStrategy());
    }
}