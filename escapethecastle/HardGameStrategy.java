/**
 * Strategy class for hard level game.
 */
public class HardGameStrategy implements IGameStrategy {
    @Override
    public int getBrickSpeed() {
        return 3;
    }

    @Override
    public int getNumberOfBricksFalling() {
        return 3;
    }
}
