import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Util {
    static Pair<CoordinateRange, CoordinateRange> readTargetArea(String filename) {
        
        Pair<CoordinateRange, CoordinateRange> targetArea = null;
        
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            Pattern pat = Pattern.compile("[xy]=-?\\d+..-?\\d+");
            Matcher mat = pat.matcher(line);
            
            mat.find(); CoordinateRange cr1 = getCoordinateRange(mat.group());
            mat.find(); CoordinateRange cr2 = getCoordinateRange(mat.group());

            targetArea = new Pair<>(cr1, cr2);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return targetArea;
    }

    private static CoordinateRange getCoordinateRange(String str) {
        if(str == null) return null;

        String[] parts = str.split("=|\\.{2}");
        CoordinateRange cr = new CoordinateRange(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        return cr;
    }

    static int absInt(int x) {
        return x >= 0 ? x : -x;
    }

    static boolean isPresentInRange(int x1, int x2, int x) {
        return (x >= x1 && x <= x2);
    }
}