import java.util.List;

/**
 * Screen for showing the content when game is over, either lost or won.
 */
public class GameOverScreen extends Screen {
    private final ScoreRepository scoreRepository;
    private final ScoreDisplay scoreDisplay;
    private final String playerName;

    /**
     * Constructor for objects of class GameOverScreen
     */
    public GameOverScreen(ScoreDisplay scoreDisplay, String playerName, ScoreRepository scoreRepository) {
        super(700, 500, 1);
        this.scoreDisplay = scoreDisplay;
        this.playerName = playerName;
        this.scoreRepository = scoreRepository;
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        ScoreBoardBackground scoreBoardBackground = new ScoreBoardBackground();
        ScoreBoardTitle scoreBoardTitle = new ScoreBoardTitle();
        String result = getResult(scoreDisplay.getScore());
        ScoreBoardResult scoreBoardResult = new ScoreBoardResult(result);
        Score userScore = new Score(scoreDisplay.getScore());
        PlayerName player = new PlayerName(playerName);
        Rank rank = new Rank(scoreRepository.getCurrentPlayerRank(playerName, GameStrategyProvider.getGameStrategy()));

        addComponent(scoreBoardBackground, getHeight(), getWidth());
        addComponent(scoreBoardTitle, getWidth() / 2, 50);
        addComponent(player, getWidth() / 2, 100);
        addComponent(scoreBoardResult, getWidth() / 2, 140);
        addComponent(userScore, getWidth() / 2, 180);
        addComponent(rank, getWidth() / 2, 220);

        showTopThreePlayers();

        ButtonFactory buttonFactory = new ButtonFactory();
        Button replayButton = buttonFactory.getButton("REPLAY");
        addComponent(replayButton, getWidth() / 2, 460);
    }

    private void showTopThreePlayers() {
        List<PlayerScore> topThree = scoreRepository.getTopThree(GameStrategyProvider.getGameStrategy());
        int x = 285;
        int y = 330;
        int dx = 70;
        int dy = 35;
        showText("High Scores", getWidth() / 2, 260);
        showText("Player     |      Score", getWidth() / 2, 300);
        for (PlayerScore ps : topThree) {
            showText(ps.getName(), x, y);
            showText(String.valueOf(ps.getScore()), x + (2 * dx), y);
            y = y + dy;
        }

    }

    private String getResult(int score) {
        if (score == 0) {
            return "Sorry... You Lose!";
        }
        return "Congratulations!! You Win!";
    }

}
