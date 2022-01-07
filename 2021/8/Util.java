import java.util.List;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Util {

    static List<String> readAllSignals(String filename) {
        List<String> signals = null;

        try {
            signals = Files.readAllLines(Paths.get(filename));
        } catch(IOException e) {
            e.printStackTrace();
        }

        return signals;
    }

    static String[] getOutputValuesArray(String input) {
        String outputString = input.split("\\|")[1];
        return outputString.trim().split("\\s+");
    }

    static String getInputValues(String input) {
        String outputString = input.split("\\|")[0];
        return outputString.trim();
    }

    static String getOutputValues(String input) {
        String outputString = input.split("\\|")[1];
        return outputString.trim();
    }

    static <T> void printArray(T[] arr) {
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    static String sortString(String s) {
        char[] charArr = s.toCharArray();
        Arrays.sort(charArr);
        return String.valueOf(charArr);
    }

    static boolean stringContainsAllCharOf(String a, String b) {
        char[] arrB = b.toCharArray();

        boolean contains = true;

        for(int i=0; i<arrB.length; i++) {
            if(a.indexOf(arrB[i]) == -1) return false;
        }

        return true;
    }
}