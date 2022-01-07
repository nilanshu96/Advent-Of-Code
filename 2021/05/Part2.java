import java.util.List;

public class Part2 {
    public static void main(String[] args) {
        List<Point> points = Util.readPoints("input.txt");
        Pair<Point,Point> cornerPoints = Util.getLowestAndHighestPoints(points);
        List<Line> lines = Util.getLines(points);
        
        int xOffset = cornerPoints.first.first;
        int yOffset = cornerPoints.first.second;

        int colSize = cornerPoints.second.first - xOffset + 1;
        int rowSize = cornerPoints.second.second - yOffset + 1;

        int[][] TwoDSpace = new int[rowSize][colSize];

        lines.stream().forEach(line -> travel2DSpace(TwoDSpace, line, xOffset, yOffset));

        int count = 0;

        for(int i=0; i<rowSize; i++) {
            for(int j=0; j<colSize; j++) {
                if(TwoDSpace[i][j] >= 2) count++;
            }
        }

        // Util.printTwoDSpace(TwoDSpace);

        System.out.println("Answer: " + count);

    }

    private static void travel2DSpace(final int[][] TwoDSpace, final Line line, final int xOffset, final int yOffset) {

        if(line.isHorizontal()) {
            int lowerX, higherX;
            int y = line.first.second - yOffset;

            if(line.first.first < line.second.first) {
                lowerX = line.first.first;
                higherX = line.second.first;
            } else {
                lowerX = line.second.first;
                higherX = line.first.first;
            }

            lowerX -= xOffset;
            higherX -= xOffset;

            for(int i = lowerX; i<= higherX; i++) {
                TwoDSpace[y][i]++;
            }
        } else if(line.isVertical()) {
            int lowerY, higherY;
            int x = line.first.first - xOffset;

            if(line.first.second < line.second.second) {
                lowerY = line.first.second;
                higherY = line.second.second;
            } else {
                lowerY = line.second.second;
                higherY = line.first.second;
            }

            lowerY -= yOffset;
            higherY -= yOffset;

            for(int i = lowerY; i<=higherY; i++) {
                TwoDSpace[i][x]++;
            }           
        } else {
            Point p1 = line.first;
            Point p2 = line.second;

            int dx = p2.first.compareTo(p1.first);
            int dy = p2.second.compareTo(p1.second);

            dx = dx < 0 ? -1 : 1;
            dy = dy < 0 ? -1 : 1;

            int x1 = p1.first - xOffset;
            int y1 = p1.second - yOffset;
            int x2 = p2.first - xOffset;
            int y2 = p2.second - yOffset;

            for(int i=x1, j=y1; (i != x2 + dx) && (j != y2 + dy); i+=dx, j+=dy) {
                TwoDSpace[j][i]++;
            }
        }
    }
}