import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Util {
    static List<String> readAllChunks(String filename) {
        List<String> chunks = null;

        try {
            chunks = Files.readAllLines(Paths.get(filename));
        } catch(IOException e) {
            e.printStackTrace();
        }

        return chunks;
    }
}