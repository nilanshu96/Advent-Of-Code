import java.util.List;

public class Part1 {
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
        }
    }
}