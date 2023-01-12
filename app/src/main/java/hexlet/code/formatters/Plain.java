package hexlet.code.formatters;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

public class Plain {

    public static Object normalizeValue(Object value) {
        return (value instanceof ArrayList || value instanceof LinkedHashMap)
                ? "[complex value]" : (value instanceof String)
                ? ("'" + value + "'") : value;
    }

    public static String getFormattedString(List<Map<String, Object>> differResult) {
        StringBuilder formattedOutput = new StringBuilder();
        String propertyAdd = "Property '%s' was added with value: %s\n";
        String propertyDel = "Property '%s' was removed\n";
        String propertyChange = "Property '%s' was updated. From %s to %s\n";

        for (var node : differResult) {
            String type = (String) node.get("type");

            switch (type) {
                case "added":
                    formattedOutput.append(String.format(propertyAdd, node.get("key"),
                            normalizeValue(node.get("value"))
                    ));
                    break;
                case "deleted":
                    formattedOutput.append(String.format(propertyDel, node.get("key")));
                    break;
                case "changed":
                    formattedOutput.append(String.format(propertyChange, node.get("key"),
                            normalizeValue(node.get("value1")),
                            normalizeValue(node.get("value2"))
                    ));
                    break;
                case "unchanged":
                    break;
                default:
                    throw new RuntimeException("Unknown node type: '" + type + "'");
            }
        }

        return formattedOutput.toString().trim();
    }
}
