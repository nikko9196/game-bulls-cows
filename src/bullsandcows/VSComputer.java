package bullsandcows;

import java.util.ArrayList;
import java.util.List;

public class VSComputer extends GameModeStrategy {
    private final Player humanPlayer;
    private final Computer opponent;
    private List<GuessRecord> guessRecords;
    private String computerSecretCode;
    String playerSecretCode;
    private String winner;

    public VSComputer(Player humanPlayer, Computer opponent) {
        this.humanPlayer = humanPlayer;
        this.opponent = opponent;
        this.guessRecords = new ArrayList<>();
    }

    @Override
    public void play() {
        int humanAttempts = 7;
        int computerAttempts = 7;

        // Intro of the game mode Player vs Computer:
        System.out.println("Starting Player vs Computer Mode ...");
        System.out.println(opponent.getName() + " is generating the secret code ...");
        computerSecretCode = opponent.generateSecretCode();
        System.out.println(opponent.getName() + " has a secret code now!");

        // Let the user enter their secret code and validate it:
        System.out.println("It is your turn to choose your secret code, which should be 4-digit number with all digits are unique, and has no spacing.");
        while (true) {
            System.out.println("Please enter your secret code: ");
            playerSecretCode = Keyboard.readInput().trim();
            if (CodeValidation.isValidCode(playerSecretCode)) {
                break;
            }
            System.out.println("Invalid secret code! It should be 4-digit number with all digits are unique, and has no spacing.");
        }

        // Turn decision:
        System.out.println(humanPlayer.getName() + " and " + opponent.getName() + " - Each has 7 attempts to guess the code.");
        System.out.println("Let's randomly decide who will go first ...");
        boolean isHumanTurn = Math.random() < 0.5;

        while (humanAttempts > 0 || computerAttempts > 0) {
            // Human's turn: Handle the turn and check if the user wins:
            if (isHumanTurn && humanAttempts > 0) {
                System.out.println(humanPlayer.getName() + "'s turn.");

                boolean hasWon = handleGuessProcess(computerSecretCode, humanPlayer, guessRecords);
                if (hasWon) {
                    winner = humanPlayer.getName();
                    return;
                }
                humanAttempts--;
                System.out.println("Your remaining attempt(s): " + humanAttempts);
                System.out.println("--------------------------------------------");

            }

            // Computer's turn: Handle the turn and check if the computer wins:
            else if (!isHumanTurn && computerAttempts > 0) {
                System.out.println(opponent.getName() + "'s turn to guess...");
                String computerGuess = opponent.makeGuess();
                System.out.println(opponent.getName() + " guess is " + computerGuess);
                int[] result = CodeValidation.countBullsAndCows(playerSecretCode, computerGuess);
                System.out.println(opponent.getName() + " has " + result[0] + " Bulls and " + result[1] + " Cows.");
                guessRecords.add(new GuessRecord(opponent, computerGuess, result[0], result[1]));
                if (result[0] == 4) {
                    System.out.println(opponent.getName() + " guessed your code. You lose!");
                    winner = opponent.getName();
                    return;
                }
                computerAttempts--;
                System.out.println(opponent.getName() + "'s remaining attempts: " + computerAttempts);
                System.out.println("--------------------------------------------");
            }
            isHumanTurn = !isHumanTurn;
        }

        // Draw situation:
        System.out.println("It is a draw! Nobody guessed the secret codes.");
        System.out.println(opponent.getName() + "'s correct secret code was: " + computerSecretCode);
        winner = "It is a draw! No winner";
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

    public String getPlayerSecretCode() {
        return playerSecretCode;
    }
}
