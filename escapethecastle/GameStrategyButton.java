import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class GameStrategyButton extends DisplayComponent {

    private IGameStrategy gameStrategy;

    public GameStrategyButton(GreenfootImage image, IGameStrategy gameStrategy) {
        this.gameStrategy = gameStrategy;
        setImage(image);
    }

    public void act() {
        IGameStrategy currentStrategy = GameStrategyProvider.getGameStrategy();
        if (!currentStrategy.equals(gameStrategy)) {
            setTransparency(255);
        }
        if (Greenfoot.mouseClicked(this)) {
            GameStrategyProvider.setGameStrategy(gameStrategy);
            setTransparency(170);
        }
    }

    private void setTransparency(int i) {
        GreenfootImage image = getImage();
        image.setTransparency(i);
        setImage(image);
    }
}
