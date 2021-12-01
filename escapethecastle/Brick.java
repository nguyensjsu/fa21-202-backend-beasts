import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Representation of the Brick object.
 */
public class Brick extends DisplayComponent implements IBrickSubject {

    private int vSpeed = 5;
    private int gravity = 0;
    private boolean shouldNotify = true;
    private boolean hasPlayedSound = false;
    private int coolDown = 80;
    private int bucket;
    private static final GreenfootSound brickSound = new GreenfootSound(
            "sounds/brick-hit-ground.wav"
    );
    private final boolean stacked = false;

    private final ArrayList<IBrickObserver> brickObservers = new ArrayList<>();
    private int brickNumber;

    public Brick(int velocity, int brickNumber) {
        this.brickNumber = brickNumber;
//        setImage(new GreenfootImage(String.valueOf(brickNumber), 30, Color.GRAY, Color.WHITE));
        getImage().scale(GameScreen.width / GameScreen.BUCKET_SIZE, 30);
        this.vSpeed = velocity;
        bucket = Greenfoot.getRandomNumber(GameScreen.BUCKET_SIZE);
    }

    public void attachObserver(IBrickObserver brickObserver) {
        brickObservers.add(brickObserver);
    }

    public void removeObserver(IBrickObserver brickObserver) {
        brickObservers.remove(brickObserver);
    }

    public void notifyObservers() {
        for (IBrickObserver brickObserver : brickObservers) {
            brickObserver.notifyBrickFall();
        }
    }


    @Override
    public void act() {
        resetLocationOnGround();
        if (!isOnGround()) {
            hasPlayedSound = false;
            fall();
        } else if (!hasPlayedSound) {
            brickSound.play();
            hasPlayedSound = true;
        }
        resetIfHitWall();
        // Use shouldNotify to prevent notifying even after a complete fall.
        if (shouldNotify && isOnGround()) {
            shouldNotify = false;
            notifyObservers();
        }
    }

    private void resetLocationOnGround() {
        if (isOnWorldGround()) {
            resetLocationOnWorldGround();
        } else {
            // Intentionally using getObject with no allowance for overlap i.e h/2 to allow the object overlapping.
            Brick below = getObject(0, getHeight() / 2, Brick.class);

            // Stack the brick on the top of the below one.
            if (below != null) {
                int belowBricksY = below.getY();
                setLocation(getX(), belowBricksY - getHeight());
            }
        }
    }

    public boolean isOnGround() {
        if (isOnWorldGround()) {
            return true;
        }
        // Intentionally using getObject with no allowance for overlap i.e h/2 to allow the object overlapping.
        Brick below = getObject(0, getHeight() / 2, Brick.class);
        if (below != null) {
            return below.isOnGround();
        }
        return false;
    }

    public void fall() {
        if (coolDown > 0) {
            coolDown--;
            return;
        }
        setLocation(getX(), getY() + vSpeed);
        vSpeed = vSpeed + gravity;
        if (isOnWorldGround()) {
            vSpeed = 0;
        }
    }

    public int getBucket() {
        return bucket;
    }

    public void setBucket(int bucket) {
        this.bucket = bucket;
    }

    public void initializeLocation() {
        setLocation(getBucketX(), 22);
    }

    public int getBucketX() {
        return bucket * getWidth() + getWidth() / 2;
    }

    public void resetBucketLocation() {
        setLocation(getBucketX(), getY());
    }

    public void tryPushingLeft() {
        // Allow pushing when brick has not hit another brick or has no brick on top of it.
        Brick leftBrick = getLeftObject(Brick.class);
        Brick topBrick = getTopObject(Brick.class);
        if (!hasHitLeftWall() && leftBrick == null && topBrick == null) {
            bucket--;
            resetBucketLocation();
        }
    }

    public void tryPushingRight() {
        // Allow pushing when brick has not hit another brick or has no brick on top of it.
        Brick rightBrick = getRightObject(Brick.class);
        Brick topBrick = getTopObject(Brick.class);
        if (!hasHitRightWall() && rightBrick == null && topBrick == null) {
            bucket++;
            resetBucketLocation();
        }
    }

    @Override
    public boolean hasHitRightWall() {
        return bucket >= (GameScreen.BUCKET_SIZE - 1);
    }

    @Override
    public boolean hasHitLeftWall() {
        return bucket <= 0;
    }

    @Override
    public void resetLocationOnRightWall() {
        bucket = 13;
        resetBucketLocation();
    }

    @Override
    public void resetLocationOnLeftWall() {
        bucket = 0;
        resetBucketLocation();
    }

    @Override
    public String toString() {
        return "Brick: " + brickNumber;
    }
}
