import greenfoot.Greenfoot;
import greenfoot.World;

/**
 * Controller class for setting the game screens and supporting the navigations.
 */
public final class GameController {
    private static GameController gameController;
    private IScreen startScreen;
    private IScreen gameScreen;
    private IScreen gameOver;
    private ScoreDisplay scoreDisplay;
    private Player player;
    private ScoreCalculator scoreCalculator;
    private ScoreRepository scoreRepo;
    private Door door;

    public static GameController getInstance() {
        if (gameController == null) {
            gameController = new GameController();
        }
        return gameController;
    }

    public enum Screen {
        START_SCREEN,
        GAME_SCREEN,
        GAME_OVER_SCREEN,
    }

    private GameController() {
    }

    private void initialize() {
        player = PlayerSelector.getChosenPlayer();
        player.setTotalNumberOfLives(GameStrategyProvider.getGameStrategy().getNumberOfLives());
        scoreDisplay = new ScoreDisplay();
        scoreCalculator = new ScoreCalculator();
        scoreRepo = new ScoreRepository(Player.getPlayerName(), scoreDisplay);
        door = new Door();
        startScreen = new StartScreen();
        gameScreen = new GameScreen(scoreDisplay, player, door, scoreCalculator, GameStrategyProvider.getGameStrategy());

        // Attach the score calculator to notify for the score.
        player.attachObserver(scoreCalculator);
        player.attachObserver(scoreRepo);
        // Attach GameScreen as observer to listen the player's state.
        player.attachObserver((IPlayerObserver) gameScreen);
        player.getImage().setTransparency(255);
        scoreCalculator.attachObserver(scoreDisplay);
        player.setPlayingState(player.getTotalNumberOfLives());
    }

    /**
     * Sets the current screen on the front panel.
     */
    public void setScreen(Screen screen) {
        switch (screen) {
            case START_SCREEN:
                Greenfoot.setWorld((World) startScreen);
                break;
            case GAME_SCREEN:
                initialize();
                Greenfoot.setWorld((World) gameScreen);
                break;
            case GAME_OVER_SCREEN:
                gameOver = new GameOverScreen(scoreDisplay, Player.getPlayerName(), scoreRepo);
                Greenfoot.setWorld((World) gameOver);
        }
    }
}
