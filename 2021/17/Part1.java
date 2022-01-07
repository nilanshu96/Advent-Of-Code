import java.util.*;

public class Part1 {
    public static void main(String[] args) {
        Pair<CoordinateRange, CoordinateRange> targetArea = Util.readTargetArea("input.txt");

        int[] numSummations = getNumberSummations(200);

        int x = getMinX(targetArea.first, numSummations);
        int y = Util.absInt(targetArea.second.getStart()) - 1;

        System.out.println(x + "," + y);
        System.out.println("Answer: " + numSummations[y]);
    }

    private static int[] getNumberSummations(int limit) {
        int[] summations = new int[limit+1];

        for(int i=1; i<=limit; i++) {
            summations[i] = i + summations[i-1];
        }

        return summations;
    }

    private static int getMinX(CoordinateRange xRange, int[] numSummations) {
        int num = Arrays.binarySearch(numSummations, xRange.getStart());
        return num >=0 ? num : Util.absInt(num) - 1;
    }
}