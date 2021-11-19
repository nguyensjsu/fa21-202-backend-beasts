import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class PlayerOption extends DisplayComponent {
    private final PlayerType playerType;

    private PlayerOption(PlayerType playerType) {
        this.playerType = playerType;
        Player player = PlayerFactory.getPlayer(playerType);
        setImage(player.getImage());
    }

    public static PlayerOption createForPlayer(PlayerType type) {
        return new PlayerOption(type);
    }

    public void act() {
        PlayerType type = PlayerSelector.getPlayerType();
        if (type == null || !type.equals(playerType)) {
            setTransparency(255);
        }
        if (Greenfoot.mouseClicked(this)) {
            PlayerSelector.choosePlayer(playerType);
            setTransparency(170);
        }
    }

    private void setTransparency(int i) {
        GreenfootImage image = getImage();
        image.setTransparency(i);
        setImage(image);
    }
}
