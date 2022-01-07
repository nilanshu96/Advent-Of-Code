import java.io.*;

public class Part2 {
    public static void main(String[] args) {
        InputReader reader = null;

        try {
            FileReader f = new FileReader("input.txt");
            reader = new InputReader(f);
            String cmd;
            Integer val;
            int depth = 0;
            int distance = 0;
            int aim = 0;

            while(((cmd = reader.next()) != null) && ((val = reader.nextInt()) != null)) {
                switch(cmd) {
                    case "forward":
                        distance += val;
                        depth += aim * val;
                        break;
                    case "down":
                        aim += val;
                        break;
                    case "up":
                        aim -= val;
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