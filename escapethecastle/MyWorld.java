import greenfoot.World;

/**
 * Write a description of class MyWorld here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MyWorld extends World {

    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(700, 500, 1);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        Character character = new Character();
        addObject(character, 140, 228);
        character.setLocation(69, 445);
        IGameStrategy currentStrategy = GameStrategyProvider.getGameStrategy();
        Brick brick = new Brick(currentStrategy.getBrickSpeed());
        addObject(brick, 268, 76);
        brick.setLocation(271, 22);
    }
}
