/**
 * Class for medium game strategy.
 */
public class MediumGameStrategy implements IGameStrategy {
    @Override
    public int getBrickSpeed() {
        return 10;
    }

    @Override
    public int getNumberOfBricksFalling() {
        return 2;
    }
}
