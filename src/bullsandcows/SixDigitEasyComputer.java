package bullsandcows;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SixDigitEasyComputer extends EasyComputer {
    private String secretCode;

    @Override
    public String getSecretCode() {
        HexaComputer hexaComputer = new HexaComputer();
        

        return "";
    }

    @Override
    public String makeGuess() {
        return "";
    }

    private void processFile(String filePath) {
        List<String> codes = new ArrayList<>();
        try (BufferedReader bR = new BufferedReader(new FileReader(filePath))) {
            String code = bR.readLine();
            while ((code = bR.readLine()) != null) {
                codes.add(code);
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
