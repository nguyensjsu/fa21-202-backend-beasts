import greenfoot.GreenfootSound;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Write a description of class Brick here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Brick extends DisplayComponent implements IBrickSubject {

    private int vSpeed = 0;
    private final int gravity = 0;
    private boolean shouldNotify = true;
    private boolean bricksTouching = false;
    private boolean hasPlayedSound = false;
    private int bucket;
    private static final GreenfootSound brickSound = new GreenfootSound(
            "sounds/brick-hit-ground.wav"
    );

    private final ArrayList<IBrickObserver> brickObservers = new ArrayList<>();

    public Brick(int velocity) {
        // Adding 1 pixel for overlaps.
        getImage().scale(GameScreen.width / GameScreen.BUCKET_SIZE, 30);
        this.vSpeed = velocity;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        bucket = random.nextInt(0, GameScreen.BUCKET_SIZE);
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
        setLocation(getX(), getY() + vSpeed);
        vSpeed = vSpeed + gravity;
        if (isOnWorldGround()) {
            vSpeed = 0;
            resetLocationOnWorldGround();
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
}
