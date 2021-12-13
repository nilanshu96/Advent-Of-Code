import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class Util {

    static int[][] readFloorHeightMap(String filename) {
        int[][] heightMap = null;

        try {
            List<String> input = Files.readAllLines(Paths.get(filename));
            if(input.get(0) != null) {
                heightMap = new int[input.size()][input.get(0).length()];

                for(int i=0; i<input.size(); i++) {
                    heightMap[i] = Arrays.stream(input.get(i).split("")).mapToInt(Integer::parseInt).toArray();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return heightMap;
    }

    static void print2DIntArray(int[][] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static <T> void printArray(T[] arr) {
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}