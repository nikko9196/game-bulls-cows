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

            if (CodeValidation.isValidOptionForTwoChoices(gameModeInput)) {
                break;
            } else {
                System.out.println("Invalid input!");
            }
        }

        if (gameModeInput.equals("1")) {
            String secretCodeDigitInput;
            while (true) {
                System.out.println("Please choose how many digits of secret code that you want to play:");
                System.out.println("Press 1 for FOUR-DIGIT secret code.");
                System.out.println("Press 2 for SIX-DIGIT secret code.");
                secretCodeDigitInput = Keyboard.readInput().trim();

                if (CodeValidation.isValidOptionForTwoChoices(secretCodeDigitInput)) {
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            }

            EasyComputer digitOptionEasyComputer;
            if (secretCodeDigitInput.equals("1")) {
                digitOptionEasyComputer = new FourDigitEasyComputer();
            } else {
                digitOptionEasyComputer = new SixDigitEasyComputer();
            }

            // Test if the player chooses to play with 6-character code:
            // If the game cannot find the file meaning no secret code is retrieved, return to the beginning of the game.
            if (!digitOptionEasyComputer.isSecretCodeAvailable()) {
                System.out.println("There is a problem to generate the secret code. Returning to the main menu ...");
                System.out.println("--------------------------------------------");
                start();
                return;
            }

            SinglePlayer gameMode = new SinglePlayer(humanPlayer, digitOptionEasyComputer);
            gameMode.play();
            requestToSaveResult(gameMode, "Single Player", "");

        } else {
            String gameLevelInput;
            while (true) {
                System.out.println("Please choose your game level:");
                System.out.println("Press 1 for EASY.");
                System.out.println("Press 2 for MEDIUM.");
                System.out.println("Press 3 for HARD.");
                gameLevelInput = Keyboard.readInput().trim();

                if (CodeValidation.isValidOptionForThreeChoices(gameLevelInput)) {
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            }

            Computer computer;
            if (gameLevelInput.equals("1")) {
                computer = new FourDigitEasyComputer();
            } else if (gameLevelInput.equals("2")) {
                computer = new MediumComputer();
            } else {
                computer = new HardComputer();
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
