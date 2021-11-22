import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;

public abstract class Player extends DisplayComponent {
    private int speed = 7;
    private int vSpeed = 0;
    private int gravity = 2;
    private int jumpStrength = 30;

    private static GreenfootSound jumpSound = new GreenfootSound("sounds/jump.wav");

    protected Player() {
        vSpeed = 0;
        jumpSound.setVolume(90);
    }

    public abstract void setPlayerImage(String img);
    
    public void act() {
        move();
        push();
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
        jumpSound.play();
    }


    public void move() {
        hitWall();
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
            Brick down = (Brick) getOneObjectAtOffset(0, getImage().getHeight() / 2, Brick.class);
            
            if(down != null) {
                // land on brick
                int brickTopLoc = down.getY() - (down.getImage().getHeight()/2);
                setLocation(getX(), brickTopLoc - getImage().getHeight()/2);
            } else {
                // land on ground
                setLocation(getX(), getWorld().getHeight() - (getImage().getHeight()/2));
            }
        }
    }
    
    public void hitWall() {
        if(getX()+(getImage().getWidth()/2) >= getWorld().getWidth())
            setLocation(getWorld().getWidth() - (getImage().getWidth()/2), getY());
        if(getX()-getImage().getWidth()/2 <= 0)
            setLocation(getImage().getWidth()/2, getY());
    }
    
    public void push() {
        // game over if brick bottom touches player
        Brick up = (Brick) getOneObjectAtOffset(0, getImage().getHeight() / -2, Brick.class);
        if(up != null) {
           GameScreen myWorld = (GameScreen) getWorld();
           ScoreDisplay scoreDisplay = myWorld.getScoreDisplay();
           GameOverScreen gameover = new GameOverScreen(scoreDisplay.getScore());
           Greenfoot.setWorld(gameover);
        }
        
        Brick left = (Brick) getOneObjectAtOffset(getImage().getWidth()/-2, 0, Brick.class);
        if(left != null) {
            if(!left.bricksTouching()) {
                left.setLocation(getX() - getImage().getWidth()/2 - left.getImage().getWidth()/2, left.getY());
            }
            setLocation(left.getX() + left.getImage().getWidth()/2 + getImage().getWidth()/2, getY());
        }
        
        Brick right = (Brick) getOneObjectAtOffset(getImage().getWidth()/2, 0, Brick.class);
        if(right != null) {
            if(!right.bricksTouching()) {
                right.setLocation(getX() + getImage().getWidth()/2 + right.getImage().getWidth()/2, right.getY());
            }
            setLocation(right.getX() - right.getImage().getWidth()/2 - getImage().getWidth()/2, getY());
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