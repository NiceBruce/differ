package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class ParserTest {

    private static Map<String, String> dataFromJsonFile;

    @BeforeAll
    public static void beforeAll() throws Exception {
        dataFromJsonFile = DifferTest.convertDataToMap("file1.json");
    }

    @Test
    void testParserThrowsException() {
        Throwable exception = assertThrows(Exception.class,
                () -> Parser.getFile("!!!NOTHING!!!"));

        assertTrue(exception.getMessage().contains("does not exist"));
    }

    @Test
    void testParserReadDataFromFile() throws Exception {
        assertEquals(dataFromJsonFile,
                Parser.readDataFromFile(DifferTest.getPathFile("file1.json").toString()));
    }

    @Test
    void testParserReadDataFromFile2() throws Exception {
        assertNotEquals(dataFromJsonFile,
                Parser.readDataFromFile(DifferTest.getPathFile("file2.json").toString()));
    }

    @Test
    void testParserReadDataFromFile3() {
        Throwable exception = assertThrows(Exception.class,
                () -> Parser.readDataFromFile("!!!NOTHING!!!"));

        assertTrue(exception.getMessage().contains("does not exist"));
    }
}
