import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to manage the score repository.
 */
public class ScoreRepository implements IPlayerObserver {
    private final String playerName;
    private final ScoreDisplay scoreDisplay;

    public ScoreRepository(String playerName, ScoreDisplay scoreDisplay) {
        this.playerName = playerName;
        this.scoreDisplay = scoreDisplay;
    }

    /**
     * Write scores to the file.
     */
    public void writeScores(int score) {
        IGameStrategy gameStrategy = GameStrategyProvider.getGameStrategy();
        File file = getFile(gameStrategy);
        try (FileWriter fr = new FileWriter(file, true); BufferedWriter br = new BufferedWriter(fr)) {
            br.write(playerName + ", " + score);
            br.newLine();
            br.flush();
        } catch (Exception e) {
            System.err.println("Some error occurred!");
        }
    }

    /**
     * Reads file for a game strategy.
     */
    public List<PlayerScore> readFile(IGameStrategy gameStrategy) {
        String line;
        List<PlayerScore> playerScores = new ArrayList<>();
        File file;
        file = getFile(gameStrategy);
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            line = br.readLine();
            while (line != null) {
                String[] s = line.split(", ");
                playerScores.add(new PlayerScore(s[0].trim(), Integer.parseInt(s[1].trim())));
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(playerScores);
        return playerScores;
    }

    private File getFile(IGameStrategy gameStrategy) {
        File file;
        if (gameStrategy instanceof EasyGameStrategy) {
            file = new File("scores_easy.txt");
        } else if (gameStrategy instanceof MediumGameStrategy) {
            file = new File("scores_medium.txt");
        } else {
            file = new File("scores_hard.txt");
        }
        return file;
    }

    public List<PlayerScore> getTopThree(IGameStrategy gameStrategy) {
        List<PlayerScore> scoreResult = readFile(gameStrategy);
        int size = scoreResult.size();
        return scoreResult.subList(0, Math.min(size, 3));
    }

    public int getCurrentPlayerRank(String playerName, IGameStrategy gameStrategy) {
        List<PlayerScore> scoreResult = readFile(gameStrategy);
        int rank = 1;
        for (PlayerScore p : scoreResult) {
            if (p.getName().equals(playerName)) {
                return rank;
            }
            rank++;
        }
        return rank;
    }

    @Override
    public void notifyLevelCompleted() {
        writeScores(scoreDisplay.getScore());
    }

    @Override
    public void notifyLevelDied() {
        writeScores(scoreDisplay.getScore());
    }
}
