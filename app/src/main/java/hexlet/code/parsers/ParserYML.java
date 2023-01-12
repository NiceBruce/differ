package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import hexlet.code.Parser;

import java.util.Map;

public class ParserYML implements Parser {
    @Override
    public final Map<String, Object> parseData(String rawData) throws JsonProcessingException {

        ObjectMapper dataMapper = new ObjectMapper(new YAMLFactory());

        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };

        return dataMapper.readValue(rawData, typeReference);
    }
}
