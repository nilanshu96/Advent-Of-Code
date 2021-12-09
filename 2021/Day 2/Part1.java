import java.io.*;

public class Part1 {
    public static void main(String[] args) {
        InputReader reader = null;

        try {
            FileReader f = new FileReader("input.txt");
            reader = new InputReader(f);
            String cmd;
            Integer val;
            int depth = 0;
            int distance = 0;

            while(((cmd = reader.next()) != null) && ((val = reader.nextInt()) != null)) {
                switch(cmd) {
                    case "forward":
                        distance += val;
                        break;
                    case "down":
                        depth += val;
                        break;
                    case "up":
                        depth -= val;
                        break;
                }
            }

            System.out.println("depth: " + depth);
            System.out.println("distance: " + distance);
            System.out.println("answer: " + depth*distance);

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) reader.close();
        }
    }
}