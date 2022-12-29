package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {

    public final Format createFormatter() {
        return new Stylish();
    }

    public final Format createFormatter(String type) {
        Format format = null;

        switch (type) {
            case "json":
                format = new Json();
                break;
            case "plain":
                format = new Plain();
                break;
            default:
                format = new Stylish();
        }

        return format;
    }

}
