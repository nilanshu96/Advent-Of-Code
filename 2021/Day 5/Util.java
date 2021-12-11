import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;

public class Util {
    static List<Point> readPoints(String fileName) {

        final String token = ",->";
        List<Point> points = new ArrayList<>();

        try(FileInputStream fin = new FileInputStream(fileName);
            InputReader reader = new InputReader(fin, token);) {
            
            Integer x,y;
            while((x = reader.nextInt()) != null && (y = reader.nextInt()) != null) {
                points.add(new Point(x,y));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return points;
    }

    static Pair<Point,Point> getLowestAndHighestPoints(List<Point> points) {
        if(points == null || points.isEmpty()) return null;

        Integer minX = points.get(0).first;
        Integer minY = points.get(0).second;

        Integer maxX = points.get(0).first;
        Integer maxY = points.get(0).second;

        for(int i=1; i<points.size(); i++) {
            Point p = points.get(i);

            if(p.first < minX) minX = p.first;
            else if(p.first > maxX) maxX = p.first;

            if(p.second < minY) minY = p.second;
            else if(p.second > maxY) maxY = p.second;
        }

        Point lowest = new Point(minX, minY);
        Point highest = new Point(maxX, maxY);

        return new Pair<>(lowest, highest);
    }

    static List<Line> getLines(List<Point> points) {
        List<Line> lines = new ArrayList<>();
        int idx = 0;
        Point p1 = null;
        Point p2 = null;

        for(int i=0; i<points.size(); i++) {
            idx = idx % 2;

            if(idx == 0) {
                p1 = points.get(i);
            } else if(idx == 1) {
                p2 = points.get(i);
                lines.add(new Line(p1,p2));
            }

            idx++;
        }

        return lines;
    }

    static void printTwoDSpace(int[][] TwoDSpace) {
        for(int i=0; i<TwoDSpace.length; i++) {
            for(int j=0; j<TwoDSpace[i].length; j++) {
                if(TwoDSpace[i][j] == 0) System.out.print(". ");
                else System.out.print(TwoDSpace[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}