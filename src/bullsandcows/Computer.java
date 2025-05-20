package bullsandcows;

import java.util.HashSet;
import java.util.Random;

public abstract class Computer extends Player {
    public static final String DEFAULT_NAME = "Alexis The Computer";

    public Computer() {
        super(DEFAULT_NAME);
    }

    protected String generateSecretCode() {
        StringBuilder secretCode = new StringBuilder();
        boolean[] usedDigits = new boolean[10];

        while (secretCode.length() < 4) {
            int digit = (int) (Math.random() * 10);
            if (!usedDigits[digit]) {
                usedDigits[digit] = true;
                secretCode.append(digit);
            }
        }

        this.secretCode = secretCode.toString();
        return this.secretCode;
    }

    public abstract String makeGuess();

    protected String generateGuessCode() {
        Random random = new Random();
        HashSet<Integer> digits = new HashSet<>();
        StringBuilder code = new StringBuilder();

        while (code.length() < 4) {
            int digit = random.nextInt(10);
            if (digits.add(digit)) {
                code.append(digit);
            }
        }

        return code.toString();
    }

}
