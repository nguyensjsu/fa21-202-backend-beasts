import greenfoot.Greenfoot;
/**
 * Write a description of class GameOverScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverScreen extends Screen {
    private int score;
    private String playerName;
    /**
     * Constructor for objects of class GameOverScreen
     */
    public GameOverScreen(int score, String playerName) {
        super(700, 500, 1);
        this.score = score;
        this.playerName = playerName;
        prepare();
    }

    private String getResult(int score) {
        if(score == 0) {
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
        
        addObject(scoreBoardBackground, getHeight(), getWidth());
        addObject(scoreBoardTitle, getWidth() / 2, 50);
        addObject(player, getWidth() / 2, 180);
        addObject(scoreBoardResult, getWidth() / 2, 250);
        addObject(userScore, getWidth() / 2, 320);
        
        ButtonFactory buttonFactory = new ButtonFactory();
        Button replayButton = buttonFactory.getButton("REPLAY");
        addComponent(replayButton, getWidth() / 2, 420);
    }
}
