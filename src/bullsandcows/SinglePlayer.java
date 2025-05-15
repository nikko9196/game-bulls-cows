package bullsandcows;

import java.util.ArrayList;
import java.util.List;

public class SinglePlayer extends GameModeStrategy {
    private final Player humanPlayer;
    private List<GuessRecord> guessRecords;
    private String computerSecretCode;
    private String winner;

    public SinglePlayer(Player humanPlayer) {
        this.humanPlayer = humanPlayer;
        this.guessRecords = new ArrayList<>();
    }

    @Override
    public void play() {
        // Intro of the game mode Single Player:
        Computer computer = new EasyComputer();
        int attemptRemaining = 7;
        System.out.println("Starting Single Player Mode ...");
        System.out.println(computer.getName() + " is generating the secret code ...");
        computerSecretCode = computer.generateSecretCode();
        System.out.println(computer.getName() + " has a secret code now! You have 7 attempts to guess the code.");

        // While the user still has attempts: Check their guesses and see if they win:
        while (attemptRemaining > 0) {
            boolean hasWon = handleGuessProcess(computerSecretCode, humanPlayer, guessRecords);
            if (hasWon) {
                winner = humanPlayer.getName();
                return;
            }
            attemptRemaining--;
            System.out.println("Your remaining attempt(s): " + attemptRemaining);
            System.out.println("--------------------------------------------");

            // When the user runs out of attempts: They lose.
            if (attemptRemaining == 0) {
                System.out.println("GAME OVER! You've run out of attempts! The correct secret code was: " + computerSecretCode);
                winner = computer.getName();
            }
        }
    }

    public List<GuessRecord> getGuessRecords() {
        return guessRecords;
    }

    public String getWinner() {
        return winner;
    }

    public String getComputerSecretCode() {
        return computerSecretCode;
    }


}
