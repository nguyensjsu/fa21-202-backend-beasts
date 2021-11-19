import greenfoot.*;

class Luigi extends Player {
    private String img;
    
    public Luigi(String img) {
        super();
        setPlayerImage(img);
    }
    
    public void setPlayerImage(String img) {
        GreenfootImage image = new GreenfootImage(img);
        image.scale(image.getWidth() / 18, image.getHeight() / 18);
        setImage(image);
    }
}