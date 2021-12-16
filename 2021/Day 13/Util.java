import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Util {
    static List<String> getAllLines(String filename) {
        try {
            return InputReader.readAllLines(filename);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    static List<Point> readAllPoints(String filename) {
        List<Point> points = new ArrayList<>();

        try {
            List<String> lines = InputReader.readAllLines(filename);

            for(int i=0; i<lines.size(); i++) {
                if(lines.get(i).matches("\\d+,\\d+")) {
                    String[] parts = lines.get(i).split(",");
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);

                    Point p = new Point(x,y);
                    points.add(p);
                };
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return points;
    }

    static List<FoldLine> readAllFoldLines(String filename) {
        List<FoldLine> foldLines = new ArrayList<>();

        try {
            List<String> lines = InputReader.readAllLines(filename);
            
            Pattern pat = Pattern.compile("[xy]=\\d+");
            for(int i=0; i<lines.size(); i++) {
                if(lines.get(i).matches("\\d+,\\d+")) continue;

                Matcher mat = pat.matcher(lines.get(i));
                while(mat.find()) {
                    String fold = mat.group();
                    String[] parts = fold.split("=");
                    String axis = parts[0];
                    int value = Integer.parseInt(parts[1]);

                    FoldLine foldLine = new FoldLine(axis, value);
                    foldLines.add(foldLine);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return foldLines;
    }

    static Point getLargestPoint(List<Point> points) {
        if(points == null || points.isEmpty()) return null;
        
        int highestX, highestY;

        highestX = points.get(0).getX();
        highestY = points.get(0).getY();

        for(int i=1; i<points.size(); i++) {
            
            Point p = points.get(i);

            if(p.getX() > highestX) {
                highestX = p.getX();
            }

            if(p.getY() > highestY) {
                highestY = p.getY();
            }
        }

        return new Point(highestX, highestY);
    }

    static int[][] copyIntMatrix(int[][] matrix, int x1, int x2, int y1, int y2) {
        int[][] copy = new int[y2-y1+1][x2-x1+1];

        for(int i=y1; i<=y2; i++){
            for(int j=x1; j<=x2; j++) {
                copy[i-y1][j-x1] = matrix[i][j];
            }
        }

        return copy;
    }

    static int[][] copyIntMatrix(int[][] matrix) {
        if(matrix == null) return null;
        return copyIntMatrix(matrix, 0, matrix[0].length - 1, 0, matrix.length - 1);
    }

    static void flipIntMatrix(int[][] matrix, boolean vertical) {
        if(matrix == null) return;

        if(vertical) {
            for(int i=0; i<matrix.length; i++) {
                for(int j=0, k=matrix[i].length-1; j<k; j++, k--) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[i][k];
                    matrix[i][k] = temp;
                }
            }
        } else {
            for(int i=0, k=matrix.length-1; i<k; i++,k--) {
                for(int j=0; j<matrix[i].length; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[k][j];
                    matrix[k][j] = temp;
                }
            }
        }
    }

    static int[][] getMatrixFromPoints(List<Point> points, Point largestPoint) {

        int rowSize = largestPoint.getY() + 1;
        int colSize = largestPoint.getX() + 1;

        int[][] matrix = new int[rowSize][colSize];

        for(int i=0; i<points.size(); i++) {
            Point p = points.get(i);

            int x = p.getX();
            int y = p.getY();

            matrix[y][x] = 1;
        }

        return matrix;
    }

    static int[][] combineMatrix(int[][] mat1, int[][] mat2, boolean vertical) {
        if(mat1 == null || mat2 == null) return null;

        if(vertical) {
            int[][] biggerMatrix, smallerMatrix;

            if(mat1[0].length > mat2[0].length) {
                biggerMatrix = mat1;
                smallerMatrix = mat2;
            } else {
                biggerMatrix = mat2;
                smallerMatrix = mat1;
            }

            int[][] newMatrix = copyIntMatrix(biggerMatrix);

            int xOffset = biggerMatrix[0].length - smallerMatrix[0].length;

            for(int i=0; i<smallerMatrix.length; i++) {
                for(int j=0; j<smallerMatrix[i].length; j++) {
                    int x = j + xOffset;
                    newMatrix[i][x] |= smallerMatrix[i][j];
                }
            }

            return newMatrix;
        } else {
            int[][] biggerMatrix, smallerMatrix;

            if(mat1.length > mat2.length) {
                biggerMatrix = mat1;
                smallerMatrix = mat2;
            } else {
                biggerMatrix = mat2;
                smallerMatrix = mat1;
            }

            int[][] newMatrix = newMatrix = copyIntMatrix(biggerMatrix);

            int yOffset = biggerMatrix.length - smallerMatrix.length;

            for(int i=0; i<smallerMatrix.length; i++) {
                for(int j=0; j<smallerMatrix[i].length; j++) {
                    int y = i + yOffset;
                    newMatrix[y][j] |= smallerMatrix[i][j];
                }
            }

            return newMatrix;
        }
    }

    static void printOrigamiMatrix(int[][] matrix) {
        if(matrix == null) return;

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                if(matrix[i][j] > 0) System.out.print("# ");
                else System.out.print(". ");
            }
            System.out.println();
        }
    }

    static int countDots(int[][] matrix) {
        int count = 0;
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                if(matrix[i][j] > 0) count++;
            }
        }
        return count;
    }
}