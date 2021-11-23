import greenfoot.*;

class Luigi extends Player {
    private final int imageHeight = 50;
    private final int imageWidth = 40;
    
    public Luigi(String runSequence, String playerImage) {
        setRunSequence(runSequence);
        setPlayerImage(playerImage);
    }
    
    public void setPlayerImage(String img) {
        playerImage = new GreenfootImage(img);
        playerImage.scale(playerImage.getWidth()/20, playerImage.getHeight()/20);
    }
    
}