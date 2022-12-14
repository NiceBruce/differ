package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true)
public class App implements Callable<Integer> {
    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: stylish]")
    String format = "filepath1 filepath2";

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filePath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filePath2;

    public static String getFile(String pathToFile) throws Exception {
        Path path = Paths.get(pathToFile).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }

    public static Map<String, String> readDataFromJson(String jsonFile) throws IOException {
        ObjectMapper dataMapper = new ObjectMapper();
        return dataMapper.readValue(jsonFile, new TypeReference<Map<String,String>>(){});
    }

    @Override
    public Integer call() throws Exception {

        String jsonFile1 = getFile(filePath1);
        String jsonFile2 = getFile(filePath2);

        System.out.println(Differ.generate(readDataFromJson(jsonFile1), readDataFromJson(jsonFile2)));

        return 0;
    }
    public static void main(String[] args) {

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }


}

