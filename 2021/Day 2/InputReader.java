import java.io.*;
import java.util.*;

public class InputReader {
    BufferedReader reader;
    StringTokenizer tokenizer;
    private final static int BUFFER_SIZE = 32768;

    InputReader(Reader in) {
        reader = new BufferedReader(in, BUFFER_SIZE);
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
                return null;
            }
        }
        return tokenizer.nextToken();
    }

    Integer nextInt() {
        String num = next();
        if(num == null) return null;

        return Integer.decode(num);
    }

    void close() {
        try {
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}