package bullsandcows;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GameLog {
    public void saveToFile(String fileName, String gameModeType, String playerSecretCode, String computerSecretCode, List<GuessRecord> guessRecords, String winner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Bulls & Cows Game's Result:");
            writer.newLine();
            writer.write("Game mode: " + gameModeType);
            writer.newLine();

            // Template to save game result for Single Player mode:
            if (gameModeType.equals("Single Player")) {
                writer.write(Computer.DEFAULT_NAME + "'s secret code: " + computerSecretCode);
                writer.newLine();
                writer.write("----------");
                writer.newLine();
                int currentTurn = 1;
                for (int i = 0; i < guessRecords.size(); i++) {
                    writer.write("Turn " + currentTurn + ":");
                    writer.newLine();
                    writer.write(guessRecords.get(i).toString());
                    writer.newLine();
                    writer.write("----------");
                    writer.newLine();
                    currentTurn++;
                }
            }

            // Template to save game result for Player vs Computer mode:
            else if (gameModeType.equals("Player vs Computer")) {
                writer.write("Your secret code: " + playerSecretCode);
                writer.newLine();
                writer.write(Computer.DEFAULT_NAME + "'s secret code: " + computerSecretCode);
                writer.newLine();
                writer.write("----------");
                writer.newLine();
                int currentTurn = 1;
                for (int i = 0; i < guessRecords.size(); i += 2) {
                    writer.write("Turn " + currentTurn + ":");
                    writer.newLine();
                    writer.write(guessRecords.get(i).toString());
                    writer.newLine();
                    if (i + 1 < guessRecords.size()) {
                        writer.write(guessRecords.get(i + 1).toString());
                        writer.newLine();
                    }
                    writer.newLine();
                    writer.write("----------");
                    writer.newLine();
                    currentTurn++;
                }
            }
            writer.write("Winner: " + winner + ".");

            System.out.println("Your game result is SAVED!");

        } catch (IOException e) {
            System.out.println("Sorry, we were unable to save your game result. Please check the file name or try again!");
            System.out.println("Error: " + e.getMessage());
        }

    }
}
