package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    private final String typeOfFile;
    private final String filePath;
    private final TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
    };

    public Parser(String pathToFile) {
        this.filePath = pathToFile;
        this.typeOfFile = filePath.substring(filePath.lastIndexOf('.') + 1);
    }

    public static String readDataFromFile(String pathToFile) throws Exception {

        Path path = Paths.get(pathToFile).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }


    public final Map<String, Object> parseDataToMap() throws Exception {

        ObjectMapper dataMapper;
        String rawData = readDataFromFile(filePath);

        dataMapper = switch (typeOfFile) {
            case "yml", "yaml" -> new ObjectMapper(new YAMLFactory());
            case "json" -> new ObjectMapper();
            default -> throw new Exception("Unknown format: '" + typeOfFile + "'");
        };

        return dataMapper.readValue(rawData, typeReference);
    }
}
