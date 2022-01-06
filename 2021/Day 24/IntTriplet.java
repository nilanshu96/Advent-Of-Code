public class IntTriplet {
    private int idx, x, y;

    IntTriplet(int idx, int x, int y) {
        this.idx = idx;
        this.x = x;
        this.y = y;
    }

    int getIdx() {
        return idx;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + idx + ": " + x +  "," + y + ")";
    }
}