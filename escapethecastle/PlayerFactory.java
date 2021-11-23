public class PlayerFactory {

    public static Player getPlayer(PlayerType playerType) {
        switch (playerType) {
            case MARIO:
                return new Mario("images/mario.png", "images/mario_flipped.png");
            case WARIO:
                return new Wario("images/wario.png", "images/wario_flipped.png");
            case LUIGI:
                return new Luigi("images/luigi.png", "images/luigi_flipped.png");
        }
        throw new IllegalStateException("Invalid PlayerType " + playerType);
    }
}