package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

public class Json {

    public static String getFormattedString(List<Map<String, Object>> differResult) throws Exception {

        ObjectMapper dataMapper = new ObjectMapper();

        return dataMapper.writerWithDefaultPrettyPrinter().writeValueAsString(differResult);
    }
}
