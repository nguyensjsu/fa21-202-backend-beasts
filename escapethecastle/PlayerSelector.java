/**
 * A singleton to provide the chosen player.
 */
public class PlayerSelector {
    private static Player player;

    public static Player getChosenPlayer() {
        return player;
    }

    public static void choosePlayer(Player chosen) {
        player = chosen;
    }

    private PlayerSelector() {
    }
}
