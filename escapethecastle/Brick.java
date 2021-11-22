import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * Write a description of class Brick here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Brick extends DisplayComponent {
    private int vSpeed = 0;
    private int gravity = 3;

    public Brick(int velocity) {
        GreenfootImage img = new GreenfootImage(getImage());
        // Width should be a divisor of the world's width (700) to allow dropping the bricks at all places.
        // If width changes, MyWorld.addNewBricks should change accordingly.
        img.scale(50, img.getHeight() / 5);
        setImage(img);
        this.vSpeed = velocity;
    }

    public void act() {
        fall();
        // Added for testing Gameover screen
        
        // if (isTouching(Player.class)) {
        //    GameOverScreen gameover = new GameOverScreen(scoreDisplay.getScore());
        //    Greenfoot.setWorld(gameover);
        // }
    }

    public void fall() {
        setLocation(getX(), getY() + vSpeed);
        if (getY() >= getWorld().getHeight() - getImage().getHeight() / 2) vSpeed = 0;
    }
}
