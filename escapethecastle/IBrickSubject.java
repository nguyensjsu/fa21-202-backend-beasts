/**
 * Write a description of interface IBrickSubject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IBrickSubject  
{
    void attachObserver(IBrickObserver observer);
    void removeObserver(IBrickObserver observer);
    void notifyObservers(int score);
}
