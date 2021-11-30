public class DiedState implements IPlayerState {
    private Player player;

    public DiedState(Player player) {
        this.player = player;
    }

    @Override
    public void startPlaying(int totalNumberOfLives) {
        throw new IllegalStateException("Not possible");
    }

    @Override
    public void lostLife() {
        throw new IllegalStateException("Not possible");
    }

    @Override
    public void won() {
        throw new IllegalStateException("Not possible");
    }

    @Override
    public void died() {
        // Do nothing.
    }
}
