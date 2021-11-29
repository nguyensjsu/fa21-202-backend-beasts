/**
 * Strategy class for hard level game.
 */
public class HardGameStrategy implements IGameStrategy {
    @Override
    public int getBrickSpeed() {
        return 10;
    }

    @Override
    public int getNumberOfBricksFalling() {
        return 3;
    }
}
