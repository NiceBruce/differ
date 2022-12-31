package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.LinkedList;
import java.util.Map;

public class Parser {

    public static <T> Map<T, T> parseYaml(String rawData) throws JsonProcessingException {
        ObjectMapper dataMapper = new ObjectMapper(new YAMLFactory());
        TypeReference<Map<T, T>> typeReference = new TypeReference<>() {
        };
        return dataMapper.readValue(rawData, typeReference);
    }

    public static <T> Map<T, T> parseJson(String rawData) throws JsonProcessingException {
        ObjectMapper dataMapper = new ObjectMapper();
        TypeReference<Map<T, T>> typeReference = new TypeReference<>() {
        };
        return dataMapper.readValue(rawData, typeReference);
    }

    public static <T> LinkedList<Map<T, T>> parseDataToMap(LinkedList<Map<String, String>> rawData) throws Exception {

        LinkedList<Map<T, T>> result = new LinkedList<>();

        for (var data : rawData) {

            String type = (String) data.keySet().toArray()[0];

            switch (type) {
                case "yml":
                case "yaml":
                    result.add(parseYaml(data.get(type)));
                    break;
                case "json":
                    result.add(parseJson(data.get(type)));
                    break;
                default:
                    throw new Exception("Unknown format: '" + type + "'");
            }
        }

        return result;
    }
}
