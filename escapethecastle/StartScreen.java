import greenfoot.*;

public class StartScreen extends Screen {
    
    public static GreenfootSound bgm = new GreenfootSound("sounds/background-1.wav");

    /**
     * Constructor for objects of class StartScreen.
     */
    public StartScreen() {
        // Create a new world with 700x500 cells with a cell size of 1x1 pixels.
        super(700, 500, 1);
        prepare();
    }
    
    public void started() {
        bgm.playLoop();
    }

    public void act() {
        if (Greenfoot.isKeyDown("Enter"))
            Greenfoot.setWorld(new GameScreen());
    }


    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        String name = Greenfoot.ask("Enter Player Name");
        
        Player mario = PlayerFactory.getPlayer(PlayerType.MARIO);
        Player wario = PlayerFactory.getPlayer(PlayerType.WARIO);
        Player luigi = PlayerFactory.getPlayer(PlayerType.LUIGI);
        PlayerSelector.choosePlayer(mario); // Choose mario by default.

        PlayerOptionDecorator marioOption = PlayerOptionDecorator.wrapAround(mario);
        PlayerOptionDecorator warioOption = PlayerOptionDecorator.wrapAround(wario);
        PlayerOptionDecorator luigiOption = PlayerOptionDecorator.wrapAround(luigi);
        addComponent(marioOption, 260, 110);
        addComponent(warioOption, 335, 105);
        addComponent(luigiOption, 420, 100);
        marioOption.setPlayerName(name);
        warioOption.setPlayerName(name);
        luigiOption.setPlayerName(name);
        
        //Adding the easy medium and difficult levels.
        EasyLevelButton easyLevel = new EasyLevelButton();
        addComponent(easyLevel, 250, 200);
        MediumLevelButton mediumLevel = new MediumLevelButton();
        addComponent(mediumLevel, 330, 200);
        HardLevelButton hardLevel = new HardLevelButton();
        addComponent(hardLevel, 420, 200);

        ButtonFactory buttonFactory = new ButtonFactory();
        Button startButton = buttonFactory.getButton("START");
        addComponent(startButton, 270, 280);

        Button quitButton = buttonFactory.getButton("QUIT");
        addComponent(quitButton, 420, 280);
    }
}
