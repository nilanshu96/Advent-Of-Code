import java.util.*;
import java.io.*;

public class Part2 {
    public static void main(String[] args) {
        InputReader reader = null;
        try {
            FileInputStream inputStream = new FileInputStream("input.txt");
            reader = new InputReader(inputStream);

            int count = 0;
            int prevSum = 0;
            int currSum = 0;
            Integer x,y;
            ArrayDeque<Integer> deque = new ArrayDeque<>();

            for(int i=0; i<3; i++) {
                x = reader.nextInt();
                prevSum += x;
                deque.add(x);
            }

            while((y = reader.nextInt()) != null) {
                x = deque.poll();
                deque.add(y);
                currSum = prevSum - x + y;
                if(currSum > prevSum) count++;
                prevSum = currSum;
            }

            System.out.println(count);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) reader.close();
        }
    }
}