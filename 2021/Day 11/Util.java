import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Arrays;

public class Util {
    static int[][] readEnergyLevels(String filename) {
        int[][] energyLevels = null;
        try {
            List<String> input = Files.readAllLines(Paths.get(filename));
            energyLevels = new int[input.size()][input.get(0).length()];

            for(int i=0; i<input.size(); i++) {
                energyLevels[i] = Arrays.stream(input.get(i).split("")).mapToInt(Integer::parseInt).toArray();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return energyLevels;
    }

    static void print2DIntArray(int[][] arr) {
        if(arr == null) return;

        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) System.out.print(arr[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}