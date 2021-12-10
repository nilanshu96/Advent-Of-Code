import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

public class Part2 {
    public static void main(String[] args) {
        try(FileInputStream fin = new FileInputStream("input.txt");
            InputReader reader = new InputReader(fin);) {

            String line = reader.next();
            int[] numbers = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();

            List<BingoCard> bingoCards = Util.readBingoCards(reader);

            Pair<BingoCard,Integer> winner = null;

            int idx = 0;
            while(bingoCards.size() > 0) {
                winner = Util.playBingo(bingoCards, numbers, idx);
                idx = winner.two;
                bingoCards.remove(winner.one);
            }

            System.out.println(winner.one);
            System.out.println("Bingo number: " + numbers[idx]);
            System.out.println("Answer: " + winner.one.getSumOfUnmarked() * numbers[idx]);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}