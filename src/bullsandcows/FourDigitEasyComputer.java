package bullsandcows;

public class FourDigitEasyComputer extends EasyComputer {
    @Override
    public String getSecretCode() {
        return generateSecretCode() ;
    }

    @Override
    public String makeGuess() {
        return generateGuessCode();
    }
}
