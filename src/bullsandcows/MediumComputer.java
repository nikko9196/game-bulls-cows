package bullsandcows;

import java.util.HashSet;
import java.util.Set;

public class MediumComputer extends Computer {
    Set<String> previousGuesses = new HashSet<String>();

    @Override
    public String makeGuess() {
        String guess = generateGuessCode();
        while (previousGuesses.contains(guess)) {
            guess = generateGuessCode();
        }
        previousGuesses.add(guess);
        // Print out - Use for testing:
        System.out.println(previousGuesses);
        return guess;
    }
}
