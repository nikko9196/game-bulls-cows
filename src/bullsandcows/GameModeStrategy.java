package bullsandcows;

import java.util.List;

public interface GameModeStrategy {
    public void play();

    public List<GuessRecord> getGuessRecords();

    public String getWinner();

    public String getComputerSecretCode();
}
