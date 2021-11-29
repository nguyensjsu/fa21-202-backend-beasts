import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Screen when game is running.
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
    private int brickCoolDown = 120;
    private List<PlayerLife> lives;

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
        lives = new ArrayList<>();
        lives.add(new PlayerLife(player));
        lives.add(new PlayerLife(player));
        prepare();
    }

    public GameScreen(ScoreDisplay scoreDisplay, Player player, Door door, ScoreCalculator scoreCalculator) {
        // Create a new world with 700x500 cells with a cell size of 1x1 pixels.
        // Change the brick's size too if you change this.
        this(scoreCalculator, scoreDisplay, door, player, 700, 500);
    }

    private void prepare() {
        addComponent(door, getWidth() - door.getWidth() / 3, getHeight() - 6 * door.getHeight() / 2);
        addComponent(scoreDisplay, 600, 10);
        int x = 0;
        for (int i = 0, livesSize = lives.size(); i < livesSize; i++) {
            PlayerLife pl = lives.get(i);
            addComponent(pl, x + pl.getWidth() / 2, pl.getHeight() / 2);
            x += pl.getWidth() + 2;
        }
        addComponent(player, x, player.getHeight() / 2);
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
                while (brickCoolDown-- > 0) {
                }
                addNewBricks();
                brickCoolDown = 120;
            }
        } else {
            addNewBricks();
        }
    }

    public void addNewBricks() {
        IGameStrategy currentStrategy = GameStrategyProvider.getGameStrategy();
        int numberOfBricks = currentStrategy.getNumberOfBricksFalling();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < numberOfBricks; i++) {
            Brick brick = new Brick(currentStrategy.getBrickSpeed(), random);
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

    @Override
    public void notifyLostLife() {
        if (lives.isEmpty()) return;
        PlayerLife lifeLost = lives.get(lives.size() - 1);
        //lifeLost.getImage().setTransparency(0);
        //lifeLost.getWorld().removeObject(lifeLost);
        removeObject(lifeLost);
        lives.remove(lifeLost);
    }

    private void setGameOverScreen() {
        GameController.getInstance().setScreen(GameController.Screen.GAME_OVER_SCREEN);
    }
}
