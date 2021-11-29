/**
 * Singleton class to provide the game strategy.
 */
public class GameStrategyProvider {
    private static IGameStrategy gameStrategy;

    public static IGameStrategy getGameStrategy() {
        if (gameStrategy == null) {
            return new EasyGameStrategy();
        }
        return gameStrategy;
    }

    public static void setGameStrategy(IGameStrategy strategy) {
        gameStrategy = strategy;
    }

    private GameStrategyProvider() {
    }
}
