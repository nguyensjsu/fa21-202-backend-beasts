import java.util.List;

/**
 * Write a description of class GameOverScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameOverScreen extends Screen {
    private int score;
    private String playerName;
    private ScoreRepository scoreRepository;

    /**
     * Constructor for objects of class GameOverScreen
     */
    public GameOverScreen(int score, String playerName, ScoreRepository scoreRepository) {
        super(700, 500, 1);
        this.score = score;
        this.playerName = playerName;
        this.scoreRepository = scoreRepository;
        prepare();
    }

    private String getResult(int score) {
        if (score == 0) {
            return "Sorry... You Lose!";
        }
        return "Congratulations!! You Win!";
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        ScoreBoardBackground scoreBoardBackground = new ScoreBoardBackground();
        ScoreBoardTitle scoreBoardTitle = new ScoreBoardTitle();
        String result = getResult(this.score);
        ScoreBoardResult scoreBoardResult = new ScoreBoardResult(result);
        Score userScore = new Score(this.score);
        PlayerName player = new PlayerName(playerName);
        Rank rank = new Rank(scoreRepository.getCurrentPlayerRank(playerName, GameStrategyProvider.getGameStrategy()));

        addComponent(scoreBoardBackground, getHeight(), getWidth());
        addComponent(scoreBoardTitle, getWidth() / 2, 50);
        addComponent(player, getWidth() / 2, 100);
        addComponent(scoreBoardResult, getWidth() / 2, 140);
        addComponent(userScore, getWidth() / 2, 180);
        addComponent(rank, getWidth() / 2, 220);
        showText("High Scores", getWidth() / 2, 260);
        showText("Player     |      Score", getWidth() / 2, 300);
        List<PlayerScore> topThree = scoreRepository.getTopThree(GameStrategyProvider.getGameStrategy());
        decorateResult(topThree);

        ButtonFactory buttonFactory = new ButtonFactory();
        Button replayButton = buttonFactory.getButton("REPLAY");
        addComponent(replayButton, getWidth() / 2, 460);
    }

    private void decorateResult(List<PlayerScore> topThree) {
        int x = 285;
        int y = 330;
        int dx = 70;
        int dy = 35;
        for (PlayerScore ps : topThree) {
            showText(ps.getName(), x, y);
            showText(String.valueOf(ps.getScore()), x + (2 * dx), y);
            y = y + dy;
        }

    }

}
