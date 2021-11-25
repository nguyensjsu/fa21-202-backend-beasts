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
    public static int width = 0;
    public static int height = 0;
    public static int bucketSize = 15;

    /**
     * Constructor for objects of class MyWorld.
     */
    public GameScreen(int width, int height) {
        // Create a new world with 700x500 cells with a cell size of 1x1 pixels.
        // Change the brick's size too if you change this.
        super(width, height, 1);
        this.width = width;
        this.height = height;
        prepare();
    }
    
    public GameScreen() {
        // Create a new world with 700x500 cells with a cell size of 1x1 pixels.
        // Change the brick's size too if you change this.
        super(700, 500, 1);
        this.width = 700;
        this.height = 500;
        prepare();
    }

    private void prepare() {
        player = PlayerSelector.getChosenPlayer();
        scoreDisplay = new ScoreDisplay();
        scoreCalculator = new ScoreCalculator();
        door = new Door();

        // Attach the score calculator to notify for the score.
        player.attachObserver(scoreCalculator);
        // Attach GameScreen as observer to listen the player's state.
        player.attachObserver(this);
        player.getImage().setTransparency(255);
        scoreCalculator.attachObserver(scoreDisplay);

        addComponent(door, getWidth() - door.getWidth() / 3, getHeight() - door.getHeight() / 2);
        addComponent(scoreDisplay, 600, 10);
        addComponent(player, 69, 445);
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
        for (int i = 0; i < numberOfBricks; i++) {
            Brick brick = new Brick(currentStrategy.getBrickSpeed());
            brick.attachObserver(this.scoreCalculator);
            currentBrick = brick;
            addComponent(brick, 268, 76);
            brick.setLocation(random.nextInt(0, GameScreen.bucketSize) * brick.getWidth() + brick.getWidth() / 2, 22);
        }
    }

    @Override
    public void notifyLevelCompleted() {
        GameOverScreen gameover = new GameOverScreen(scoreDisplay.getScore(), player.getPlayerName());
        Greenfoot.setWorld(gameover);
    }

    @Override
    public void notifyLevelDied() {
        GameOverScreen gameover = new GameOverScreen(scoreDisplay.getScore(), player.getPlayerName());
        Greenfoot.setWorld(gameover);
    }
}
