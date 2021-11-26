import greenfoot.Greenfoot;

public class GameController {
    private static GameController gameController;
    private StartScreen startScreen;
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
        scoreRepo = new ScoreRepository(player.getPlayerName(), scoreDisplay);
        door = new Door();
        startScreen = new StartScreen();
        gameScreen = new GameScreen(scoreDisplay, player, door, scoreCalculator);

        // Attach the score calculator to notify for the score.
        player.attachObserver(scoreCalculator);
        player.attachObserver(scoreRepo);
        // Attach GameScreen as observer to listen the player's state.
        player.attachObserver(gameScreen);
        player.getImage().setTransparency(255);
        scoreCalculator.attachObserver(scoreDisplay);
    }

    public void setScreen(Screen screen) {
        switch (screen) {
            case START_SCREEN:
                Greenfoot.setWorld(startScreen);
                break;
            case GAME_SCREEN:
                initialize();
                Greenfoot.setWorld(gameScreen);
                break;
            case GAME_OVER_SCREEN:
                gameOver = new GameOverScreen(scoreDisplay, player.getPlayerName(), scoreRepo);
                Greenfoot.setWorld(gameOver);
        }
    }
}
