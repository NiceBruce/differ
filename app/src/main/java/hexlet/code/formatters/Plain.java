package hexlet.code.formatters;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

public class Plain {

    public static Object toNormalizeValue(Object value) {
        return (value instanceof ArrayList || value instanceof LinkedHashMap)
                ? "[complex value]" : (value instanceof String)
                ? ("'" + value + "'") : value;
    }

    public static String getFormattedString(List<Map<String, Object>> differResult) {
        StringBuilder formattedOutput = new StringBuilder();

        for (var node : differResult) {

            String type = (String) node.get("type");

            switch (type) {
                case "added":
                    formattedOutput.append("Property '").append(node.get("key")).append("' was added with value: ")
                            .append(toNormalizeValue(node.get("value"))).append("\n");
                    break;
                case "deleted":
                    formattedOutput.append("Property '").append(node.get("key")).append("' was removed\n");
                    break;
                case "changed":
                    formattedOutput.append("Property '").append(node.get("key")).append("' was updated. From ")
                            .append(toNormalizeValue(node.get("value1"))).append(" to ")
                            .append(toNormalizeValue(node.get("value2"))).append("\n");
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
