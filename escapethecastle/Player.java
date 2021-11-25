import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.GreenfootImage;

import java.util.ArrayList;

public abstract class Player extends DisplayComponent implements IPlayerSubject {
    private final int speed = 7;
    private int vSpeed = 0;
    private int gravity = 3;
    private int jumpStrength = 30;
    private String name = "Player 1";
    
    private GreenfootImage[] playerRightImages;
    private GreenfootImage[] playerLeftImages;
    protected GreenfootImage playerImage;
    private float currentImage = 0f;
    private static final float animationSpeed = 0.4f;


    private final ArrayList<IPlayerObserver> playerObservers = new ArrayList<>();
    private static final GreenfootSound jumpSound = new GreenfootSound("sounds/jump.wav");
    private static final GreenfootSound gameOverSound = new GreenfootSound("sounds/game-over.wav");

    protected Player() {
        vSpeed = 0;
        jumpSound.setVolume(90);
        gameOverSound.setVolume(80);
    }
    
    public String getPlayerName() {
        return name;
    }
    
    public void setPlayerName(String name) {
        this.name = name;
    }

    public GreenfootImage getPlayerImage() {
        return playerImage;
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
    
    protected void setRunSequence(String img) {
        GreenfootImage bigImage = new GreenfootImage(img);
        int w=bigImage.getWidth(), h = bigImage.getHeight();
        int numImagesAcross=4;
        playerRightImages = new GreenfootImage[numImagesAcross];
        playerLeftImages = new GreenfootImage[numImagesAcross];
        for(int x=0; x<numImagesAcross; x++) {
            playerRightImages[x] = new GreenfootImage(w/numImagesAcross, h);
            playerLeftImages[x] = new GreenfootImage(w/numImagesAcross, h);
            playerRightImages[x].drawImage(bigImage, -w*x/numImagesAcross, 0);
            playerLeftImages[x].drawImage(bigImage, -w*x/numImagesAcross, 0);
            playerLeftImages[x].mirrorHorizontally();
        }
        setImage(playerRightImages[0]);
    }

    public void act() {
        move();
        push();
        hasReachedDoor();
    }

    private void hasReachedDoor() {
        Door touch = (Door) getOneIntersectingObject(Door.class);
        if (touch != null && Greenfoot.isKeyDown("Enter")) {
            notifyObservers(PlayerFinalState.WON);
        }
    }

    public void moveLeft() {
        setLocation(getX() - speed, getY());
        setImage(playerLeftImages[(int)currentImage%playerRightImages.length]);
        currentImage += animationSpeed;
        if(currentImage == playerRightImages.length) {
            currentImage = 0;
        }
    }

    public void moveRight() {
        setLocation(getX() + speed, getY());
        setImage(playerRightImages[(int)currentImage%playerLeftImages.length]);
        currentImage += animationSpeed;
        if(currentImage == playerRightImages.length) {
            currentImage = 0;
        }
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
            StartScreen.bgm.stop();
            gameOverSound.play();
            notifyObservers(PlayerFinalState.DIED);
        }

        Brick left = (Brick) getOneObjectAtOffset(getImage().getWidth() / -2, 0, Brick.class);
        if (left != null) {
            if (!left.bricksTouching() && !left.hasHitWall()) {
                left.setLocation(getX() - getImage().getWidth() / 2 - left.getImage().getWidth() / 2, left.getY());
            }
            setLocation(left.getX() + left.getImage().getWidth() / 2 + getImage().getWidth() / 2, getY());
        }

        Brick right = (Brick) getOneObjectAtOffset(getImage().getWidth() / 2, 0, Brick.class);
        if (right != null) {
            if (!right.bricksTouching() && !right.hasHitWall()) {
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