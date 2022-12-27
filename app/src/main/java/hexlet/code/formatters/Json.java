package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Format;
import java.util.ArrayList;
import java.util.Map;

public final class Json implements Format {

    @Override
    public <T> String print(ArrayList<Map<?, ?>> differResult) {
        String resultJson = "";
        ObjectMapper dataMapper = new ObjectMapper();

        try {
            resultJson = dataMapper.writerWithDefaultPrettyPrinter().writeValueAsString(differResult);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return resultJson;
    }
}
