package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ParserTest {

    private static Map<?, ?> dataFromJsonFile;
    private static Map<?, ?> dataFromYmlFile;
    private static String rawDataJson;
    private static String rawDataYml;

    @BeforeAll
    public static void beforeAll() throws Exception {

        dataFromJsonFile = DifferTest.convertDataToMap("file1.json", "json");
        dataFromYmlFile = DifferTest.convertDataToMap("file5.yml", "yml");
        rawDataJson = DifferTest.getDataFromFile("file1.json");
        rawDataYml = DifferTest.getDataFromFile("file5.yml");
    }

    @Test
    void testparseDataToMapJson() throws Exception {

        assertEquals(dataFromJsonFile,
                Parser.parseDataToMap(rawDataJson, "json"));
    }

    @Test
    void testparseDataToMapYml() throws Exception {

        assertEquals(dataFromYmlFile,
                Parser.parseDataToMap(rawDataYml, "yml"));
    }
    @Test
    void testParserReadDataFromEmnptyFile() throws Exception {
        assertEquals(Map.of(),
                Parser.parseDataToMap("", ""));
    }

    @Test
    void testParserReadDataWithWrongData() {
        Throwable exception = assertThrows(Exception.class,
                () -> Parser.parseDataToMap("qwerty", ""));
        assertTrue(exception.getMessage().contains("Unrecognized token 'qwerty'"));
    }
}
