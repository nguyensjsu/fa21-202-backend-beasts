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
        IGameStrategy currentStrategy = GameStrategyProvider.getGameStrategy();
        int brickSpeed = currentStrategy.getBrickSpeed();
        switch(brickSpeed) {
            case 1: this.updateScore += score * 10;
            break;
            case 2: this.updateScore += score * 25;
            break;
            case 4: this.updateScore += score * 50;
            break;
        }
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
