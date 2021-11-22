/**
 * Write a description of interface IScoreUpdate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IScoreUpdateSubject  
{
    void attachObserver(IScoreUpdateObserver observer);
    void removeObserver(IScoreUpdateObserver observer);
    void notifyObservers(int score);
}
