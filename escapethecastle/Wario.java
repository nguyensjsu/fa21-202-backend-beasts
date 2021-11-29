import greenfoot.*;

/**
 * Represents wario player
 */
class Wario extends Player {
    private final int imageHeight = 60;
    private final int imageWidth = 55;

    public Wario(String runSequence, String playerImage) {
        setRunSequence(runSequence);
        setPlayerImage(playerImage);
    }

    public void setPlayerImage(String img) {
        playerImage = new GreenfootImage(img);
        playerImage.scale(playerImage.getWidth() / 6, playerImage.getHeight() / 6);
    }

}