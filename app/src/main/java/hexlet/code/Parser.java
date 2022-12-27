package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Parser {

    public static String getFile(String pathToFile) throws Exception {

        Path path = Paths.get(pathToFile).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }

    public static <T> Map<T, T> readDataFromFile(String fileName) throws Exception {

        String dataFromFile = getFile(fileName);

        if (dataFromFile.equals("")) {
            return Map.of();
        }

        ObjectMapper dataMapper = new ObjectMapper();

        TypeReference<Map<T, T>> typeReference = new TypeReference<>() {
        };
        return dataMapper.readValue(dataFromFile, typeReference);
    }
}
