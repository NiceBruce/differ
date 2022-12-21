package hexlet.code;

import static hexlet.code.Parser.readDataFromFile;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true)
 class App implements Callable<Integer> {
    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: stylish]")
    private String format = "filepath1 filepath2";

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filePath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filePath2;

    @Override
    public Integer call() throws Exception {

        Differ.generate(readDataFromFile(filePath1), readDataFromFile(filePath2));

        return 0;
    }
    public static void main(String[] args) {

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
