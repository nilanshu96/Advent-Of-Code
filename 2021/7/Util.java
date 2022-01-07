import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.util.Arrays;

public class Util {

    static int[] getPositionList(String fileName) {

        int[] positionList = null;
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            String line = reader.readLine();
            positionList = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return positionList;
    }

    static void printIntArray(int[] arr) {
        for(int i=0; i<arr.length; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }
}