import java.util.Arrays;

public class Part1 {
    public static void main(String[] args) {
        int[] initialFishList = Util.getInitialFishList("input.txt");
        int[] cycleDays = new int[9];

        for(int i=0; i<initialFishList.length; i++) {
            int fish = initialFishList[i];
            cycleDays[fish]++;
        }

        int daysToCycle = 80;

        for(int day=1; day<=daysToCycle; day++) processCycleDay(cycleDays);

        int fishCount = Arrays.stream(cycleDays).sum();

        System.out.println("Fish count at day " + daysToCycle + " : " + fishCount);

    }

    private static void processCycleDay(int[] cycleDays) {
        int zeroDayVal = cycleDays[0];

        for(int i=0; i<8; i++) {
            cycleDays[i] = cycleDays[i+1];
        }

        cycleDays[6] += zeroDayVal;
        cycleDays[8] = zeroDayVal;
    }
}