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

    public static String readDataFromFile(String pathToFile) throws Exception {

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

        String rawDataFromFile1 = readDataFromFile(filePath1);
        String rawDataFromFile2 = readDataFromFile(filePath2);

        ParserFactory factory = new ParserFactory();

//        Can i do this Short writing?
//        Map<String, Object>  data1 = factory.create(getType(filePath1)).parseData(rawDataFromFile1);
//        Map<String, Object>  data2 = factory.create(getType(filePath2)).parseData(rawDataFromFile2);

        Parser parserForFile1 = factory.create(getType(filePath1));
        Parser parserForFile2 = factory.create(getType(filePath2));

        Map<String, Object>  data1 = parserForFile1.parseData(rawDataFromFile1);
        Map<String, Object>  data2 = parserForFile2.parseData(rawDataFromFile2);

        List<Map<String, Object>> resultDiff = DiffBuilder.getDifference(data1, data2);

        return Formatter.format(resultDiff, format);
    }
}
