package bullsandcows;

import java.util.HashSet;
import java.util.Set;

public class CodeValidation {
    // Validate the user input when there are 2 options to choose (Eg. For Game Mode and Digit Code options):
    public static boolean isValidOptionForTwoChoices(String input) {
        try {
            int number = Integer.parseInt(input);
            return (number >= 1 && number <= 2);
        } catch (NumberFormatException e) {
            return false;
        }
    }


    // Validate the user input when there are 3 options to choose (Eg. For Game Level options):
    public static boolean isValidOptionForThreeChoices(String input) {
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
    public static boolean isValidCodeForFourDigitCode(String input) {
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
    /**
     * As mentioned in the task 5's requirement:
     * "You may move the HexaComputer class to an appropriate package that fits your game structure.
     * However, you should not modify anything else in the class."
     * isValidCode() under HexaComputer cannot be re-used as it is a private method.
     * Therefore, isValidCodeForSixDigitCode() is re-created as a static method here.
     */
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


    // Check the Bul