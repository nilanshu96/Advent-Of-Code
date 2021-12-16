import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class InputReader {

    private static List<String> lines;
    private static String filenameRead;

    private InputReader() {};

    static List<String> readAllLines(String filename) throws IOException {
        if(filename == null) return null;

        if(filename.equals(filenameRead) && lines != null && !lines.isEmpty()) {
            return lines;
        }

        lines = Files.readAllLines(Paths.get(filename));
        lines.removeAll(Arrays.asList("",null));
        filenameRead = filename;

        return lines;
    }
}