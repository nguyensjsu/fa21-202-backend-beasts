import java.util.ArrayList;

/**
 * Write a description of class ScoreCalculator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ScoreCalculator implements IScoreUpdateSubject, IBrickObserver, IPlayerObserver {
    static ArrayList<IScoreUpdateObserver> observers = new ArrayList<>();
    public  int updateScore = 0;
    private int numberOfBricks = 0;
    private static final int SCOREFACTOR = 1000;

    @Override
    public void increaseBricks(int bricks) {
        this.numberOfBricks = bricks;
    }

    public void attachObserver(IScoreUpdateObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IScoreUpdateObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(int score) {
        for (IScoreUpdateObserver sco : observers) {
            sco.updateScore(score);
        }
    }

    @Override
    public void notifyLevelCompleted() {
        IGameStrategy currentStrategy = GameStrategyProvider.getGameStrategy();
        if (currentStrategy instanceof EasyGameStrategy) {
            updateScore += (SCOREFACTOR / numberOfBricks) * 100;
        } else if (currentStrategy instanceof MediumGameStrategy) {
            updateScore += (SCOREFACTOR / numberOfBricks) * 150;
        } else {
            updateScore += (SCOREFACTOR / numberOfBricks) * 200;
        }
        notifyObservers(updateScore);
    }

    @Override
    public void notifyLevelDied() {
        updateScore = 0;
        notifyObservers(updateScore);
    }
}
