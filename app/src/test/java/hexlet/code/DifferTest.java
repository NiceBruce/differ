package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {

    private Path resourceDirectory = Paths.get("src", "test", "resources");
    private String pathToFile1 = resourceDirectory.toFile().getAbsolutePath() + "/file1.json";
    private String pathToFile2 = resourceDirectory.toFile().getAbsolutePath() + "/file2.json";

    public static String getTestFile(String pathToFile) throws Exception {
        Path path = Paths.get(pathToFile).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }

    public static Map<String, String> readTestDataFromJson(String jsonFile) throws IOException {
        ObjectMapper dataMapper = new ObjectMapper();
        TypeReference<Map<String, String>> typeReference = new TypeReference<Map<String, String>>() {
        };
        return dataMapper.readValue(jsonFile, typeReference);
    }



    @Test
    void testCaseBaseSituation() throws Exception {
        String expected = "{\n"
                + "  - follow=false\n"
                + "    host=hexlet.io\n"
                + "  - proxy=123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose=true\n"
                + "}";

        String actual = Differ.generate(readTestDataFromJson(getTestFile(pathToFile1)),
                readTestDataFromJson(getTestFile(pathToFile2)));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testCaseWhenEmptyBothFiles() throws Exception {
        String expected = "{\n"
                + "}";

        Map<String, String> emptyDataFromFile1 = Map.of();
        Map<String, String> emptyDataFromFile2 = Map.of();

        String actual = Differ.generate(emptyDataFromFile1, emptyDataFromFile2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testCaseWhenEmptySecondFile() throws Exception {
        String expected = "{\n"
                + "  - follow=false\n"
                + "  - host=hexlet.io\n"
                + "  - proxy=123.234.53.22\n"
                + "  - timeout=50\n"
                + "}";

        Map<String, String> emptyDataFromFile2 = Map.of();

        String actual = Differ.generate(readTestDataFromJson(getTestFile(pathToFile1)), emptyDataFromFile2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testCaseWhenEmptyFirstFile() throws Exception {
        String expected = "{\n"
                + "  + host=hexlet.io\n"
                + "  + timeout=20\n"
                + "  + verbose=true\n"
                + "}";

        Map<String, String> emptyDataFromFile1 = Map.of();

        String actual = Differ.generate(emptyDataFromFile1, readTestDataFromJson(getTestFile(pathToFile2)));

        assertThat(actual).isEqualTo(expected);
    }

}
