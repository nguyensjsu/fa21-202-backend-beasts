import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

import java.util.ArrayList;

/**
 * Abstract class to represent a player.
 */
public abstract class Player extends DisplayComponent implements IPlayerSubject {
    private final int speed = 7;

    private int vSpeed = 0;
    private final int gravity = 3;
    private final int jumpStrength = 30;
    private static String name = "Player 1";

    private IPlayerState currentState;
    private final IPlayerState notPlayingState;
    private final IPlayerState playingState;
    private final IPlayerState wonState;
    private final IPlayerState diedState;

    private GreenfootImage[] playerRightImages;
    private GreenfootImage[] playerLeftImages;
    protected GreenfootImage playerImage;
    private float currentImage = 0f;
    private static final float animationSpeed = 0.4f;
    private int totalNumberOfLives = 1;

    private final ArrayList<IPlayerObserver> playerObservers = new ArrayList<>();
    private static final GreenfootSound jumpSound = new GreenfootSound("sounds/jump.wav");
    public static final GreenfootSound gameOverSound = new GreenfootSound("sounds/game-over.wav");

    protected Player() {
        vSpeed = 0;
        jumpSound.setVolume(90);
        gameOverSound.setVolume(80);
        notPlayingState = new NotPlayingState(this);
        playingState = new PlayingState(this);
        wonState = new WonState(this);
        diedState = new DiedState(this);
        currentState = notPlayingState;
    }

    public static String getPlayerName() {
        return name;
    }

    public static void setPlayerName(String name) {
        Player.name = name;
    }

    public GreenfootImage getPlayerImage() {
        return playerImage;
    }

    public void startPlaying() {
        currentState.startPlaying(totalNumberOfLives);
    }

    public void lostLife() {
        currentState.lostLife();
    }

    public void won() {
        currentState.won();
    }

    public void died() {
        currentState.died();
    }

    public void setPlayingState(int totalNumberOfLives) {
        ((PlayingState) playingState).setNumberOfLives(totalNumberOfLives);
        currentState = playingState;
    }

    public void setWonState() {
        currentState = wonState;
    }

    public void setDiedState() {
        currentState = diedState;
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
    public void notifyObservers(PlayerState playerState) {
        if (playerState.equals(PlayerState.DIED)) {
            for (IPlayerObserver playerObserver : playerObservers) {
                playerObserver.notifyLevelDied();
            }
        } else if (PlayerState.LOST_LIFE.equals(playerState)) {
            for (IPlayerObserver playerObserver : playerObservers) {
                playerObserver.notifyLostLife();
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
        int w = bigImage.getWidth();
        int h = bigImage.getHeight();
        int numImagesAcross = 4;
        playerRightImages = new GreenfootImage[numImagesAcross];
        playerLeftImages = new GreenfootImage[numImagesAcross];
        for (int x = 0; x < numImagesAcross; x++) {
            playerRightImages[x] = new GreenfootImage(w / numImagesAcross, h);
            playerLeftImages[x] = new GreenfootImage(w / numImagesAcross, h);
            playerRightImages[x].drawImage(bigImage, -w * x / numImagesAcross, 0);
            playerLeftImages[x].drawImage(bigImage, -w * x / numImagesAcross, 0);
            playerLeftImages[x].mirrorHorizontally();
        }
        setImage(playerRightImages[0]);
    }

    @Override
    public void act() {
        move();
        push();
        hasReachedDoor();
    }

    private void hasReachedDoor() {
        Door touch = (Door) getOneIntersectingObject(Door.class);
        if (touch != null && Greenfoot.isKeyDown("Enter")) {
            won();
        }
    }

    public void tryMovingLeft() {
        Brick leftBrick = getLeftObject(Brick.class);
        // If there is brick on left, don't move.
        if (leftBrick != null) return;

        setLocation(getX() - speed, getY());
        setImage(playerLeftImages[(int) currentImage % playerRightImages.length]);
        currentImage += animationSpeed;
        if (currentImage == playerRightImages.length) {
            currentImage = 0;
        }
    }

    public void tryMovingRight() {
        Brick rightBrick = getRightObject(Brick.class);
        // If there is brick on right, don't move.
        if (rightBrick != null) return;

        setLocation(getX() + speed, getY());
        setImage(playerRightImages[(int) currentImage % playerLeftImages.length]);
        currentImage += animationSpeed;
        if (currentImage == playerRightImages.length) {
            currentImage = 0;
        }
    }

    public void fall() {
        // If player is falling and on the solid ground, stop the fall.
        if (isOnSolidGround() && vSpeed >= 0) {
            vSpeed = 0;
            return;
        }
        setLocation(getX(), getY() + vSpeed);
        vSpeed = vSpeed + gravity;
    }

    public void jump() {
        vSpeed = -jumpStrength;
        fall();
        jumpSound.play();
    }


    public void move() {
        resetIfHitWall();
        if (Greenfoot.isKeyDown("left")) {
            tryMovingLeft();
        }
        if (Greenfoot.isKeyDown("right")) {
            tryMovingRight();
        }
        if (Greenfoot.isKeyDown("up") && isOnSolidGround()) {
            jump();
        }
        if (!isOnSolidGround()) {
            fall();
        } else {
            if (vSpeed <= 0) {
                while (isOnSolidGround()) {
                    setLocation(getX(), getY() - 1);
                }
                setLocation(getX(), getY() + 1);
                return;
            }
            vSpeed = 0;
            push();
            Brick down = getBottomSideObject(Brick.class);
            // If a brick is there to land, land there.
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

    public void push() {
        // game over if brick bottom touches player
        Brick up = getTopObject(Brick.class);
        if (up != null && !up.isOnGround()) {
            lostLife();
        }

        Brick leftBrick = getLeftObject(Brick.class);
        if (leftBrick != null && Greenfoot.isKeyDown("left")) {
            leftBrick.tryPushingLeft();
        }

        Brick rightBrick = getRightObject(Brick.class);
        if (rightBrick != null && Greenfoot.isKeyDown("right")) {
            rightBrick.tryPushingRight();
        }

    }

    public boolean isOnSolidGround() {
        if (isOnWorldGround()) return true;

        Brick b = getBelowObject(Brick.class);
//        System.out.println("Touched " + b + " at speed " + vSpeed);
        return b != null;
    }

    public int getTotalNumberOfLives() {
        return totalNumberOfLives;
    }

    public void setTotalNumberOfLives(int lives) {
        totalNumberOfLives = lives;
    }

    public void setvSpeed(int vSpeed) {
        this.vSpeed = vSpeed;
    }

    public ArrayList<IPlayerObserver> getPlayerObservers() {
        return playerObservers;
    }
}