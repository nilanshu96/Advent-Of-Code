import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Util {
    static List<String> readALUInstructions(String filename) {
        try {
           return Files.readAllLines(Paths.get(filename)); 
        } catch(IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}