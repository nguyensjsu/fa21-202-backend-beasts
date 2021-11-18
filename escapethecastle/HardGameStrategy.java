public class HardGameStrategy implements IGameStrategy {
    @Override
    public int getBrickSpeed() {
        return 4;
    }

    @Override
    public int getNumberOfBricksFalling() {
        return 3;
    }
}
