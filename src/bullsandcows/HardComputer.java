package bullsandcows;

import java.util.*;

public class HardComputer extends Computer{
    private String currentGuess;
    private String candidateGuess;
    private int[] resultGuessVsSecretCode;
    private int[] resultGuessVsCandidateGuess;
    Set<String> possibleGuessList = new HashSet<>();

    //For testing the logic setup:
    public HardComputer() {
        possibleGuessList.add("1234");
        possibleGuessList.add("2345");
        possibleGuessList.add("0987");
        possibleGuessList.add("3456");
        possibleGuessList.add("8091");
        possibleGuessList.add("2697");
        possibleGuessList.add("4012");
        System.out.println("First possibleGuessList: " + possibleGuessList);
    }

    //Get the random guess from the possibleGuessList:
    private String getRandomGuess(){
        List<String> guessList = new ArrayList<>(possibleGuessList);
        Random rand = new Random();
        int index = rand.nextInt(possibleGuessList.size());
        return guessList.get(index);
    };

    @Override
    public String makeGuess() {
        this.currentGuess = getRandomGuess();
        //Print out to test
        System.out.println(currentGuess);
        return currentGuess;
    }

    // Check the result of bulls and cows between the currentGuess and the candidateGuess:
//    private boolean isMatchingBullsAndCows(int[] resultGuessVsSecretCode) {
//        resultGuessVsCandidateGuess = CodeValidation.countBullsAndCows(currentGuess, candidateGuess);
//        return resultGuessVsSecretCode[0] == resultGuessVsCandidateGuess[0] && resultGuessVsSecretCode[1] == resultGuessVsCandidateGuess[1];
//    }

    // Update possibleGuessList after the pruning process:
    private void updatePossibleGuessList(int[] resultGuessVsSecretCode){
        Set<String> newPossibleGuessList = new HashSet<>();
        resultGuessVsCandidateGuess = CodeValidation.countBullsAndCows(currentGuess, candidateGuess);
        for (String candidateGuess : possibleGuessList) {
            if (resultGuessVsSecretCode[0] == resultGuessVsCandidateGuess[0] && resultGuessVsSecretCode[1] == resultGuessVsCandidateGuess[1]) {
                newPossibleGuessList.add(candidateGuess);
            }
        }
        possibleGuessList = newPossibleGuessList;
        System.out.println("Updated possibleGuessList after each round: " + possibleGuessList);
    }


    public void pruningProcess(int bulls, int cows) {
        resultGuessVsSecretCode = new int[]{bulls, cows};
        updatePossibleGuessList(resultGuessVsSecretCode);
    }
}
