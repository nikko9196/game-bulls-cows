package bullsandcows;

import java.util.List;

public abstract class GameModeStrategy {
//    protected List<GuessRecord> guessRecords;
//    protected String winner;
//    protected String computerSecretCode;

    public abstract void play();

    public abstract List<GuessRecord> getGuessRecords();

    public abstract String getWinner();

    public abstract String getComputerSecretCode();

    public boolean handleGuessProcess(String computerSecretCode, Player humanPlayer, List<GuessRecord> guessRecords, Computer computer) {
        String humanGuess;
        while (true) {
            System.out.print("Please enter your guess: ");
            humanGuess = Keyboard.readInput().trim();

            boolean isValidGuess;

            if (computer instanceof SixDigitEasyComputer) {
                isValidGuess = CodeValidation.isValidCodeForSixDigitCode(humanGuess);
                if (!isValidGuess) {
                    System.out.println("Your guess " + humanGuess + " is invalid. It should be exactly six unique characters, where the allowed characters are numbers, 0 - 9, and letters, a - f, and has no spacing. Please try again!");
                }

            } else {
                isValidGuess = CodeValidation.isValidCodeForFourDigitCode(humanGuess);
                if (!isValidGuess) {
                    System.out.println("Your guess " + humanGuess + " is invalid. It should be a 4-digit number with all digits are unique, and has no spacing. Please try again!");
                }
            }
            if (isValidGuess) {
                break;
            }
        }

        System.out.println("Your guess is " + humanGuess + ".");
        int[] result = CodeValidation.countBullsAndCows(computerSecretCode, humanGuess);
        guessRecords.add(new GuessRecord(humanPlayer, humanGuess, result[0], result[1]));

        if (result[0] == computerSecretCode.length()) {
            System.out.println("Congratulations! You win! You've guessed the secret code correctly!");
            return true;
        }
        System.out.println("You have " + result[0] + " Bulls and " + result[1] + " Cows.");
        return false;
    }
}
