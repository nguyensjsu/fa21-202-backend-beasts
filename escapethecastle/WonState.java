public class WonState implements IPlayerState {
    private Player player;

    WonState(Player player) {
        this.player = player;
    }

    @Override
    public void lostLife() {
        throw new IllegalStateException("Not possible");
    }

    @Override
    public void won() {
        // Do nothing.
    }

    @Override
    public void died() {
        throw new IllegalStateException("Not possible");
    }

    @Override
    public void startPlaying(int totalNumberOfLives) {

    }
}
