package hexlet.code;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class DifferTest {
    private static final int STYLISH_CASE_JSON12 = 0;
    private static final int STYLISH_CASE_JSON34 = 1;
    private static final int PLAIN_CASE_JSON12 = 2;
    private static final int PLAIN_CASE_JSON34 = 3;
    private static final int EMPTY_CASE = 4;
    private static final int FILE1_EMPTY_CASE = 5;
    private static final int FILE2_EMPTY_CASE = 6;



    private static String dataFromJsonFile1;
    private static String dataFromJsonFile2;
    private static String dataFromYmlFile1;
    private static String dataFromYmlFile2;
    private static String dataFromJsonFile3;
    private static String dataFromJsonFile4;
    private static String dataFromYmlFile3;
    private static String dataFromYmlFile4;
    private static String[] expectedDofferResultArray;
    private static String resultDiffBeetweenJsonCase1;
    private static String resultDiffBeetweenJsonCase2;
    private static String emptyfile;



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



    public static <T> Map<T, T> convertDataToMap(String fileName) throws Exception {
        String dataFromFile = getDataFromFile(fileName);
        ObjectMapper dataMapper = new ObjectMapper();
        TypeReference<Map<T, T>> typeReference = new TypeReference<>() {
        };

        return dataMapper.readValue(dataFromFile, typeReference);
    }
//
//    public static <T> ArrayList<Map<?, ?>> convertDataToListOfMap(String fileName) throws Exception {
//        String dataFromFile = getDataFromFile(fileName);
//        ObjectMapper dataMapper = new ObjectMapper();
//        TypeReference<ArrayList<Map<?, ?>>> typeReference = new TypeReference<>() {
//        };
//
//        return dataMapper.readValue(dataFromFile, typeReference);
//    }

    public static String[] convertDataToArrayStr(String fileName) throws Exception {
        String dataFromFile = getDataFromFile(fileName);
        ObjectMapper dataMapper = new ObjectMapper();
        TypeReference<String[]> typeReference = new TypeReference<>() {
        };

        String[] res = dataMapper.readValue(dataFromFile, typeReference);

        return res;
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        dataFromJsonFile1 = getPathFile("file1.json").toString();
        dataFromJsonFile2 = getPathFile("file2.json").toString();
        dataFromJsonFile3 = getPathFile("file3.json").toString();
        dataFromJsonFile4 = getPathFile("file4.json").toString();
        dataFromYmlFile1 = getPathFile("file1.yml").toString();
        dataFromYmlFile2 = getPathFile("file2.yml").toString();
        dataFromYmlFile3 = getPathFile("file3.yml").toString();
        dataFromYmlFile4 = getPathFile("file4.yml").toString();
        emptyfile = getPathFile("empty.json").toString();
        expectedDofferResultArray = convertDataToArrayStr("resultArrayOfStrings.json");
        resultDiffBeetweenJsonCase1 = getDataFromFile("resultDiffBeetweenJson1_2.json");
        resultDiffBeetweenJsonCase2 = getDataFromFile("resultDiffBeetweenJson3_4.json");
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of(resultDiffBeetweenJsonCase1,
                        dataFromJsonFile1, dataFromJsonFile2, "json"),
                Arguments.of(resultDiffBeetweenJsonCase2,
                        dataFromJsonFile3, dataFromJsonFile4, "json"),
                Arguments.of(expectedDofferResultArray[STYLISH_CASE_JSON12],
                        dataFromJsonFile1, dataFromJsonFile2, ""),
                Arguments.of(expectedDofferResultArray[STYLISH_CASE_JSON34],
                        dataFromJsonFile3, dataFromJsonFile4, ""),
                Arguments.of(expectedDofferResultArray[PLAIN_CASE_JSON12],
                        dataFromJsonFile1, dataFromJsonFile2, "plain"),
                Arguments.of(expectedDofferResultArray[PLAIN_CASE_JSON34],
                        dataFromJsonFile3, dataFromJsonFile4, "plain"),
                Arguments.of(resultDiffBeetweenJsonCase1,
                        dataFromYmlFile1, dataFromYmlFile2, "json"),
                Arguments.of(resultDiffBeetweenJsonCase2,
                        dataFromYmlFile3, dataFromYmlFile4, "json"),
                Arguments.of(expectedDofferResultArray[STYLISH_CASE_JSON12],
                        dataFromYmlFile1, dataFromYmlFile2, ""),
                Arguments.of(expectedDofferResultArray[STYLISH_CASE_JSON34],
                        dataFromYmlFile3, dataFromYmlFile4, ""),
                Arguments.of(expectedDofferResultArray[PLAIN_CASE_JSON12],
                        dataFromYmlFile1, dataFromYmlFile2, "plain"),
                Arguments.of(expectedDofferResultArray[PLAIN_CASE_JSON34],
                        dataFromYmlFile3, dataFromYmlFile4, "plain"),
                Arguments.of(expectedDofferResultArray[EMPTY_CASE],
                        emptyfile, emptyfile, ""),
                Arguments.of(expectedDofferResultArray[FILE1_EMPTY_CASE],
                        dataFromJsonFile1, emptyfile, ""),
                Arguments.of(expectedDofferResultArray[FILE2_EMPTY_CASE],
                        emptyfile, dataFromJsonFile2, "")

        );
    }
    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    public final void differTest(String expeted, String filepath1, String filepath2,
                        String format) {
        assertEquals(expeted, Differ.generate(filepath1, filepath2, format));
    }
}
