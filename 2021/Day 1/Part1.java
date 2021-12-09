import java.io.*;

public class Part1 {
    public static void main(String[] args) {
        Integer x,y;

        try {
            FileInputStream f = new FileInputStream("input.txt");
            InputReader reader = new InputReader(f);

            int count = 0;
            x = reader.nextInt();
            while((y = reader.nextInt()) != null) {
                if(y.compareTo(x) > 0) count++;
                x = y;
            }
            System.out.println(count);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}