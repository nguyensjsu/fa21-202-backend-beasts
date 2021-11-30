import greenfoot.GreenfootImage;

/**
 * A representation of the door object in the game.
 */
public class Door extends DisplayComponent {

    public Door() {
        GreenfootImage image = new GreenfootImage("images/door.png");
        image.scale(image.getWidth() / 20, image.getHeight() / 20);
        setImage(image);
    }

}
