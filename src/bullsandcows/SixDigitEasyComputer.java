package bullsandcows;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SixDigitEasyComputer extends EasyComputer {

    @Override
    public String getSecretCode() {
        List<String> codes;
        try {
            codes = processFile("hexadecimals.txt");
        } catch (IOException e) {
            System.out.println("Error at getSecretCode(): " + e.getMessage());
            return null;
        }

        HexaComputer hexaComputer = new HexaComputer(codes);

        // Determine the size of 6-digit code list after validation:
        // (As we cannot modify HexaComputer.java to add the method getSize())
        int size = 0;
        while (true) {
            try {
                hexaComputer.getCode(size);
                size++;
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        // For testing
        System.out.println("Size of 6-digit code list after validation: " + size);

        Random random = new Random();
        int randomIndex = random.nextInt(size);
        return hexaComputer.getCode(randomIndex);
    }

    @Override
    public String makeGuess() {
        return "";
    }

    private List<String> processFile(String filePath) throws FileNotFoundException {
        List<String> codes = new ArrayList<>();
        try (BufferedReader bR = new BufferedReader(new FileReader(filePath))) {
            String code;
            while ((code = bR.readLine()) != null) {
                codes.add(code);
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            System.out.println("Error at processFile(): " + e.getMessage());
        }
        return codes;
    }
}
