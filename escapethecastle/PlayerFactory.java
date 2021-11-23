public class PlayerFactory {

    public static Player getPlayer(PlayerType playerType) {
        switch (playerType) {
            case MARIO:
                return new Mario("images/mrun.png", "images/mario.png");
            case WARIO:
                return new Wario("images/wrun.png", "images/wario.png");
            case LUIGI:
                return new Luigi("images/lrun.png", "images/luigi.png");
        }
        throw new IllegalStateException("Invalid PlayerType " + playerType);
    }
}