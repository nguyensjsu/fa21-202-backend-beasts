import java.util.Objects;

/**
 * Display component for showing the player's score.
 */
public class PlayerScore implements Comparable<PlayerScore> {
    private final String name;
    private final int score;

    public PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(PlayerScore o) {
        return Integer.compare(o.getScore(), this.getScore());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerScore)) return false;
        PlayerScore that = (PlayerScore) o;
        return getScore() == that.getScore() && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getScore());
    }
}
