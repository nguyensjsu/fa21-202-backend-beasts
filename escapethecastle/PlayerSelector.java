/**
 * A singleton to provide the chosen player.
 */
public class PlayerSelector {
    private Player player;

    private static PlayerSelector instance;

    public static PlayerSelector getInstance() {
        if (instance == null) {
            instance = new PlayerSelector();
        }
        return instance;
    }

    public static Player getChosenPlayer() {
        return getInstance().player;
    }

    public static void choosePlayer(Player chosen) {
        getInstance().player = chosen;
    }

    private PlayerSelector() {
    }
}
