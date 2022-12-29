package hexlet.code.formatters;

import hexlet.code.Format;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Plain  implements Format {

    public static <T> T isComplexValue(T value) {
        return (value instanceof ArrayList || value instanceof LinkedHashMap)
                ? (T) "[complex value]" : (value instanceof String)
                ? (T) ("'" + value + "'") : value;
    }

    @Override
    public final <T> String print(ArrayList<Map<?, ?>> differResult) {
        String formattedOutput = "";

        for (var node : differResult) {

            String type = (String) node.get("type");

            switch (type) {
                case "added":
                    formattedOutput += "Property '" + node.get("key") + "' was added with value: "
                            + isComplexValue(node.get("value")) + "\n";
                    break;
                case "deleted":
                    formattedOutput += "Property '" + node.get("key") + "' was removed\n";
                    break;
                case "changed":
                    formattedOutput += "Property '" + node.get("key") + "' was updated. From "
                            + isComplexValue(node.get("value1")) + " to "
                            + isComplexValue(node.get("value2")) + "\n";
                    break;
                case "unchanged":
                    break;
                default:
                    throw new RuntimeException("Unknown node type: '" + type + "'");
            }
//            if (node.containsValue("deleted")) {
//                formattedOutput += "Property '" + node.get("key") + "' was removed\n";
//            } else if (node.containsValue("added")) {
//                formattedOutput += "Property '" + node.get("key") + "' was added with value: "
//                        + isComplexValue(node.get("value")) + "\n";
//            } else if (node.containsValue("changed")) {
//                formattedOutput += "Property '" + node.get("key") + "' was updated. From "
//                        + isComplexValue(node.get("value1")) + " to "
//                        + isComplexValue(node.get("value2")) + "\n";
//            }
        }

        return formattedOutput.trim();
    }
}
