package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.skyscreamer.jsonassert.JSONAssert;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferTest {
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;
    private static String emptyfile;


    public static Path getPathFile(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    public static String readFixture(String fileName) throws Exception {
        Path filePath = getPathFile(fileName);
        return Files.readString(filePath);
    }


    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
        emptyfile = getPathFile("empty.json").toString();

    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public final void generateTest(String format) throws Exception {
        String filePath1 = getPathFile("file1." + format).toString();
        String filePath2 = getPathFile("file2." + format).toString();

        assertThat(Differ.generate(filePath1, filePath2))
                .isEqualTo(resultStylish);

        assertThat(Differ.generate(filePath1, filePath2, "stylish"))
                .isEqualTo(resultStylish);

        assertThat(Differ.generate(filePath1, filePath2, "plain"))
                .isEqualTo(resultPlain);

        String actualJson = Differ.generate(filePath1, filePath2, "json");
        JSONAssert.assertEquals(resultJson, actualJson, false);

    }

    @Test
    void testParserThrowsException() {
        Throwable exception = assertThrows(Exception.class,
                () -> Differ.readDataFromFile("!!!NOTHING!!!"));

        assertTrue(exception.getMessage().contains("does not exist"));
    }

    @Test
    void testParserThrowsExceptionWhenEmptyFile() {

        Throwable exception = assertThrows(Exception.class,
                () -> Differ.generate(emptyfile, emptyfile));
        assertTrue(exception.getMessage().contains("No content to map due to end-of-input"));
    }
}
