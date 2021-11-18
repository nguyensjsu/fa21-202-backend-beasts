public class EasyGameStrategy implements IGameStrategy {
    @Override
    public int getBrickSpeed() {
        return 1;
    }

    @Override
    public int getNumberOfBricksFalling() {
        return 1;
    }
}
