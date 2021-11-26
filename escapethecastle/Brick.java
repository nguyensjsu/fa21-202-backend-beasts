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
  private final int gravity = 0;
  private boolean shouldNotify = true;
  private boolean bricksTouching = false;
  private boolean hasPlayedSound = false;
  private static final GreenfootSound brickSound = new GreenfootSound(
    "sounds/brick-hit-ground.wav"
  );

  private final ArrayList<IBrickObserver> brickObservers = new ArrayList<>();

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

  public Brick(int velocity) {
    GreenfootImage img = new GreenfootImage(getImage());
    img.scale(GameScreen.width / GameScreen.BUCKET_SIZE, img.getHeight() / 5);
    setImage(img);
    this.vSpeed = velocity;
  }

  public void act() {
    if (!isOnGround()) {
      hasPlayedSound = false;
      fall();
    } else {
      if (!hasPlayedSound) {
        brickSound.play();
        hasPlayedSound = true;
      }
    }
    hitWall();
    // Use shouldNotify to prevent notifying even after a complete fall.
    if (shouldNotify && isOnGround()) {
      shouldNotify = false;
      notifyObservers();
    }
    if (!bricksTouching) {
      if (isTouching(Brick.class)) {
        Brick below = (Brick) getOneObjectAtOffset(
          0,
          getImage().getHeight() / 2,
          Brick.class
        );
        if (below != null) {
          setLocation(
            getX(),
            below.getY() -
            below.getImage().getHeight() /
            2 -
            getImage().getHeight() /
            2
          );
        }

        Brick top = (Brick) getOneObjectAtOffset(
          0,
          getImage().getHeight() / -2,
          Brick.class
        );
        if (top != null) {
          bricksTouching = true;
        }

        Brick left = (Brick) getOneObjectAtOffset(
          getImage().getWidth() / -2,
          0,
          Brick.class
        );
        if (left != null) {
          bricksTouching = true;
          setLocation(
            left.getX() +
            left.getImage().getWidth() /
            2 +
            getImage().getWidth() /
            2,
            getY()
          );
        }

        Brick right = (Brick) getOneObjectAtOffset(
          getImage().getWidth() / 2,
          0,
          Brick.class
        );
        if (right != null) {
          bricksTouching = true;
          setLocation(
            right.getX() -
            right.getImage().getWidth() /
            2 -
            getImage().getWidth() /
            2,
            getY()
          );
        }
      }
    }
  }

  public boolean bricksTouching() {
    return bricksTouching;
  }

  public boolean isOnGround() {
    if (getY() >= getWorld().getHeight() - getImage().getHeight() / 2) {
      return true;
    }
    Brick below = (Brick) getOneObjectAtOffset(
      0,
      getImage().getHeight() / 2,
      Brick.class
    );
    if (below != null) {
      return below.isOnGround();
    }
    return false;
  }

  public boolean hasHitWall() {
    return (
      (getX() + (getImage().getWidth() / 2) >= getWorld().getWidth()) ||
      (getX() - getImage().getWidth() / 2 <= 0)
    );
  }

  public void hitWall() {
    if (
      getX() + (getImage().getWidth() / 2) >= getWorld().getWidth()
    ) setLocation(getWorld().getWidth() - (getImage().getWidth() / 2), getY());
    if (getX() - getImage().getWidth() / 2 <= 0) setLocation(
      getImage().getWidth() / 2,
      getY()
    );
  }

  public void fall() {
    setLocation(getX(), getY() + vSpeed);
    vSpeed = vSpeed + gravity;
    if (getY() >= getWorld().getHeight() - getImage().getHeight() / 2) {
      vSpeed = 0;
      setLocation(
        getX(),
        getWorld().getHeight() - (getImage().getHeight() / 2)
      );
    }
  }
}
