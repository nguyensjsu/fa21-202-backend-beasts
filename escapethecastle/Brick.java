import greenfoot.*;
import java.util.ArrayList;

/**
 * Write a description of class Brick here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Brick extends DisplayComponent implements IBrickSubject {
    private int vSpeed = 0;
    private int gravity = 3;
    private boolean bricksTouching = false;
    
    static ArrayList<IBrickObserver> observers = new ArrayList<>();
    
    public void setScore(int score) {
        notifyObservers(score);
    }
    public void attachObserver(IBrickObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(IBrickObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers(int score) {
        for (IBrickObserver bo: observers) {
            bo.setScore(score);
        }
    }

    public Brick(int velocity) {
        GreenfootImage img = new GreenfootImage(getImage());
        // Width should be a divisor of the world's width (700) to allow dropping the bricks at all places.
        // If width changes, GameScreen.addNewBricks should change accordingly.
        img.scale(50, img.getHeight() / 5);
        setImage(img);
        this.vSpeed = velocity;
    }

    public void act() {
        if(!bricksTouching) {
            fall();
            hitWall();
            Brick below = (Brick) getOneObjectAtOffset(0, getImage().getHeight() / 2, Brick.class);
            if(below != null) {
                setLocation(getX(), below.getY() - below.getImage().getHeight() / 2 - getImage().getHeight() / 2);
            }
            
            Brick left = (Brick) getOneObjectAtOffset(getImage().getWidth() / -2, 0, Brick.class);
            if(left != null) {
                bricksTouching = true;
                setLocation(left.getX() + left.getImage().getWidth() / 2 + getImage().getWidth() / 2, getY());
            } else {
                bricksTouching = false;
            }
            
            Brick right = (Brick) getOneObjectAtOffset(getImage().getWidth() / 2, 0, Brick.class);
            if(right != null) {
                bricksTouching = true;
                setLocation(right.getX() - right.getImage().getWidth() / 2 - getImage().getWidth() / 2, getY());
            } else {
                bricksTouching = false;
            }
        }
    }
    
    public boolean bricksTouching() {
        return bricksTouching;
    }
    
    public void hitWall() {
        if (getX() + (getImage().getWidth() / 2) >= getWorld().getWidth())
            setLocation(getWorld().getWidth() - (getImage().getWidth() / 2), getY());
        if (getX() - getImage().getWidth() / 2 <= 0)
            setLocation(getImage().getWidth() / 2, getY());
    }

    public void fall() {
        setLocation(getX(), getY() + vSpeed);
        if (getY() >= getWorld().getHeight() - getImage().getHeight() / 2) vSpeed = 0;   
    }
}
