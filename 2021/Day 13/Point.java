public class Point extends Pair<Integer, Integer> {

    Point(int x, int y) {
        super(Integer.valueOf(x), Integer.valueOf(y));
    }

    int getX() {
        return getFirst().intValue();
    }

    int getY() {
        return getSecond().intValue();
    }
}