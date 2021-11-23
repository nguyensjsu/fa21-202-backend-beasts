import greenfoot.Greenfoot;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Write a description of class MyWorld here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameScreen extends Screen implements IPlayerObserver {

    private Brick currentBrick;
    private ScoreCalculator scoreCalculator;
    private ScoreDisplay scoreDisplay;
    private Door door;

    /**
     * Constructor for objects of class MyWorld.
     */
    public GameScreen() {
        // Create a new world with 700x500 cells with a cell size of 1x1 pixels.
        // Change the brick's size too if you change this.
        super(700, 500, 1);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        Player player = PlayerSelector.getChosenPlayer();
        scoreDisplay = new ScoreDisplay();
        scoreCalculator = new ScoreCalculator();
        door = new Door();

        // Attach the score calculator to notify for the score.
        player.attachObserver(scoreCalculator);
        // Attach GameScreen as observer to listen the player's state.
        player.attachObserver(this);
        player.getImage().setTransparency(255);
        scoreCalculator.attachObserver(scoreDisplay);

        addObject(door, getWidth() - door.getWidth() / 3, getHeight() - door.getHeight() / 2);
        addObject(scoreDisplay, 600, 10);
        addObject(player, 69, 445);
    }

    public ScoreDisplay getScoreDisplay() {
        return scoreDisplay;
    }

    @Override
    public void act() {
        addNewBricksIfNeeded();
    }

    public void addNewBricksIfNeeded() {
        if (currentBrick != null) {
            // If brick has landed on the ground.
            if (currentBrick.isOnGround()) {
                addNewBricks();
            }
        } else {
            addNewBricks();
        }
    }

    public void addNewBricks() {
        IGameStrategy currentStrategy = GameStrategyProvider.getGameStrategy();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int numberOfBricks = currentStrategy.getNumberOfBricksFalling();
        int bucketSize = 14;
        for (int i = 0; i < numberOfBricks; i++) {
            Brick brick = new Brick(currentStrategy.getBrickSpeed());
            brick.attachObserver(this.scoreCalculator);
            currentBrick = brick;
            addComponent(brick, 268, 76);
            brick.setLocation(random.nextInt(0, bucketSize) * brick.getWidth() + brick.getWidth() / 2, 22);
        }
    }

    @Override
    public void notifyLevelCompleted() {
        GameOverScreen gameover = new GameOverScreen(scoreDisplay.getScore());
        Greenfoot.setWorld(gameover);
    }

    @Override
    public void notifyLevelDied() {
        GameOverScreen gameover = new GameOverScreen(scoreDisplay.getScore());
        Greenfoot.setWorld(gameover);
    }
}
