package hexlet.code;

import java.util.Map;
import java.util.TreeMap;

public class Differ {


    public static String generate(Map<String, String> file1, Map<String, String> file2) {
        TreeMap<String, String> mapOfTwoFiles = new TreeMap<>(file1);
        mapOfTwoFiles.putAll(file2);
        String res = "{\n";

        for (var e: mapOfTwoFiles.entrySet()) {
            if (file1.containsKey(e.getKey()) && !file2.containsKey(e.getKey())) {
                res += "  - " + e + "\n";
            }
            if (!file1.containsKey(e.getKey()) && file2.containsKey(e.getKey())) {
                res += "  + " + e + "\n";
            }
            if (file1.containsKey(e.getKey()) && file2.containsKey(e.getKey())) {
                if (file1.containsValue(e.getValue()) && file2.containsValue(e.getValue())) {
                    res += "    " + e + "\n";
                } else if (!file1.containsValue(e.getValue()) && file2.containsValue(e.getValue())) {
                    res += "  - " + e.getKey() + ": " + file1.get(e.getKey()) + "\n";
                    res += "  + " + e.getKey() + ": " + file2.get(e.getKey()) + "\n";
                }
            }
        }
//        Map<String, String> mapOfTwoFiles = Stream.of(file1, file2)
//                .flatMap(m -> m.entrySet().stream())
//                .collect(Collectors.toMap(
//                        m -> m.getKey(),
//                        m -> m.getValue()
//                ));

        res += "}";
        return res;
    }
}
