package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {


    public static <T> Map<T, T> parseDataToMap(String dataFromFile, String fileFormat) throws Exception {


        if (dataFromFile.equals("")) {
            return Map.of();
        }

        ObjectMapper dataMapper = (fileFormat.equals("yml"))
                ? new ObjectMapper(new YAMLFactory()) : new ObjectMapper();

        TypeReference<Map<T, T>> typeReference = new TypeReference<>() {
        };

        return dataMapper.readValue(dataFromFile, typeReference);
    }
}
