/**
 * Subject for the brick fall.
 */
public interface IBrickSubject {
    void attachObserver(IBrickObserver observer);

    void removeObserver(IBrickObserver observer);

    void notifyObservers();
}
