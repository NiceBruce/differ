package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static String getType(String pathToFile) {
        return pathToFile.substring(pathToFile.lastIndexOf('.') + 1);
    }

    public static String readDataFromFile(String pathToFile) throws Exception {

        Path path = Paths.get(pathToFile).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }

    public static ObjectMapper selectParser(String pathToFile) throws Exception {

        String typeOfFile = getType(pathToFile);

        return switch (typeOfFile) {
            case "yml", "yaml" -> new ObjectMapper(new YAMLFactory());
            case "json" -> new ObjectMapper();
            default -> throw new Exception("Unknown format: '" + typeOfFile + "'");
        };
    }

    public final Map<String, Object> parseData(String pathToFile) throws Exception {

        String rawData = readDataFromFile(pathToFile);
        ObjectMapper dataMapper = selectParser(pathToFile);

        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };

        return dataMapper.readValue(rawData, typeReference);
    }
}
