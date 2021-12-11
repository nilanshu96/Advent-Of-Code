public class Line extends Pair<Point,Point> {
    Line(Point first, Point second) {
        super(first, second);
    }

    boolean isVertical() {
        return first.first.equals(second.first);
    }

    boolean isHorizontal() {
        return first.second.equals(second.second);
    }
}