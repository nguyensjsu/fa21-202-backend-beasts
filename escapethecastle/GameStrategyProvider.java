/**
 * Singleton class to provide the game strategy.
 */
public class GameStrategyProvider {
    private IGameStrategy gameStrategy;

    private static GameStrategyProvider instance;

    public static GameStrategyProvider getInstance() {
        if (instance == null) {
            instance = new GameStrategyProvider();
        }
        return instance;
    }

    public static IGameStrategy getGameStrategy() {
        return getInstance().gameStrategy;
    }

    public static void setGameStrategy(IGameStrategy strategy) {
        getInstance().gameStrategy = strategy;
    }

    private GameStrategyProvider() {
        this.gameStrategy = new EasyGameStrategy();
    }
}
