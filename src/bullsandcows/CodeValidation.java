package bullsandcows;

import java.util.HashSet;
import java.util.Set;

public class CodeValidation {
    // Validate the user input when choosing the game mode options:
    public static boolean isValidOptionForGameMode(String input) {
        try {
            int number = Integer.parseInt(input);
            return (number >= 1 && number <= 2);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate the user input when choosing how many digits of secret code the player wants to play with:
    public static boolean isValidOptionSecretCodeDigits(String input) {
        try {
            int number = Integer.parseInt(input);
            return (number >= 1 && number <= 2);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate the user input when choosing the game mode levels:
    public static boolean isValidOptionForGameLevel(String input) {
        try {
            int number = Integer.parseInt(input);
            return (number >= 1 && number <= 3);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate the user input when deciding to save a game result or not:
    public static boolean isValidYesNoInput(String input) {
        return input.equalsIgnoreCase("Y") ||
                input.equalsIgnoreCase("YES") ||
                input.equalsIgnoreCase("N") ||
                input.equalsIgnoreCase("NO");
    }

    // 4-digit secret code: Check the entered guess is valid or not:
    public static boolean isValidCode(String input) {
        if (input.length() != 4 || !input.matches("\\d{4}")) {
            return false;
        }

        Set<Character> checkUnique = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (!checkUnique.add(c)) {
                return false;
            }
        }
        return true;
    }

    // 6-digit secret code: Check the entered guess is valid or not:
    public static boolean isValidCodeForSixDigitCode(String code) {
        if (code.length() != 6) {
            return false;
        }
        HashSet<Character> charSet = new HashSet<>();
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            } else if ((c < 'a' || c > 'f') && (c < '0' || c > '9')) {
                return false;
            } else if (!charSet.add(c)) {
                return false;
            }
        }
        return true;
    }

    // Check the Bulls and Counts result:
    public static int[] countBullsAndCows(String secretCode, String guess) {
        int bulls = 0;
        int cows = 0;

//        int[] secretDigits = new int[10];
//        int[] guessDigits = new int[10];

        // Count Bulls:
        for (int i = 0; i < secretCode.length(); i++) {
            if (secretCode.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else if (secretCode.contains(String.valueOf(guess.charAt(i)))) {
                cows++;
            }
        }

//        // Count Cows:
//        for (int i = 0; i < secretDigits.length; i++) {
//            cows += Math.min(secretDigits[i], guessDigits[i]);
//        }

        return new int[]{bulls, cows};
    }
}
