package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {


    public final Format createFormatter(String type) {
        Format format = null;

        if (type.equals("json")) {
            format = new Json();
        } else if (type.equals("plain")) {
            format = new Plain();
        } else {
            format = new Stylish();
        }

        return format;
    }

}
