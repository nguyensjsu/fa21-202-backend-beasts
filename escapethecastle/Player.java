import greenfoot.*;

import java.util.ArrayList;

public abstract class Player extends DisplayComponent implements IPlayerSubject {
    private final int speed = 7;
    private int vSpeed = 0;
    private int gravity = 3;
    private int jumpStrength = 30;
    private String name = "Player 1";
    
    protected GreenfootImage img;
    protected GreenfootImage flipImg;

    private final ArrayList<IPlayerObserver> playerObservers = new ArrayList<>();
    private static final GreenfootSound jumpSound = new GreenfootSound("sounds/jump.wav");

    protected Player() {
        vSpeed = 0;
        jumpSound.setVolume(90);
    }
    
    public String getPlayerName() {
        return name;
    }
    
    public void setPlayerName(String name) {
        this.name = name;
    }

    @Override
    public void attachObserver(IPlayerObserver observer) {
        playerObservers.add(observer);
    }

    @Override
    public void removeObserver(IPlayerObserver observer) {
        playerObservers.remove(observer);
    }

    @Override
    public void notifyObservers(PlayerFinalState playerFinalState) {
        if (playerFinalState.equals(PlayerFinalState.DIED)) {
            for (IPlayerObserver playerObserver : playerObservers) {
                playerObserver.notifyLevelDied();
            }
        } else {
            for (IPlayerObserver playerObserver : playerObservers) {
                playerObserver.notifyLevelCompleted();
            }
        }
    }

    public abstract void setPlayerImage(String img);
    
    public abstract void setPlayerFlipImage(String img);
    
    public void act() {
        move();
    }

    public void moveLeft() {
        setImage(flipImg);
        setLocation(getX() - speed, getY());
    }

    public void moveRight() {
        setImage(img);
        setLocation(getX() + speed, getY());
    }

    public void fall() {
        setLocation(getX(), getY() + vSpeed);
        vSpeed = vSpeed + gravity;
    }

    public void jump() {
        vSpeed = -jumpStrength;
        fall();
        jumpSound.play();
    }


    public void move() {
        hitWall();
        if (Greenfoot.isKeyDown("left")) {
            moveLeft();
        }
        if (Greenfoot.isKeyDown("right")) {
            moveRight();
        }
        if (Greenfoot.isKeyDown("up") && isOnSolidGround()) {
            jump();
        }
        if (!isOnSolidGround()) {
            fall();
        } else {
            vSpeed = 0;
            push();
            
            int imageHeight = getImage().getHeight();
            int imageWidth = getImage().getWidth();
            Brick down = (Brick) (getOneObjectAtOffset(0, imageHeight / 2, Brick.class) != null ?
                            getOneObjectAtOffset(0, imageHeight / 2, Brick.class) : 
                            getOneObjectAtOffset(imageWidth / -2, imageHeight / 2, Brick.class) != null ?
                            getOneObjectAtOffset(imageWidth / -2, imageHeight / 2, Brick.class) : 
                            getOneObjectAtOffset(imageWidth / 2, imageHeight / 2, Brick.class) != null ?
                            getOneObjectAtOffset(imageWidth / 2, imageHeight / 2, Brick.class) : null);
            if (down != null) {
                // land on brick
                int brickTopLoc = down.getY() - (down.getImage().getHeight() / 2);
                setLocation(getX(), brickTopLoc - getImage().getHeight() / 2);
            } else {
                // land on ground
                setLocation(getX(), getWorld().getHeight() - (getImage().getHeight() / 2));
            }
        }
    }

    public void hitWall() {
        if (getX() + (getImage().getWidth() / 2) >= getWorld().getWidth())
            setLocation(getWorld().getWidth() - (getImage().getWidth() / 2), getY());
        if (getX() - getImage().getWidth() / 2 <= 0)
            setLocation(getImage().getWidth() / 2, getY());
    }

    public void push() {
        // game over if brick bottom touches player
        Brick up = (Brick) getOneObjectAtOffset(0, getImage().getHeight() / -2, Brick.class);
        if (up != null && !up.isOnGround()) {
            //Adding code for score calculator when a player dies.
            notifyObservers(PlayerFinalState.DIED);
        }

        Brick left = (Brick) getOneObjectAtOffset(getImage().getWidth() / -2, 0, Brick.class);
        if (left != null) {
            if (!left.bricksTouching()) {
                left.setLocation(getX() - getImage().getWidth() / 2 - left.getImage().getWidth() / 2, left.getY());
            }
            setLocation(left.getX() + left.getImage().getWidth() / 2 + getImage().getWidth() / 2, getY());
        }

        Brick right = (Brick) getOneObjectAtOffset(getImage().getWidth() / 2, 0, Brick.class);
        if (right != null) {
            if (!right.bricksTouching()) {
                right.setLocation(getX() + getImage().getWidth() / 2 + right.getImage().getWidth() / 2, right.getY());
            }
            setLocation(right.getX() - right.getImage().getWidth() / 2 - getImage().getWidth() / 2, getY());
        }

    }

    public boolean isOnSolidGround() {
        if (getY() >= getWorld().getHeight() - (getImage().getHeight() / 2)) return true;

        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();

        if (getOneObjectAtOffset(imageWidth / -2, imageHeight / 2, Brick.class) != null || 
        getOneObjectAtOffset(imageWidth / 2, imageHeight / 2, Brick.class) != null || 
        getOneObjectAtOffset(0, imageHeight / 2, Brick.class) != null) {
            return true;
        }
        return false;
    }
}