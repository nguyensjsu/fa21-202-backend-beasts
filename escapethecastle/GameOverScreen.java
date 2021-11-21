import greenfoot.Greenfoot;
/**
 * Write a description of class GameOverScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverScreen extends Screen {
    ScoreBoardButton scoreBoardButton;
    private int score;
    /**
     * Constructor for objects of class GameOverScreen
     */
    public GameOverScreen(int score) {
        super(700, 500, 1);
        this.score = score;
        prepare();
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(scoreBoardButton)) {
            Greenfoot.setWorld(new StartScreen());
        }
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
        scoreBoardButton = new ScoreBoardButton();
        String result = getResult(this.score);
        ScoreBoardResult scoreBoardResult = new ScoreBoardResult(result);
        Score userScore = new Score(this.score);
        
        addObject(scoreBoardBackground, getHeight(), getWidth());
        addObject(scoreBoardTitle, 250, 50);
        addObject(scoreBoardResult, 280, 200);
        addObject(userScore, 230, 350);
        addObject(scoreBoardButton, 250, 450);
    }
}
