package hexlet.code.formatters;

import hexlet.code.Format;
import java.util.ArrayList;
import java.util.Map;

public class Stylish implements Format {

    @Override
    public final <T> String print(ArrayList<Map<?, ?>> differResult) {

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

//            if (node.containsValue("deleted")) {
//                formattedOutput += "  - " + node.get("key") + ": " + node.get("value") + "\n";
//            } else if (node.containsValue("added")) {
//                formattedOutput += "  + " + node.get("key") + ": " + node.get("value") + "\n";
//            } else if (node.containsValue("unchanged")) {
//                formattedOutput += "    " + node.get("key") + ": " + node.get("value") + "\n";
//            } else {
//                formattedOutput += "  - " + node.get("key") + ": " + node.get("value1") + "\n";
//                formattedOutput += "  + " + node.get("key") + ": " + node.get("value2") + "\n";
//            }
        }

        formattedOutput += "}";

        return formattedOutput.trim();
    }
}
