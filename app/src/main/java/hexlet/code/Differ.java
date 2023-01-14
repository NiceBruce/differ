package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String getType(String pathToFile) {
        return pathToFile.substring(pathToFile.lastIndexOf('.') + 1);
    }

    public static String readData(String pathToFile) throws Exception {

        Path path = Paths.get(pathToFile).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {

        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        String rawData1 = readData(filePath1);
        String rawData2 = readData(filePath2);

        ParserFactory factory = new ParserFactory();
        Parser parser = factory.create(getType(filePath1));

        Map<String, Object>  data1 = parser.parseData(rawData1);
        Map<String, Object>  data2 = parser.parseData(rawData2);

        List<Map<String, Object>> resultDiff = DiffBuilder.getDifference(data1, data2);

        return Formatter.format(resultDiff, format);
    }
}
