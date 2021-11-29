import greenfoot.*;

/**
 * Represents luigi player
 */
public class Luigi extends Player {
    public Luigi(String runSequence, String playerImage) {
        setRunSequence(runSequence);
        setPlayerImage(playerImage);
    }

    public void setPlayerImage(String img) {
        playerImage = new GreenfootImage(img);
        playerImage.scale(playerImage.getWidth() / 4, playerImage.getHeight() / 4);
    }
}