package request;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static java.awt.SystemColor.text;

public record Request(
        String httpMethod,
        String target,
        String httpVersion,
        Headers headers
) {
    public static int MAXIMUM_MESSAGE = 8192;
    public static Request fromInputStream(InputStream inputStream) throws IOException {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        );
        String line;
        StringBuilder request = new StringBuilder();
        ArrayList<String> lines = new ArrayList<>();
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            lines.add(line);
            if (line.contains("\r\n\r\n")) break;
        }

        String[] requestLines = lines.getFirst().split(" ");
        return new Request(requestLines[0], requestLines[1], requestLines[2], null);
    }
}
