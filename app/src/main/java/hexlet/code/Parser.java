package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class Parser {


    public static <T> Map<T, T> parseDataToMap(String dataFromFile) throws Exception {

        if (dataFromFile.equals("")) {
            return Map.of();
        }

        ObjectMapper dataMapper = new ObjectMapper();

        TypeReference<Map<T, T>> typeReference = new TypeReference<>() {
        };

        return dataMapper.readValue(dataFromFile, typeReference);
    }
}
