import greenfoot.*;

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
    
    public Brick(int velocity) {
        GreenfootImage img = new GreenfootImage(getImage());
        img.scale(img.getWidth() / 5, img.getHeight() / 5);
        setImage(img);
        this.velocity = velocity;
    }

    public void act() {
        fall();
        // Added for testing Gameover screen
        if(isTouching(Player.class)) {
            GameOverScreen gameover = new GameOverScreen(60);
            Greenfoot.setWorld(gameover);
        }
    }

    public void fall() {
        setLocation(getX(), getY() + velocity);
        if (velocity != 0 && getY() > getWorld().getHeight() - getImage().getHeight() / 2 - 1) {
            velocity = 0;
            Greenfoot.playSound("sounds/brick-hit-ground.wav");
        }
    }
}
