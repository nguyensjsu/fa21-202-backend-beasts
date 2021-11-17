import greenfoot.Actor;
import greenfoot.GreenfootImage;

/**
 * Write a description of class Brick here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Brick extends DisplayComponent {
    /**
     * Act - do whatever the Brick wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    private final int GRAVITY = 1;
    private int velocity;
    
    public Brick() {
        GreenfootImage img = new GreenfootImage(getImage());
        img.scale(img.getWidth() / 5, img.getHeight() / 5);
        setImage(img);
        velocity = 4;
    }

    public void act() {
        fall();
    }

    public void fall() {
        setLocation(getX(), getY() + velocity);
        if (getY() > getWorld().getHeight() - getImage().getHeight() / 2 - 1) velocity = 0;
    }
}
