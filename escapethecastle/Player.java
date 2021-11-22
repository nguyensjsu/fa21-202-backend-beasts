import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public abstract class Player extends DisplayComponent {
    private final int GRAVITY = 1;
    private int velocity;
    private static GreenfootSound jumpSound = new GreenfootSound("sounds/jump.wav");

    protected Player() {
        velocity = 0;
        jumpSound.setVolume(90);
    }

    public abstract void setPlayerImage(String img);

    /**
     * Act - do whatever the Character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Add your action code here.
        fall();
        if (Greenfoot.isKeyDown("up") && isOnSolidGround()) jump();
        move();
    }

    public void fall() {
        setLocation(getX(), getY() + velocity);
        if (isOnSolidGround()) velocity = 0;
        else velocity += GRAVITY;
    }

    public void jump() {
        jumpSound.play();
        velocity = -20;
    }


    public void move() {
        int x = getX();
        int y = getY();

        if (Greenfoot.isKeyDown("up")) y--;
        if (Greenfoot.isKeyDown("left")) x -= 2;
        if (Greenfoot.isKeyDown("down")) y++;
        if (Greenfoot.isKeyDown("right")) x += 2;
        setLocation(x, y);
    }

    public boolean isOnSolidGround() {
        boolean isOnGround = false;
        if (getY() > getWorld().getHeight() - getImage().getHeight() / 2 - 1) isOnGround = true;

        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();

        if (getOneObjectAtOffset(imageWidth / -2, imageHeight / 2, Brick.class) != null || getOneObjectAtOffset(imageWidth / 2, imageHeight / 2, Brick.class) != null) {
            isOnGround = true;
        }
        return isOnGround;
    }
}