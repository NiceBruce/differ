package hexlet.code;

import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {

        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        Map<String, Object>  data1 = new Parser().parseData(filePath1);
        Map<String, Object>  data2 = new Parser().parseData(filePath2);

        List<Map<String, Object>> resultDiff = DiffBuilder.getDifference(data1, data2);

        return Formatter.format(resultDiff, format);
    }
}
