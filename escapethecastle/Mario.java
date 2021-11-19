import greenfoot.*;

class Mario extends Player {
    private String img;
    
    public Mario(String img) {
        super();
        getPlayerImage(img);
    }
    
    public void getPlayerImage(String img) {
        GreenfootImage image = new GreenfootImage(img);
        image.scale(image.getWidth() / 5, image.getHeight() / 5);
        setImage(image);
    }
}