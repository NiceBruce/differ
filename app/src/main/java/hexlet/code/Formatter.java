package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(List<Map<String, Object>> resultDiff, String type) throws Exception {

        return switch (type) {
            case "stylish" -> Stylish.getFormattedString(resultDiff);
            case "json" -> Json.getFormattedString(resultDiff);
            case "plain" -> Plain.getFormattedString(resultDiff);
            default -> throw new Exception("Unknown format: '" + type + "'");
        };
    }

}
