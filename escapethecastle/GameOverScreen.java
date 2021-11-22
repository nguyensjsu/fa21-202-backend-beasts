import greenfoot.Greenfoot;
/**
 * Write a description of class GameOverScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverScreen extends Screen {
    private int score;
    /**
     * Constructor for objects of class GameOverScreen
     */
    public GameOverScreen(int score) {
        super(700, 500, 1);
        this.score = score;
        prepare();
    }

    private String getResult(int score) {
        if(score > 50) {
            return "Congratulations!! You Win!";
        }
        return "Sorry... You Lose!";
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
        
        addObject(scoreBoardBackground, getHeight(), getWidth());
        addObject(scoreBoardTitle, getWidth() / 2, 50);
        addObject(scoreBoardResult, getWidth() / 2, 200);
        addObject(userScore, getWidth() / 2, 300);
        
        ButtonFactory buttonFactory = new ButtonFactory();
        Button replayButton = buttonFactory.getButton("REPLAY");
        addComponent(replayButton, getWidth() / 2, 400);
    }
}
