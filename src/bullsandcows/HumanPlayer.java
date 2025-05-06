package bullsandcows;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public String generateSecretCode() {
        return "";
    }
}
