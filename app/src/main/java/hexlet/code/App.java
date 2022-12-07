package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true)
public class App implements Runnable {
    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: stylish]")
    String format = "filepath1 filepath2";

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String file1 = "filepath1";

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String file2 = "filepath1";
    @Override
    public void run() {
    }
    public static void main(String[] args) {

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }


}

