package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Map;


public class Differ {

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }

    public static LinkedList<Map<String, String>> readDataFromFile(String... pathToFile) throws Exception {

        LinkedList<Map<String, String>> rawData = new LinkedList<>();

        for (var file : pathToFile) {

            Path path = Paths.get(file).toAbsolutePath().normalize();

            if (!Files.exists(path)) {
                throw new Exception("File '" + path + "' does not exist");
            }

            rawData.add(Map.of(getDataFormat(file), Files.readString(path)));
        }

        return rawData;
    }



    public static String generate(String filePath1, String filePath2) throws Exception {

        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        var rawData = readDataFromFile(filePath1, filePath2);

        var data = Parser.parseDataToMap(rawData);

        var resultDiff = DiffBuilder.getDifference(data);

        return Formatter.createFormatter(resultDiff, format);
    }
}
