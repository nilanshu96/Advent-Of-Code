import java.util.List;
import java.util.ArrayList;

public class BingoCard {

    private List<List<Pair<Integer,Boolean>>> bingoCard;

    public BingoCard() {
        bingoCard = new ArrayList<>(5);
    }

    public List<Pair<Integer,Boolean>> get(int idx) {
        return bingoCard.get(idx);
    }

    public void add(List<Pair<Integer,Boolean>> list) {
        bingoCard.add(list);
    }

    public void mark(int num) {
        boolean marked = false;
        for(int i=0; i<bingoCard.size(); i++) {
            for(int j=0; j<bingoCard.get(i).size(); j++) {
                if(num == bingoCard.get(i).get(j).one) {
                    bingoCard.get(i).get(j).two = Boolean.TRUE;
                    marked = true;
                    break;
                }
            }
            if(marked) break;
        }
    }

    public boolean checkBingo() {
        boolean bingo = false;

        for(int i=0; i<bingoCard.size(); i++) {
            boolean checkRow = true;
            for(int j=0; j<bingoCard.get(i).size(); j++) {
                if(bingoCard.get(i).get(j).two == Boolean.FALSE) {
                    checkRow = false;
                    break;
                }
            }

            if(checkRow) return true;
        }

        for(int j=0; j<bingoCard.size(); j++) {
            boolean checkColumn = true;
            for(int i=0; i<bingoCard.get(j).size(); i++) {
                if(bingoCard.get(i).get(j).two == Boolean.FALSE) {
                    checkColumn = false;
                    break;
                }
            }
            if(checkColumn) return true;
        }

        return bingo;
    }

    public int getSumOfUnmarked() {
        int sum = 0;
        for(int i=0; i<bingoCard.size(); i++) {
            for(int j=0; j<bingoCard.get(i).size(); j++) {
                if(bingoCard.get(i).get(j).two == Boolean.FALSE) sum += bingoCard.get(i).get(j).one;
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---BINGO CARD---\n");
        bingoCard.stream().forEach(card -> {
            sb.append(card.toString());
            sb.append("\n");
        });
        sb.append("----------------\n");
        return sb.toString();
    }
}