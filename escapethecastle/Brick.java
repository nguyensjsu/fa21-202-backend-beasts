import greenfoot.*;

/**
 * Write a description of class Brick here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Brick extends DisplayComponent {
    private int vSpeed = 0;
    private int gravity = 2;
    
    public Brick(int velocity) {
        GreenfootImage img = new GreenfootImage(getImage());
        img.scale(img.getWidth() / 5, img.getHeight() / 5);
        setImage(img);
        this.vSpeed = velocity;
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
        setLocation(getX(), getY() + vSpeed);
        if (getY() >= getWorld().getHeight() - getImage().getHeight() / 2) vSpeed = 0;
    }
}
