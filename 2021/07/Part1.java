import java.util.Arrays;

public class Part1 {
    public static void main(String[] args) {
        int[] positionList = Util.getPositionList("input.txt");
        // Util.printIntArray(positionList);
        Arrays.sort(positionList);

        double median = 0;
        double answer = 0;
        int len = positionList.length;

        if(len%2 == 0) {
            int pos1 = len/2;
            int pos2 = pos1 - 1;

            median = (double)positionList[pos1]/2;
            median += (double)positionList[pos2]/2;
        } else {
            median  = positionList[len/2];
        }

        for(int i=0; i<len; i++) {
            answer += Math.abs(median - positionList[i]);
        }

        System.out.println("Answer: " + answer);

    }
}