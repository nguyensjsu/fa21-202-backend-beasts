import greenfoot.Greenfoot;
/**
 * Write a description of class GameOverScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverScreen extends Screen {
    ScoreBoardButton scoreBoardButton;
    /**
     * Constructor for objects of class GameOverScreen
     */
    public GameOverScreen() {
        super(700, 500, 1);
        prepare();
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(scoreBoardButton)) {
            Greenfoot.setWorld(new MyWorld());
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        ScoreBoardBackground scoreBoardBackground = new ScoreBoardBackground();
        ScoreBoardTitle scoreBoardTitle = new ScoreBoardTitle();
        scoreBoardButton = new ScoreBoardButton();
        
        addObject(scoreBoardBackground, 0, 0);
        addObject(scoreBoardTitle, 250, 50);
        addObject(scoreBoardButton, 250, 400);
    }
}
