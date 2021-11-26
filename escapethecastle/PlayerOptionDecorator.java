import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class PlayerOptionDecorator extends DisplayComponent {
    private final Player wrappedPlayer;
    private static final GreenfootSound CLICK_SOUND = new GreenfootSound("sounds/button-click.wav");

    private PlayerOptionDecorator(Player wrapped) {
        this.wrappedPlayer = wrapped;
        setImage(wrapped.getPlayerImage());
        setTransparency(170);
    }

    public static PlayerOptionDecorator wrapAround(Player player) {
        return new PlayerOptionDecorator(player);
    }

    public void act() {
        Player chosenPlayer = PlayerSelector.getChosenPlayer();
        if (chosenPlayer == null || !chosenPlayer.equals(wrappedPlayer)) {
            setTransparency(255);
        }
        if (Greenfoot.mouseClicked(this)) {
            CLICK_SOUND.play();
            PlayerSelector.choosePlayer(wrappedPlayer);
            setTransparency(170);
        }
    }

    private void setTransparency(int i) {
        GreenfootImage image = getImage();
        image.setTransparency(i);
        setImage(image);
    }
    
    public void setPlayerName(String name) {
        wrappedPlayer.setPlayerName(name);
    }
}
