package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

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

    public static <T> String generate(String filePath1, String filePath2, String format) {

        Map<T, T> file1;
        Map<T, T> file2;

        try {
            file1 = Parser.readDataFromFile(filePath1);
            file2 = Parser.readDataFromFile(filePath2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Formatter formatter = new Formatter();
        Format currentFormat = formatter.createFormatter(format);

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

        return currentFormat.print(resultDiff);
    }
}
