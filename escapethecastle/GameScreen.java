/**
 * Write a description of class MyWorld here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameScreen extends Screen implements IPlayerObserver {

    private Brick currentBrick;
    private final ScoreCalculator scoreCalculator;
    private final ScoreDisplay scoreDisplay;
    private final Door door;
    private final Player player;
    public static int width = 700;
    public static int height = 500;
    public static final int BUCKET_SIZE = 14;

    /**
     * Constructor for objects of class MyWorld.
     */
    public GameScreen(ScoreCalculator scoreCalculator, ScoreDisplay scoreDisplay, Door door, Player player, int w, int h) {
        super(w, h, 1);
        this.scoreCalculator = scoreCalculator;
        this.scoreDisplay = scoreDisplay;
        this.door = door;
        this.player = player;
        width = w;
        height = h;
        prepare();
    }

    public GameScreen(ScoreDisplay scoreDisplay, Player player, Door door, ScoreCalculator scoreCalculator) {
        // Create a new world with 700x500 cells with a cell size of 1x1 pixels.
        // Change the brick's size too if you change this.
        this(scoreCalculator, scoreDisplay, door, player, 700, 500);
    }

    private void prepare() {
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
        int numberOfBricks = currentStrategy.getNumberOfBricksFalling();
        for (int i = 0; i < numberOfBricks; i++) {
            Brick brick = new Brick(currentStrategy.getBrickSpeed());
            brick.attachObserver(this.scoreCalculator);
            currentBrick = brick;
            addComponent(brick, 268, 76);
            brick.initializeLocation();
        }
    }

    @Override
    public void notifyLevelCompleted() {
        setGameOverScreen();
    }

    @Override
    public void notifyLevelDied() {
        setGameOverScreen();
    }

    private void setGameOverScreen() {
        GameController.getInstance().setScreen(GameController.Screen.GAME_OVER_SCREEN);
    }
}
