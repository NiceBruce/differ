package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.LinkedHashMap;

public class DiffBuilder {
    public static Map<String, Object> createNode(String type, String key, Object... values) {
        Map<String, Object> node = new LinkedHashMap<>() {{
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

    public static List<Map<String, Object>> getDifference(Map<String, Object> file1,
                                                                    Map<String, Object> file2) {

        Map<String, Object> uniqDataFromTwoFiles = new TreeMap<>(file1);
        uniqDataFromTwoFiles.putAll(file2);

        List<Map<String, Object>> resultDiff = new LinkedList<>();

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
}
