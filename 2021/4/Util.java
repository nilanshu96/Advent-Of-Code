import java.util.List;
import java.util.ArrayList;

public class Util {
    static List<BingoCard> readBingoCards(final InputReader reader) {
        
        List<BingoCard> bingoCards = new ArrayList<>();
        
        boolean inputOver = false;

        if(reader == null) return null;

        while(!inputOver) {

            BingoCard bingoCard = new BingoCard();
            for(int i=0; i<5; i++) {
                List<Pair<Integer,Boolean>> bingoCardRow = new ArrayList<>(5); 
                for(int j=0; j<5; j++) {
                    Integer num = reader.nextInt();
                    if(num == null) {
                        inputOver = true;
                        break;
                    }
                    bingoCardRow.add(new Pair<>(num, Boolean.FALSE));
                }

                if(inputOver) break;

                bingoCard.add(bingoCardRow);
            }

            if(inputOver) break;

            bingoCards.add(bingoCard);
        }

        return bingoCards;
    }

    static Pair<BingoCard,Integer> playBingo(final List<BingoCard> bingoCards, final int[] numbers) {
        BingoCard winner = null;
        Integer bingoNum = -1;

        for(int i=0; i<numbers.length; i++) {
            for(int j=0; j<bingoCards.size(); j++) {
                bingoCards.get(j).mark(numbers[i]);
                if(bingoCards.get(j).checkBingo()) {
                    winner = bingoCards.get(j);
                    bingoNum = numbers[i];
                    break;
                }
            }
            if(winner != null) break;
        }

        return new Pair<>(winner, bingoNum);
    }

    static Pair<BingoCard,Integer> playBingo(final List<BingoCard> bingoCards, final int[] numbers, final int idx) {
        BingoCard winner = null;
        Integer bingoNumIdx = -1;

        for(int i=idx; i<numbers.length; i++) {
            for(int j=0; j<bingoCards.size(); j++) {
                bingoCards.get(j).mark(numbers[i]);
                if(bingoCards.get(j).checkBingo()) {
                    winner = bingoCards.get(j);
                    bingoNumIdx = i;
                    break;
                }
            }
            if(winner != null) break;
        }

        return new Pair<>(winner, bingoNumIdx);
    }
}