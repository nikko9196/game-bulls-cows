package bullsandcows;

public class FourDigitComputer extends EasyComputer {
    @Override
    public String getSecretCode() {
        return generateSecretCode() ;
    }

    @Override
    public String makeGuess() {
        return generateGuessCode();
    }
}
