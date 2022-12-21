package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class DifferTest {

    private static Map<String, String> dataFromJsonFile1;
    private static Map<String, String> dataFromJsonFile2;
    private static Map<String, String> dataFromYmlFile1;
    private static Map<String, String> dataFromYmlFile2;

    public static Path getPathFile(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }
    public static String getDataFromFile(String fileName) throws Exception {

        Path filePath = getPathFile(fileName);

        if (!Files.exists(filePath)) {
            throw new Exception("File '" + filePath + "' does not exist");
        }
        return Files.readString(filePath);
    }

    public static Map<String, String> convertDataToMap(String fileName) throws Exception {
        String dataFromFile = getDataFromFile(fileName);
        ObjectMapper dataMapper = new ObjectMapper();
        TypeReference<Map<String, String>> typeReference = new TypeReference<>() {
        };

        return dataMapper.readValue(dataFromFile, typeReference);
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        dataFromJsonFile1 = convertDataToMap("file1.json");
        dataFromJsonFile2 = convertDataToMap("file2.json");
        dataFromYmlFile1 = convertDataToMap("file1.yml");
        dataFromYmlFile2 = convertDataToMap("file2.yml");
    }

    @Test
    void testDifferJson() {
        String expected = "{\n"
                + "  - follow=false\n"
                + "    host=hexlet.io\n"
                + "  - proxy=123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose=true\n"
                + "}";

        String actual = Differ.generate(dataFromJsonFile1, dataFromJsonFile2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDifferWhenEmptyBothFiles() {
        String expected = "{\n"
                + "}";

        Map<String, String> emptyDataFromFile1 = Map.of();
        Map<String, String> emptyDataFromFile2 = Map.of();

        String actual = Differ.generate(emptyDataFromFile1, emptyDataFromFile2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDifferJsonWhenEmptySecondFile() {
        String expected = "{\n"
                + "  - follow=false\n"
                + "  - host=hexlet.io\n"
                + "  - proxy=123.234.53.22\n"
                + "  - timeout=50\n"
                + "}";

        Map<String, String> emptyDataFromFile2 = Map.of();

        String actual = Differ.generate(dataFromJsonFile1, emptyDataFromFile2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDifferJsonWhenEmptyFirstFile() {
        String expected = "{\n"
                + "  + host=hexlet.io\n"
                + "  + timeout=20\n"
                + "  + verbose=true\n"
                + "}";

        Map<String, String> emptyDataFromFile1 = Map.of();

        String actual = Differ.generate(emptyDataFromFile1, dataFromJsonFile2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDifferYml() {
        String expected = "{\n"
                + "  - follow=false\n"
                + "    host=hexlet.io\n"
                + "  - proxy=123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose=true\n"
                + "}";

        String actual = Differ.generate(dataFromYmlFile1, dataFromYmlFile2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDifferYmlWhenEmptySecondFile() {
        String expected = "{\n"
                + "  - follow=false\n"
                + "  - host=hexlet.io\n"
                + "  - proxy=123.234.53.22\n"
                + "  - timeout=50\n"
                + "}";

        Map<String, String> emptyDataFromFile2 = Map.of();

        String actual = Differ.generate(dataFromYmlFile1, emptyDataFromFile2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDifferYmlWhenEmptyFirstFile() {
        String expected = "{\n"
                + "  + host=hexlet.io\n"
                + "  + timeout=20\n"
                + "  + verbose=true\n"
                + "}";

        Map<String, String> emptyDataFromFile1 = Map.of();

        String actual = Differ.generate(emptyDataFromFile1, dataFromYmlFile2);

        assertThat(actual).isEqualTo(expected);
    }

}
