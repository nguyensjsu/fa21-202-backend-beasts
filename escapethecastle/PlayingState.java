import greenfoot.Greenfoot;

public class PlayingState implements IPlayerState {
    private final Player player;
    private int totalNumberOfLives;
    private int numberOfLivesLeft;

    PlayingState(Player player) {
        this.player = player;
    }

    @Override
    public void lostLife() {
        if (numberOfLivesLeft <= 0) {
            player.died();
        }
        player.notifyObservers(PlayerState.LOST_LIFE);
        player.setLocation(numberOfLivesLeft * player.getWidth() / 2 - 2 * (totalNumberOfLives - numberOfLivesLeft), player.getHeight() / 2);
        player.setvSpeed(5);
        numberOfLivesLeft--;
        Greenfoot.delay(15);
    }

    @Override
    public void won() {
        player.setWonState();
        player.notifyObservers(PlayerState.WON);
    }

    @Override
    public void died() {
        player.setDiedState();
        StartScreen.BACKGROUND_MUSIC.stop();
        Player.gameOverSound.play();
        player.notifyObservers(PlayerState.DIED);
    }

    @Override
    public void startPlaying(int totalNumberOfLives) {
        // Do nothing.
    }

    public void setNumberOfLives(int totalNumberOfLives) {
        this.totalNumberOfLives = totalNumberOfLives;
        numberOfLivesLeft = totalNumberOfLives;
    }
}
