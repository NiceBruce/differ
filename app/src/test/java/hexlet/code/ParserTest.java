package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ParserTest {

    private static Map<?, ?> dataFromJsonFile;
    private static String rawData;

    @BeforeAll
    public static void beforeAll() throws Exception {

        dataFromJsonFile = DifferTest.convertDataToMap("file1.json");
        rawData = DifferTest.getDataFromFile("file1.json");
    }

    @Test
    void testparseDataToMap() throws Exception {
        System.out.println(dataFromJsonFile);
        System.out.println(Parser.parseDataToMap(rawData));

        assertEquals(dataFromJsonFile,
                Parser.parseDataToMap(rawData));
    }
    @Test
    void testParserReadDataFromEmnptyFile() throws Exception {
        assertEquals(Map.of(),
                Parser.parseDataToMap(""));
    }

    @Test
    void testParserReadDataWithWrongData() {
        Throwable exception = assertThrows(Exception.class,
                () -> Parser.parseDataToMap("qwerty"));
        assertTrue(exception.getMessage().contains("Unrecognized token 'qwerty'"));
    }
}
