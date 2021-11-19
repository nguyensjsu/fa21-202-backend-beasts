public class PlayerSelector {
    private static PlayerType playerType = PlayerType.MARIO;

    public static Player getPlayer() {
        return PlayerFactory.getPlayer(playerType);
    }

    public static void choosePlayer(PlayerType type) {
        playerType = type;
    }

    public static PlayerType getPlayerType() {
        return playerType;
    }
}
