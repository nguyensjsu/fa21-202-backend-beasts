public class NotPlayingState implements IPlayerState {
    private Player player;

    NotPlayingState(Player player) {
        this.player = player;
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
        throw new IllegalStateException("Not possible");
    }

    @Override
    public void startPlaying(int totalNumberOfLives) {
        player.setPlayingState(totalNumberOfLives);
    }
}
