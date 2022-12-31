package hexlet.code;



import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class DiffBuilder {
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

    public static <T> LinkedList<Map<?, ?>> getDifference(LinkedList<Map<T, T>> dataFromParser) {

        TreeMap<T, T> uniqDataFromTwoFiles = new TreeMap<>();
        LinkedList<Map<?, ?>> resultDiff = new LinkedList<>();
        Map<T, T> data1 = dataFromParser.get(0);
        Map<T, T> data2 = dataFromParser.get(1);

        for (var data : dataFromParser) {
            uniqDataFromTwoFiles.putAll(data);
        }

        for (var e: uniqDataFromTwoFiles.entrySet()) {

            if (data1.containsKey(e.getKey()) && !data2.containsKey(e.getKey())) {

                resultDiff.add(createNode("deleted", e.getKey(), data1.get(e.getKey())));

            } else if (!data1.containsKey(e.getKey()) && data2.containsKey(e.getKey())) {

                resultDiff.add(createNode("added", e.getKey(), data2.get(e.getKey())));

            } else if (!((data1.get(e.getKey())) == null || (data2.get(e.getKey())) == null)
                    && ((data1.get(e.getKey())).equals(data2.get(e.getKey())))) {

                resultDiff.add(createNode("unchanged", e.getKey(), e.getValue()));

            } else {
                resultDiff.add(createNode("changed", e.getKey(), data1.get(e.getKey()),
                        data2.get(e.getKey())));
            }
        }

        return resultDiff;
    }
}
