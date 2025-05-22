package bullsandcows;

public abstract class EasyComputer extends Computer {

    public abstract String getSecretCode();

    public boolean isSecretCodeAvailable() {
        return getSecretCode() != null;
    }
}
