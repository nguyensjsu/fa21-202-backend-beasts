import greenfoot.GreenfootImage;

public class Door extends DisplayComponent {
    GreenfootImage image;

    public Door() {
        image = new GreenfootImage("images/door.png");
        image.scale(image.getWidth() / 2, image.getHeight() / 2);
        setImage(image);
    }

}
