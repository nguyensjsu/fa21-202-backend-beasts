import greenfoot.*;

class Wario extends Player {
    private final int imageHeight = 60;
    private final int imageWidth = 55;
    
    public Wario(String img, String flipImg) {
        super();
        setPlayerImage(img);
        setPlayerFlipImage(flipImg);
    }
    
    public void setPlayerImage(String img) {
        GreenfootImage image = new GreenfootImage(img);
        image.scale(imageWidth, imageHeight);
        this.img = image;
        setImage(image);
    }
    
    public void setPlayerFlipImage(String img) {
        GreenfootImage image = new GreenfootImage(img);
        image.scale(imageWidth, imageHeight);
        this.flipImg = image;
    }
}