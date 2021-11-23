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
    private static final int SCOREFACTOR = 1000;

    public int getScore() {
        return updateScore;
    }

    public void setScore(int score) {
        IGameStrategy currentStrategy = GameStrategyProvider.getGameStrategy();
        int brickSpeed = currentStrategy.getBrickSpeed();
        switch (brickSpeed) {
            case 1:
                this.updateScore += score * 10;
                break;
            case 2:
                this.updateScore += score * 25;
                break;
            case 4:
                this.updateScore += score * 50;
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
        for (IScoreUpdateObserver sco : observers) {
            sco.updateScore(score);
        }
    }

    @Override
    public void notifyLevelCompleted() {
        IGameStrategy currentStrategy = GameStrategyProvider.getGameStrategy();
        //TODO(Mayank): Use a variable numberOfBricks in the class and increment them from Brick.notifyOObservers.
        int numberOfBricks = 60;
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
