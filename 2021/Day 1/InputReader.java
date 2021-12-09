import java.io.*;
import java.util.*;

public class InputReader {
    BufferedReader reader;
    StringTokenizer tokenizer;
    private final static int BUFFER_SIZE = 32768;

    InputReader(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream), BUFFER_SIZE);
        tokenizer = null;
    }

    String next() {
        while(tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                String line = reader.readLine();
                if(line == null) return null;
                tokenizer = new StringTokenizer(line);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        return tokenizer.nextToken();
    }

    Integer nextInt() {
        String num = next();
        if(num == null) return null;
        else return Integer.decode(num); 
    }
    }