import greenfoot.Greenfoot;

/**
 * Controller class for setting the game screens and supporting the navigations.
 */
public final class GameController {
    private static GameController gameController;
    private GameScreen gameScreen;
    private GameOverScreen gameOver;
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
        scoreDisplay = new ScoreDisplay();
        scoreCalculator = new ScoreCalculator();
        scoreRepo = new ScoreRepository(Player.getPlayerName(), scoreDisplay);
        door = new Door();
        gameScreen = new GameScreen(scoreDisplay, player, door, scoreCalculator);

        // Attach the score calculator to notify for the score.
        player.attachObserver(scoreCalculator);
        player.attachObserver(scoreRepo);
        // Attach GameScreen as observer to listen the player's state.
        player.attachObserver(gameScreen);
        player.getImage().setTransparency(255);
        scoreCalculator.attachObserver(scoreDisplay);
    }

    /**
     * Sets the current screen on the front panel.
     */
    public void setScreen(Screen screen) {
        switch (screen) {
            case START_SCREEN:
                Greenfoot.setWorld(new StartScreen());
                break;
            case GAME_SCREEN:
                initialize();
                Greenfoot.setWorld(gameScreen);
                break;
            case GAME_OVER_SCREEN:
                gameOver = new GameOverScreen(scoreDisplay, Player.getPlayerName(), scoreRepo);
                Greenfoot.setWorld(gameOver);
        }
    }
}
