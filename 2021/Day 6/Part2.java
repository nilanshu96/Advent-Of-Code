import java.util.Arrays;

public class Part2 {
    public static void main(String[] args) {
        int[] initialFishList = Util.getInitialFishList("input.txt");
        long[] cycleDays = new long[9];

        for(int i=0; i<initialFishList.length; i++) {
            int fish = initialFishList[i];
            cycleDays[fish]++;
        }

        int daysToCycle = 256;

        for(int day=1; day<=daysToCycle; day++) processCycleDay(cycleDays);

        long fishCount = Arrays.stream(cycleDays).sum();

        System.out.println("Fish count at day " + daysToCycle + " : " + fishCount);

    }

    private static void processCycleDay(long[] cycleDays) {
        long zeroDayVal = cycleDays[0];

        for(int i=0; i<8; i++) {
            cycleDays[i] = cycleDays[i+1];
        }

        cycleDays[6] += zeroDayVal;
        cycleDays[8] = zeroDayVal;
    }
}