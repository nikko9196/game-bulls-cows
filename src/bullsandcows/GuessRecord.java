package bullsandcows;

public class GuessRecord {
    private Player player;
    private String guess;
    private int bulls;
    private int cows;

    public GuessRecord(Player player, String guess, int bulls, int cows) {
        this.player = player;
        this.guess = guess;
        this.bulls = bulls;
        this.cows = cows;
    }

    public String toString() {
        return player.getName() + " guessed " + guess + ", scoring " + bulls + " bulls and " + cows + " cows.";
    }
}
