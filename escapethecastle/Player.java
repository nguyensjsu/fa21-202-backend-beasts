import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public abstract class Player extends DisplayComponent {
    private int speed = 7;
    private int vSpeed = 0;
    private int gravity = 2;
    private int jumpStrength = 30;

    protected Player() {
        vSpeed = 0;
    }

    public abstract void setPlayerImage(String img);

    
    public void act() {
        move();
    }
    
    public void moveLeft() {
        setLocation(getX() - speed, getY());
    }
    
    public void moveRight() {
        setLocation(getX() + speed, getY());
    }

    public void fall() {
        setLocation(getX(), getY() + vSpeed);
        vSpeed = vSpeed + gravity;
    }

    public void jump() {
        vSpeed = -jumpStrength;
        fall();
    }


    public void move() {
         if(Greenfoot.isKeyDown("left")){
            moveLeft();
        }
        if(Greenfoot.isKeyDown("right")){
            moveRight();
        }
        if(Greenfoot.isKeyDown("up") && isOnSolidGround()){
            jump();
        }
        if(!isOnSolidGround()) {
            fall();
        } else {
            vSpeed = 0;
            Brick rightBrick = (Brick) getOneObjectAtOffset(getImage().getWidth() / -2, getImage().getHeight() / 2, Brick.class);
            Brick leftBrick = (Brick) getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / 2, Brick.class);
            
            if(rightBrick != null) {
                // land on brick
                int brickTopLoc = rightBrick.getY() - (rightBrick.getImage().getHeight()/2);
                setLocation(getX(), brickTopLoc - getImage().getHeight()/2);
            } else if(leftBrick != null) {
                // land on brick
                int brickTopLoc = leftBrick.getY() - (leftBrick.getImage().getHeight()/2);
                setLocation(getX(), brickTopLoc - getImage().getHeight()/2);
            } else {
                // land on ground
                setLocation(getX(), getWorld().getHeight() - (getImage().getHeight()/2));
            }
        }
    }

    public boolean isOnSolidGround() {
        if (getY() >= getWorld().getHeight() - (getImage().getHeight() / 2)) return true;

        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();

        if (getOneObjectAtOffset(imageWidth / -2, imageHeight / 2, Brick.class) != null || getOneObjectAtOffset(imageWidth / 2, imageHeight / 2, Brick.class) != null) {
            return true;
        }
        return false;
    }
}