package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.LinkedList;
import java.util.Map;

public class Formatter {

    public static String createFormatter(LinkedList<Map<?, ?>> resultDiff, String type) throws Exception {

        String result = switch (type) {
            case "stylish" -> Stylish.print(resultDiff);
            case "json" -> Json.print(resultDiff);
            case "plain" -> Plain.print(resultDiff);
            default -> throw new Exception("Unknown format: '" + type + "'");
        };

        return result;
    }

}
