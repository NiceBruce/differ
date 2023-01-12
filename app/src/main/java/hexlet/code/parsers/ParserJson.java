package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Parser;

import java.util.Map;

public class ParserJson implements Parser {
    @Override
    public final Map<String, Object> parseData(String rawData) throws JsonProcessingException {

        ObjectMapper dataMapper = new ObjectMapper();

        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };

        return dataMapper.readValue(rawData, typeReference);
    }
}
