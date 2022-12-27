package hexlet.code.formatters;

import hexlet.code.Format;
import java.util.ArrayList;
import java.util.Map;

public class Stylish implements Format {

    @Override
    public final <T> String print(ArrayList<Map<?, ?>> differResult) {

        String formattedOutput = "{\n";

        for (var node : differResult) {
            if (node.containsValue("deleted")) {
                formattedOutput += "  - " + node.get("key") + ": " + node.get("value") + "\n";
            } else if (node.containsValue("added")) {
                formattedOutput += "  + " + node.get("key") + ": " + node.get("value") + "\n";
            } else if (node.containsValue("unchanged")) {
                formattedOutput += "    " + node.get("key") + ": " + node.get("value") + "\n";
            } else {
                formattedOutput += "  - " + node.get("key") + ": " + node.get("value1") + "\n";
                formattedOutput += "  + " + node.get("key") + ": " + node.get("value2") + "\n";
            }
        }

        formattedOutput += "}";

        return formattedOutput;
    }
}
