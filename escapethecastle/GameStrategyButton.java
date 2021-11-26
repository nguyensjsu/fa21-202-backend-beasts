import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class GameStrategyButton extends DisplayComponent {

    private final IGameStrategy gameStrategy;
    private static final GreenfootSound clickSound = new GreenfootSound("sounds/button-click.wav");

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
            clickSound.play();
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
