import greenfoot.GreenfootImage;

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
    private int bricksTouchedGround = 0;

    static ArrayList<IBrickObserver> observers = new ArrayList<>();

    public void attachObserver(IBrickObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(IBrickObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers(int bricks) {
        for (IBrickObserver bo: observers) {
            bo.increaseBricks(bricks);
        }
    }

    public Brick(int velocity) {
        GreenfootImage img = new GreenfootImage(getImage());
        // Width should be a divisor of the world's width (700) to allow dropping the bricks at all places.
        // If width changes, GameScreen.addNewBricks should change accordingly.
        img.scale(50, img.getHeight() / 5);
        setImage(img);
        this.vSpeed = velocity;
        attachObserver(new ScoreCalculator());
    }

    public void act() {
        if(!isOnGround()) {
            fall();
        }
        if(!bricksTouching) {
            hitWall();

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

    public boolean isOnGround() {
        if(getY() >= getWorld().getHeight() - getImage().getHeight() / 2) {
            this.bricksTouchedGround++;
            notifyObservers(bricksTouchedGround);
            return true;
        }
        Brick below = (Brick) getOneObjectAtOffset(0, getImage().getHeight() / 2, Brick.class);
        if(below != null) {
            return below.isOnGround();
        }
        return false;
    }

    public void hitWall() {
        if (getX() + (getImage().getWidth() / 2) >= getWorld().getWidth())
            setLocation(getWorld().getWidth() - (getImage().getWidth() / 2), getY());
        if (getX() - getImage().getWidth() / 2 <= 0)
            setLocation(getImage().getWidth() / 2, getY());
    }

    public void fall() {
        setLocation(getX(), getY() + vSpeed);
    }
}
