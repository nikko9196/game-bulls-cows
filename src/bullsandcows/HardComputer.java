package bullsandcows;

import java.util.*;

public class HardComputer extends Computer {
    private String currentGuess;
    Set<String> possibleGuessList = new HashSet<>();

    public HardComputer() {
        possibleGuessList = generatePossibleGuessList();
        // Print out for testing:
        System.out.println("***** possibleGuessList's size: " + possibleGuessList.size() + " *****");
        System.out.println("***** First possibleGuessList: " + possibleGuessList);
    }

    // Generate possibleGuessList:
    private Set<String> generatePossibleGuessList() {
        for (int i = 123; i <= 9876; i++) {
            String possibleGuess = String.valueOf(i);
            if (possibleGuess.length() != 4) {
                possibleGuess = "0" + possibleGuess;
            }
            if (CodeValidation.isValidCodeForFourDigitCode(possibleGuess)) {
                possibleGuessList.add(possibleGuess);
            }
        }
        return possibleGuessList;
    }

    //Get the random guess from the possibleGuessList:
    private String getRandomGuess() {
        List<String> guessList = new ArrayList<>(possibleGuessList);
        Random rand = new Random();
        int index = rand.nextInt(possibleGuessList.size());
        return guessList.get(index);
    }

    @Override
    public String makeGuess() {
        this.currentGuess = getRandomGuess();
        // Print out to test
        System.out.println("***** Computer's currentGuess: " + currentGuess + " *****");
        return currentGuess;
    }

    // Update possibleGuessList after the pruning process:
    private void updatePossibleGuessList(int[] resultGuessVsSecretCode) {
        Set<String> newPossibleGuessList = new HashSet<>();
        for (String candidate : possibleGuessList) {
            int[] resultGuessVsCandidateGuess = CodeValidation.countBullsAndCows(currentGuess, candidate);
            if (resultGuessVsSecretCode[0] == resultGuessVsCandidateGuess[0] && resultGuessVsSecretCode[1] == resultGuessVsCandidateGuess[1]) {
                newPossibleGuessList.add(candidate);
            }
        }
        possibleGuessList = newPossibleGuessList;
        // Print out to test
        System.out.println("***** possibleGuessList's size after each round: " + possibleGuessList.size() + " *****");
        System.out.println("***** Updated possibleGuessList after each round: " + possibleGuessList + " *****");
    }

    public void pruningProcess(int bulls, int cows) {
        int[] resultGuessVsSecretCode = new int[]{bulls, cows};
        updatePossibleGuessList(resultGuessVsSecretCode);
    }
}
