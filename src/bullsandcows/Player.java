package bullsandcows;

public abstract class Player {
    protected String name;
    protected String secretCode;
    protected int attempt;

    public Player(String name) {
        this.name = name;
        attempt = 7;
    }

    public abstract String generateSecretCode();

    public String getName() {
        return name;
    }
}
