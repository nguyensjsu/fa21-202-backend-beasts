import greenfoot.*;

class Mario extends Player {
    private final int imageHeight = 50;
    private final int imageWidth = 40;
    
    public Mario(String runSequence, String playerImage) {
        setRunSequence(runSequence);
        setPlayerImage(playerImage);
    }
    
    public void setPlayerImage(String img) {
        playerImage = new GreenfootImage(img);
        playerImage.scale(playerImage.getWidth()/25, playerImage.getHeight()/25);
    }
    
}