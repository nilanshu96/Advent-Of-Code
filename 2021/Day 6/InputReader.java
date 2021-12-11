import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Closeable;

public class InputReader implements Closeable{
    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private String token = " \t\n\r\f";
    private final static int BUFFER_SIZE = 32768;

    InputReader(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in), BUFFER_SIZE);
        tokenizer = null;
    }

    InputReader(InputStream in, String token) {
        this(in);
        this.token = this.token + token;
    }

    String next() {
        try {
            while(tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if(line == null) return null;

                tokenizer = new StringTokenizer(line, token);
            }
        } catch(IOException e) {
                e.printStackTrace();
                return null;
        }

        return tokenizer.nextToken();    
    }

    Integer nextInt() {
        String num = next();
        if(num == null) return null;

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