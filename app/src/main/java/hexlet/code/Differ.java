package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }

    public static String readDataFromFile(String pathToFile) throws Exception {

        Path path = Paths.get(pathToFile).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }

    public static <T> Map<?, ?> createNode(String type, T key, T... values) {
        Map<?, ?> node = new LinkedHashMap<>() {{
                put("key", key);
                put("type", type);
                if (type.equals("changed")) {
                    put("value1", values[0]);
                    put("value2", values[1]);
                } else {
                    put("value", values[0]);
                }
            }};

        return node;
    }

    public static <T> ArrayList<Map<?, ?>> getDifference(Map<T, T> file1, Map<T, T> file2) {

        TreeMap<T, T> uniqDataFromTwoFiles = new TreeMap<>(file1);
        uniqDataFromTwoFiles.putAll(file2);

        ArrayList<Map<?, ?>> resultDiff = new ArrayList<>();

        for (var e: uniqDataFromTwoFiles.entrySet()) {

            if (file1.containsKey(e.getKey()) && !file2.containsKey(e.getKey())) {

                resultDiff.add(createNode("deleted", e.getKey(), file1.get(e.getKey())));

            } else if (!file1.containsKey(e.getKey()) && file2.containsKey(e.getKey())) {

                resultDiff.add(createNode("added", e.getKey(), file2.get(e.getKey())));

            } else if (!((file1.get(e.getKey())) == null || (file2.get(e.getKey())) == null)
                    && ((file1.get(e.getKey())).equals(file2.get(e.getKey())))) {

                resultDiff.add(createNode("unchanged", e.getKey(), e.getValue()));

            } else {
                resultDiff.add(createNode("changed", e.getKey(), file1.get(e.getKey()),
                        file2.get(e.getKey())));
            }
        }

        return resultDiff;
    }

    public static String generate(String filePath1, String filePath2) throws Exception {


        String dataFromFile1 = readDataFromFile(filePath1);
        String dataFromFile2 = readDataFromFile(filePath2);


        Map<Object, Object> file1 = Parser.parseDataToMap(dataFromFile1, getDataFormat(filePath1));
        Map<Object, Object> file2 = Parser.parseDataToMap(dataFromFile2, getDataFormat(filePath2));


        ArrayList<Map<?, ?>> resultDiff = getDifference(file1, file2);

        Formatter formatter = new Formatter();
        Format currentFormat = formatter.createFormatter();

        return currentFormat.print(resultDiff);
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {


        String dataFromFile1 = readDataFromFile(filePath1);
        String dataFromFile2 = readDataFromFile(filePath2);

        Map<Object, Object> file1 = Parser.parseDataToMap(dataFromFile1, getDataFormat(filePath1));
        Map<Object, Object> file2 = Parser.parseDataToMap(dataFromFile2, getDataFormat(filePath2));


        ArrayList<Map<?, ?>> resultDiff = getDifference(file1, file2);

        Formatter formatter = new Formatter();
        Format currentFormat = formatter.createFormatter(format);

        return currentFormat.print(resultDiff);
    }
}
