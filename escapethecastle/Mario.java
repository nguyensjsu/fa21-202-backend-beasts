import greenfoot.*;


/**
 * Class for player Mario.
 */
class Mario extends Player {

    public Mario(String runSequence, String playerImage) {
        setRunSequence(runSequence);
        setPlayerImage(playerImage);
    }

    public void setPlayerImage(String img) {
        playerImage = new GreenfootImage(img);
        playerImage.scale(playerImage.getWidth() / 25, playerImage.getHeight() / 25);
    }

}