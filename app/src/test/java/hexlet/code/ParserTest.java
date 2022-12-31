package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ParserTest {

    private static Map<?, ?> dataFromJsonFile;
    private static Map<?, ?> dataFromYmlFile;
    private static String rawDataJson;
    private static String rawDataYml;

    private static LinkedList<Map<String, String>> wrongData  = new LinkedList<>();
    private static LinkedList<Map<String, String>> currentDataJson  = new LinkedList<>();
    private static LinkedList<Map<String, String>> currentDataYml  = new LinkedList<>();

    @BeforeAll
    public static void beforeAll() throws Exception {

        dataFromJsonFile = DifferTest.convertDataToMap("file1.json", "json");
        dataFromYmlFile = DifferTest.convertDataToMap("file5.yml", "yml");
        rawDataJson = DifferTest.getDataFromFile("file1.json");
        rawDataYml = DifferTest.getDataFromFile("file5.yml");
        wrongData.add(Map.of("xls", "qwerty"));
        currentDataJson.add(Map.of("json", rawDataJson));
        currentDataYml.add(Map.of("yml", rawDataYml));
    }

    @Test
    void testparseDataToMapJson() throws Exception {
        LinkedList<Map<?, ?>> expected = new LinkedList<>();
        expected.add(dataFromJsonFile);

        assertEquals(expected, Parser.parseDataToMap(currentDataJson));
    }

    @Test
    void testparseDataToMapYml() throws Exception {
        LinkedList<Map<?, ?>> expected = new LinkedList<>();
        expected.add(dataFromYmlFile);

        assertEquals(expected,
                Parser.parseDataToMap(currentDataYml));
    }

    @Test
    void testParserReadDataWithWrongFormat() {
        Throwable exception = assertThrows(Exception.class,
                () -> Parser.parseDataToMap(wrongData));
        assertTrue(exception.getMessage().contains("Unknown format: 'xls'"));
    }

    @Test
    void testParseYamlWithWrongData() {
        Throwable exception = assertThrows(Exception.class,
                () -> Parser.parseYaml("qwerty"));
        assertTrue(exception.getMessage().contains("no String-argument constructor"
                + "/factory method to deserialize from String value ('qwerty')"));
    }

    @Test
    void testParseJsonWithWrongData() {
        Throwable exception = assertThrows(Exception.class,
                () -> Parser.parseJson("wasd"));
        assertTrue(exception.getMessage().contains("Unrecognized token 'wasd'"));
    }

}
