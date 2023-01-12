package hexlet.code;

import hexlet.code.parsers.ParserJson;
import hexlet.code.parsers.ParserYML;

public class ParserFactory {
    public final Parser create(String typeOfParser) throws Exception {
        return switch (typeOfParser) {
            case "yml", "yaml" -> new ParserYML();
            case "json" -> new ParserJson();
            default -> throw new Exception("Unknown format: '" + typeOfParser + "'");
        };
    }
}
