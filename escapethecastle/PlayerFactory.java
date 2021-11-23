public class PlayerFactory {

    public static Player getPlayer(PlayerType playerType) {
        switch (playerType) {
            case MARIO:
                return new Mario("images/Mario.png");
            case WARIO:
                return new Wario("images/Wario.png");
            case LUIGI:
                return new Luigi("images/Luigi.png");
        }
        throw new IllegalStateException("Invalid PlayerType " + playerType);
    }
}