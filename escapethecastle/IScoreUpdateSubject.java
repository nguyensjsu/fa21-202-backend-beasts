/**
 * Subject interface for score updates.
 */
public interface IScoreUpdateSubject {
    void attachObserver(IScoreUpdateObserver observer);

    void removeObserver(IScoreUpdateObserver observer);

    void notifyObservers(int score);
}
