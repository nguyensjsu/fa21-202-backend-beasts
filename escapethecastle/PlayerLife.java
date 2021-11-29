public class PlayerLife extends DisplayComponent{
    private final Player wrappedPlayer;
    public PlayerLife(Player player){
        wrappedPlayer = player;
        setImage(wrappedPlayer.getImage());
    }

}
