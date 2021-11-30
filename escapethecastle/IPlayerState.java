public interface IPlayerState {
    void startPlaying(int totalNumberOfLives);

    void lostLife();

    void won();

    void died();
}
