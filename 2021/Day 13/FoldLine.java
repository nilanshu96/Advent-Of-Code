public class FoldLine extends Pair<String,Integer> {
    FoldLine(String axis, int value) {
        super(axis, Integer.valueOf(value));
    }

    String getAxis() {
        return getFirst();
    }

    int getValue() {
        return getSecond().intValue();
    }

    boolean isVertical() {
        return "x".equals(getAxis());
    }
}