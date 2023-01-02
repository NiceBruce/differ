package hexlet.code.formatters;

import java.util.LinkedList;
import java.util.Map;

public class Stylish {


    public static String print(LinkedList<Map<?, ?>> differResult) {

        String formattedOutput = "{\n";

        for (var node : differResult) {

            String type = (String) node.get("type");

            switch (type) {
                case "added":
                    formattedOutput += "  + " + node.get("key") + ": " + node.get("value") + "\n";
                    break;
                case "deleted":
                    formattedOutput += "  - " + node.get("key") + ": " + node.get("value") + "\n";
                    break;
                case "changed":
                    formattedOutput += "  - " + node.get("key") + ": " + node.get("value1") + "\n";
                    formattedOutput += "  + " + node.get("key") + ": " + node.get("value2") + "\n";
                    break;
                case "unchanged":
                    formattedOutput += "    " + node.get("key") + ": " + node.get("value") + "\n";
                    break;
                default:
                    throw new RuntimeException("Unknown node type: '" + type + "'");
            }
        }

        formattedOutput += "}";

        return formattedOutput;
    }
}
