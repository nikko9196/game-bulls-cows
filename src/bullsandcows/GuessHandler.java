package bullsandcows;

import java.util.List;

public class GuessHandler {
    public static boolean handleGuessProcess(String computerSecretCode, Player humanPlayer, List<GuessRecord> guessRecords) {
        String humanGuess;
        while (true) {
            System.out.print("Please enter your guess: ");
            humanGuess = Keyboard.readInput().trim();
            if (CodeValidation.isValidCode(humanGuess)) {
                break;
            }
            System.out.println("Your guess " + humanGuess + " is invalid. It should be a 4-digit number with all digits are unique, and has no spacing. Please try again!");
        }
        System.out.println("Your guess is " + humanGuess + ".");
        int[] result = CodeValidation.countBullsAndCows(computerSecretCode, humanGuess);
        guessRecords.add(new GuessRecord(humanPlayer, humanGuess, result[0], result[1]));

        if (result[0] == 4) {
            System.out.println("Congratulations! You win! You've guessed the secret code correctly!");
            return true;
        }
        System.out.println("You have " + result[0] + " Bulls and " + result[1] + " Cows.");
        return false;
    }
}
