package bullsandcows;

import java.util.*;

public class HardComputer extends Computer {
    private String currentGuess;
    private String candidateGuess;
    private int[] resultGuessVsSecretCode;
    private int[] resultGuessVsCandidateGuess;
    Set<String> possibleGuessList = new HashSet<>();

    //For testing the logic setup:
//    public HardComputer() {
//        possibleGuessList.add("1234");
//        possibleGuessList.add("2345");
//        possibleGuessList.add("0987");
//        possibleGuessList.add("3456");
//        possibleGuessList.add("8091");
//        possibleGuessList.add("2697");
//        possibleGuessList.add("4012");
//        System.out.println("First possibleGuessList: " + possibleGuessList);
//    }

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
            if (CodeValidation.isValidCode(possibleGuess)) {
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

    ;

    @Override
    public String makeGuess() {
        this.currentGuess = getRandomGuess();
        // Print out to test
        System.out.println("***** Computer's currentGuess: " + currentGuess + " *****");
        return currentGuess;
    }

    // Check the result of bulls and cows between the currentGuess and the candidateGuess:
//    private boolean isMatchingBullsAndCows(int[] resultGuessVsSecretCode) {
//        resultGuessVsCandidateGuess = CodeValidation.countBullsAndCows(currentGuess, candidateGuess);
//        return resultGuessVsSecretCode[0] == resultGuessVsCandidateGuess[0] && resultGuessVsSecretCode[1] == resultGuessVsCandidateGuess[1];
//    }

    // Update possibleGuessList after the pruning process:
    private void updatePossibleGuessList(int[] resultGuessVsSecretCode) {
        Set<String> newPossibleGuessList = new HashSet<>();
        for (String candidate : possibleGuessList) {
            this.candidateGuess = candidate;
            resultGuessVsCandidateGuess = CodeValidation.countBullsAndCows(currentGuess, candidateGuess);
            if (resultGuessVsSecretCode[0] == resultGuessVsCandidateGuess[0] && resultGuessVsSecretCode[1] == resultGuessVsCandidateGuess[1]) {
                newPossibleGuessList.add(candidateGuess);
            }
        }
        possibleGuessList = newPossibleGuessList;
        // Print out to test
        System.out.println("***** possibleGuessList's size after each round: " + possibleGuessList.size() + " *****");
        System.out.println("***** Updated possibleGuessList after each round: " + possibleGuessList + " *****");
    }


    public void pruningProcess(int bulls, int cows) {
        resultGuessVsSecretCode = new int[]{bulls, cows};
        updatePossibleGuessList(resultGuessVsSecretCode);
    }
}
