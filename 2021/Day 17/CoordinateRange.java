public class CoordinateRange extends Pair<String, Pair<Integer, Integer>> {

    CoordinateRange(String dimension, int r1, int r2) {
        super(dimension, new Pair<>(Integer.valueOf(r1), Integer.valueOf(r2)));
    }

    String getName() {
        return this.first;
    }

    Pair<Integer,Integer> getRange() {
        return this.second;
    }

    int getStart() {
        return this.second.first.intValue();
    }

    int getEnd() {
        return this.second.second.intValue();
    }
}