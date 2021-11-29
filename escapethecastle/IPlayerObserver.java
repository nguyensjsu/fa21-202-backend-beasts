/**
 * Observer interface for listening to the player's state.
 */
public interface IPlayerObserver {
    void notifyLevelCompleted();

    void notifyLevelDied();

    void notifyLostLife();
}
