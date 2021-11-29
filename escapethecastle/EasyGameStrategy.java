/**
 * Game strategy for easy game level.
 */
public class EasyGameStrategy implements IGameStrategy {
    @Override
    public int getBrickSpeed() {
        return 12;
    }

    @Override
    public int getNumberOfBricksFalling() {
        return 1;
    }

    @Override
    public int getNumberOfLives() {
        return 1;
    }
}
