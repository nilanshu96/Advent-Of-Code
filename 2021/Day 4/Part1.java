import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Part1 {
    public static void main(String[] args) {
        try(final FileInputStream fin = new FileInputStream("input.txt");
            final InputReader reader = new InputReader(fin);) {
            
            String line = reader.next();
            int[] numbers = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            
            List<BingoCard> bingoCards = Util.readBingoCards(reader);
    
            Pair<BingoCard,Integer> winner = Util.playBingo(bingoCards, numbers);

            System.out.println(winner.one);
            System.out.println("Bingo Number: " + winner.two);
            System.out.println("Answer: " + winner.one.getSumOfUnmarked() * winner.two);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    

}