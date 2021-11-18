import greenfoot.Greenfoot;

public class StartScreen extends Screen {

    /**
     * Constructor for objects of class StartScreen.
     */
    public StartScreen() {
        // Create a new world with 700x500 cells with a cell size of 1x1 pixels.
        super(700, 500, 1);
        prepare();
    }

    public void act() {
        if (Greenfoot.isKeyDown("Enter"))
            Greenfoot.setWorld(new MyWorld());
    }


    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {

        // Adding different characters for the game
        PlayerFactory playerFactory = new PlayerFactory();
        Player mario = playerFactory.getPlayer(PlayerType.MARIO);
        addComponent(mario, 250, 110);
        Player wario = playerFactory.getPlayer(PlayerType.WARIO);
        addComponent(wario, 330, 110);
        Player luigi = playerFactory.getPlayer(PlayerType.LUIGI);
        addComponent(luigi, 420, 110);

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
