import java.util.ArrayList;
/**
 * Write a description of class ScoreCalculator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreCalculator implements IScoreUpdateSubject, IBrickObserver
{
    static ArrayList<IScoreUpdateObserver> observers = new ArrayList<>();
    public int updateScore;
    
    public int getScore() {
        return updateScore;
    }
    
    public void setScore(int score) {
        this.updateScore += score;
        notifyObservers(this.updateScore);
    }
    
    public void attachObserver(IScoreUpdateObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(IScoreUpdateObserver observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers(int score) {
        for (IScoreUpdateObserver sco: observers) {
            sco.updateScore(score);
        }
    }
}
