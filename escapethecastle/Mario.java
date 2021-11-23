import greenfoot.*;

class Mario extends Player {
    private String img;
    
    public Mario(String img) {
        super();
        setPlayerImage(img);
    }
    
    public void setPlayerImage(String img) {
        GreenfootImage image = new GreenfootImage(img);
        image.scale(image.getWidth() / 25, image.getHeight() / 25);
        setImage(image);
    }
}