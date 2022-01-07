import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Closeable;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStream;

public class InputReader implements Closeable{
    BufferedReader reader;
    StringTokenizer tokenizer;
    private final static int BUFFER_SIZE = 32768;

    InputReader(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in), BUFFER_SIZE);
        tokenizer = null;
    }

    String next() {
        try {
            while(tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if(line == null) return null;

                tokenizer = new StringTokenizer(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
        return tokenizer.nextToken();
    }

    Integer nextInt() {
        String num = next();
        if (num == null) return null;

        return Integer.decode(num);
    }

    @Override
    public void close() {
        try {
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}