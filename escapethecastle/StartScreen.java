import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Landing screen of the game.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartScreen extends World
{

    /**
     * Constructor for objects of class StartScreen.
     * 
     */
    public StartScreen()
    {    
        // Create a new world with 700x500 cells with a cell size of 1x1 pixels.
        super(700, 500, 1); 
        prepare();
    }

    public void act() {
        if(Greenfoot.isKeyDown("Enter"))
            Greenfoot.setWorld(new MyWorld());
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        ButtonFactory buttonFactory = new ButtonFactory();

        Button startButton = buttonFactory.getButton("START");
        addObject(startButton,350,158);

        Button quitButton = buttonFactory.getButton("QUIT");
        addObject(quitButton,350,280);
    }
}
