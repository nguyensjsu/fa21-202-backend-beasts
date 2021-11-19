import greenfoot.*;

class Wario extends Player {
    private String img;
    
    public Wario(String img) {
        super();
        getPlayerImage(img);
    }
    
    public void getPlayerImage(String img) {
        GreenfootImage image = new GreenfootImage(img);
        image.scale(image.getWidth() / 15, image.getHeight() / 15);
        setImage(image);
    }
}