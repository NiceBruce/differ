package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedList;
import java.util.Map;

public class Json {

    public static <T> String print(LinkedList<Map<?, ?>> differResult) throws Exception {

        String resultJson = "";
        ObjectMapper dataMapper = new ObjectMapper();

        resultJson = dataMapper.writerWithDefaultPrettyPrinter().writeValueAsString(differResult);

        return resultJson;
    }
}
