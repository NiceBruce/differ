package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String getFormattedString(List<Map<String, Object>> differResult) {

        StringBuilder formattedOutput = new StringBuilder("{\n");
        String propertyAdd = "  + %s: %s\n";
        String propertyDel = "  - %s: %s\n";
        String propertyUnchange = "    %s: %s\n";

        for (var node : differResult) {

            String type = (String) node.get("type");

            switch (type) {
                case "added" -> formattedOutput.append(String.format(propertyAdd,
                                node.get("key"),
                                node.get("value")));
                case "deleted" -> formattedOutput.append(String.format(propertyDel,
                                node.get("key"),
                                node.get("value")));
                case "changed" -> {
                    formattedOutput.append(String.format(propertyDel,
                            node.get("key"), node.get("value1")));
                    formattedOutput.append(String.format(propertyAdd,
                            node.get("key"), node.get("value2")));
                }
                case "unchanged" -> formattedOutput.append(String.format(propertyUnchange,
                                node.get("key"), node.get("value")));
                default -> throw new RuntimeException("Unknown node type: '" + type + "'");
            }
        }

        formattedOutput.append("}");

        return formattedOutput.toString();
    }
}
