package bullsandcows;

public class EasyComputer extends Computer {

    @Override
    public String makeGuess() {
        return generateGuessCode();
    }
}
