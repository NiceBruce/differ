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

    public static void concat(StringBuilder sb, Object... dataSet) {

        for (Object data : dataSet) {
            sb.append(data);
        }
    }

    public static String getFormattedString(List<Map<String, Object>> differResult) {
        StringBuilder formattedOutput = new StringBuilder();

        for (var node : differResult) {

            String type = (String) node.get("type");

            switch (type) {
                case "added":
                    concat(formattedOutput, "Property '",
                            node.get("key"),
                            "' was added with value: ",
                            normalizeValue(node.get("value")),
                            "\n"
                    );
                    break;
                case "deleted":
                    concat(formattedOutput, "Property '",
                            node.get("key"),
                            "' was removed\n"
                    );
                    break;
                case "changed":
                    concat(formattedOutput, "Property '",
                            node.get("key"),
                            "' was updated. From ",
                            normalizeValue(node.get("value1")),
                            " to ",
                            normalizeValue(node.get("value2")),
                            "\n"
                    );
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
