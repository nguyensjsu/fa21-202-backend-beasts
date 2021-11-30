/**
 * A factory to create the players for the game.
 */
public final class PlayerFactory {

    static Player player = null;
    public static Player getPlayer(PlayerType playerType) {
        switch (playerType) {
            case MARIO:
                player = new Mario("images/mrun.png", "images/mario.png");
                return player;
            case WARIO:
                player = new Wario("images/wrun.png", "images/wario.png");
                return player;
            case LUIGI:
                player = new Luigi("images/lrun.png", "images/luigi.png");
                return player;
        }
        throw new IllegalStateException("Invalid PlayerType " + playerType);
    }

    private PlayerFactory() {
    }
}