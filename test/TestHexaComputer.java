import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestHexaComputer {
    // TODO Write appropriate unit tests for testing HexaComputer
    private HexaComputer hexaComputer;

    @Test
    public void testNullCodeListThrowException() {
        List<String> codes = null;
        assertThrows(IllegalArgumentException.class, () -> new HexaComputer(codes));
    }

    @Test
    public void testEmptyCodeListThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new HexaComputer(Collections.emptyList()));
    }

    @Test
    public void testAllInvalidCodesThrowException() {
        List<String> allInvalidCodes = Arrays.asList("12345", "1234567", "12345z", "1234 5", "a12344", "aa1234", "@12345", "+12345", "-12345", "ABCDEF", "ABC123");
        assertThrows(IllegalArgumentException.class, () -> new HexaComputer(allInvalidCodes));
    }

    @Test
    public void testAllValidCodesPassValidation() {
        List<String> allValidCodes = Arrays.asList("012345", "456789", "abcdef", "123abc");
        hexaComputer = new HexaComputer(allValidCodes);
        assertEquals("012345", hexaComputer.getCode(0));
        assertEquals("456789", hexaComputer.getCode(1));
        assertEquals("abcdef", hexaComputer.getCode(2));
        assertEquals("123abc", hexaComputer.getCode(3));
    }

    @Test
    public void testValidCodesMixedInvalidCodes() {
        List<String> allValidCodes = Arrays.asList("@12345", "012345", "12345", "456789", "1234567", "abcdef", "12345z", "123abc");
        hexaComputer = new HexaComputer(allValidCodes);
        assertEquals("012345", hexaComputer.getCode(0));
        assertEquals("456789", hexaComputer.getCode(1));
        assertEquals("abcdef", hexaComputer.getCode(2));
        assertEquals("123abc", hexaComputer.getCode(3));
    }

    @Test
    public void testGetCodeThrowIndexOutOfBoundsException(){
        List<String> codes = Arrays.asList("edf456", "123abc", "1234567");
        hexaComputer = new HexaComputer(codes);
        assertThrows(IndexOutOfBoundsException.class, () -> hexaComputer.getCode(-1));
        assertEquals("edf456", hexaComputer.getCode(0));
        assertEquals("123abc", hexaComputer.getCode(1));
        assertThrows(IndexOutOfBoundsException.class, () -> hexaComputer.getCode(2));
    }


}



