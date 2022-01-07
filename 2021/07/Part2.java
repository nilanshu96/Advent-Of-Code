import java.util.Arrays;

public class Part2 {
    public static void main(String[] args) {
        int[] positionList = Util.getPositionList("input.txt");
        Arrays.sort(positionList);

        double mean = Arrays.stream(positionList).average().getAsDouble();
        int pos1 = (int)Math.floor(mean);
        int pos2 = (int)Math.ceil(mean);

        int answer = Math.min(calculateTotalFuel(positionList,pos1), calculateTotalFuel(positionList, pos2));

        System.out.println("Answer: " + answer);
    }

    private static int calculateTotalFuel(int[] positions, int pos) {
        int sum = 0;

        for(int i=0; i<positions.length; i++) {
            int dist = Math.abs(positions[i] - pos);
            int fuelCost = dist * (dist + 1);
            fuelCost /= 2;
            sum += fuelCost;
        }

        return sum;
    }
}