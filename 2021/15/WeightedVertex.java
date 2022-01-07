public class WeightedVertex extends Pair<Pair<Integer,Integer>, Integer> implements Comparable<WeightedVertex> {

    WeightedVertex(int row, int col, int weight) {
        super(new Pair<>(Integer.valueOf(row), Integer.valueOf(col)), Integer.valueOf(weight));
    }

    int getRow() {
        return first.first;
    }

    int getCol() {
        return first.second;
    }

    int getWeight() {
        return second;
    }

    @Override
    public int compareTo(WeightedVertex ob) {
        return second.compareTo(ob.second);
    }
}