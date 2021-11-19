import greenfoot.*;

class Mario extends Player {
    private String img;
    
    public Mario(String img) {
        super();
        setPlayerImage(img);
    }
    
    public void setPlayerImage(String img) {
        GreenfootImage image = new GreenfootImage(img);
        image.scale(image.getWidth() / 5, image.getHeight() / 5);
        setImage(image);
    }
}