package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Format;
import java.util.ArrayList;
import java.util.Map;

public final class Json implements Format {

    @Override
    public <T> String print(ArrayList<Map<?, ?>> differResult) throws Exception {

        String resultJson = "";
        ObjectMapper dataMapper = new ObjectMapper();

        resultJson = dataMapper.writerWithDefaultPrettyPrinter().writeValueAsString(differResult);

        return resultJson;
    }
}
