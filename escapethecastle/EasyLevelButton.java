import greenfoot.Color;
import greenfoot.GreenfootImage;

public class EasyLevelButton extends GameStrategyButton {

    public EasyLevelButton() {
        super(new GreenfootImage("EASY", 20,  Color.BLACK, Color.GREEN), new EasyGameStrategy());
    }
}
