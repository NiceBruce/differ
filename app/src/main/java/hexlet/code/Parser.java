package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Map;
public interface Parser {
    Map<String, Object> parseData(String rawData) throws JsonProcessingException;
}
