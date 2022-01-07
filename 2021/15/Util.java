import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {
    static int[][] readMap(String filename) {
        
        int[][] map = null;
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            if(lines == null || lines.isEmpty()) return null;

            map = new int[lines.size()][];

            for(int i=0; i<lines.size(); i++) {
                map[i] = Arrays.stream(lines.get(i).split("")).mapToInt(Integer::parseInt).toArray();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    static int[][] get5xMap(int[][] map) {
        int rowSize = map.length;
        int colSize = map[0].length;

        int newRowSize = rowSize * 5;
        int newColSize = colSize * 5;

        int[][] map5x = new int[newRowSize][newColSize];

        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                partialMapCalculation(i,j,map, map5x);
            }
        }

        return map5x;
    } 

    private static void partialMapCalculation(int r, int c, int[][] original, int[][] original5x) {
        int rows = original.length;
        int cols = original[0].length;

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                int num = original[i][j] + r + c;
                if(num > 9) num = num % 9;
                int row = i + rows*r;
                int col = j + cols*c;

                original5x[row][col] = num;
            }
        }
    }

    static void print2DIntArray(int[][] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    static void fillIntMatrix(int[][] matrix, int num) {
        for(int i=0; i<matrix.length; i++)
            for(int j=0; j<matrix[i].length; j++)
                matrix[i][j] = num;
    }
}