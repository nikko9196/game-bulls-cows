package bullsandcows;

public class GameManager {
    public static void main(String[] args) {
        new GameManager().start();
    }

    private void start() {
        // Intro of the game:
        System.out.println("Welcome to Bulls & Cows game!");
        System.out.print("Please enter your name: ");
        String namePlayer = Keyboard.readInput().trim();
        Player humanPlayer = new HumanPlayer(namePlayer);

        System.out.println("Hi " + namePlayer + ", I am " + Computer.DEFAULT_NAME + ". Let's play the Bulls & Cows game together!");

        // Choose the option for game mode and game level to start the game:
        String gameModeInput;
        while (true) {
            System.out.println("Please choose your game mode:");
            System.out.println("Press 1 for Single Player.");
            System.out.println("Press 2 for Player vs Computer.");
            gameModeInput = Keyboard.readInput().trim();

            if (CodeValidation.isValidOption(gameModeInput)) {
                break;
            } else {
                System.out.println("Invalid input!");
            }
        }

        if (gameModeInput.equals("1")) {
            SinglePlayer gameMode = new SinglePlayer(humanPlayer);
            gameMode.play();
            requestToSaveResult(gameMode, "Single Player", "");

        } else {
            String gameLevelInput;
            while (true) {
                System.out.println("Please choose your game level:");
                System.out.println("Press 1 for EASY.");
                System.out.println("Press 2 for MEDIUM.");
                gameLevelInput = Keyboard.readInput().trim();

                if (CodeValidation.isValidOption(gameLevelInput)) {
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            }

            Computer computer;
            if (gameLevelInput.equals("1")) {
                computer = new EasyComputer();
            } else {
                computer = new MediumComputer();
            }
            VSComputer gameMode = new VSComputer(humanPlayer, computer);
            gameMode.play();
            requestToSaveResult(gameMode, "Player vs Computer", gameMode.getPlayerSecretCode());
        }
    }


    private void requestToSaveResult(GameModeStrategy gameMode, String gameModeType, String playerSecretCode) {
        System.out.println("Do you want to save the game result? (Y/N)");
        String saveGameResult = Keyboard.readInput().trim();

        while (!CodeValidation.isValidYesNoInput(saveGameResult)) {
            System.out.println("Invalid input. Please enter Y (Yes) or N (No).");
            saveGameResult = Keyboard.readInput().trim();
        }

        if (saveGameResult.equalsIgnoreCase("Y") || saveGameResult.equalsIgnoreCase("YES")) {
            GameLog gameLog = new GameLog();
            System.out.print("Please enter the file name for the game result: ");
            String fileName = Keyboard.readInput().trim();

            if (!fileName.toLowerCase().endsWith(".txt")) {
                fileName = fileName + ".txt";
            }

            System.out.println("Your result is saving ...");
            gameLog.saveToFile(fileName, gameModeType, playerSecretCode, gameMode.getComputerSecretCode(), gameMode.getGuessRecords(), gameMode.getWinner());

        }
        System.out.println("Thank you for playing the game. Bye bye!");
    }
}
