package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {


    public static String getFormattedString(List<Map<String, Object>> differResult) {

        StringBuilder formattedOutput = new StringBuilder("{\n");

        for (var node : differResult) {

            String type = (String) node.get("type");

            switch (type) {
                case "added" -> formattedOutput.append("  + ").append(node.get("key")).append(": ")
                        .append(node.get("value")).append("\n");
                case "deleted" -> formattedOutput.append("  - ").append(node.get("key")).append(": ")
                        .append(node.get("value")).append("\n");
                case "changed" -> {
                    formattedOutput.append("  - ").append(node.get("key")).append(": ")
                            .append(node.get("value1")).append("\n");
                    formattedOutput.append("  + ").append(node.get("key")).append(": ")
                            .append(node.get("value2")).append("\n");
                }
                case "unchanged" -> formattedOutput.append("    ").append(node.get("key")).append(": ")
                        .append(node.get("value")).append("\n");
                default -> throw new RuntimeException("Unknown node type: '" + type + "'");
            }
        }

        formattedOutput.append("}");

        return formattedOutput.toString();
    }
}
