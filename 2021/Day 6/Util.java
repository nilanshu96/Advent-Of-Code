import java.io.FileInputStream;
import java.util.Arrays;
import java.io.IOException;

public class Util {
    static int[] getInitialFishList(final String fileName) {

        int[] initialFishList = null;

        try(final FileInputStream fin = new FileInputStream(fileName);
        final InputReader reader = new InputReader(fin);) {
            String numList = reader.next();
            initialFishList = Arrays.stream(numList.split(",")).mapToInt(Integer::parseInt).toArray();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return initialFishList;
    }

    static void printIntArray(int[] arr) {
        for(int i=0; i<arr.length; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }
}