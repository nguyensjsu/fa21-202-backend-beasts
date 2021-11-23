public interface IPlayerSubject {

    void attachObserver(IPlayerObserver observer);

    void removeObserver(IPlayerObserver observer);

    void notifyObservers(PlayerFinalState playerFinalState);
}
