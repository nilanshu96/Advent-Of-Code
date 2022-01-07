import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;

public class Part2 {
    public static void main(String[] args) {
        long start,end;
        start = System.currentTimeMillis();

        List<Scanner> scanners = Util.readAllScanners("input.txt");

        ArrayDeque<Scanner> comparersList = new ArrayDeque<>();
        List<Scanner> comparedToList = new ArrayList<>(scanners);
        comparersList.offer(scanners.get(0));
        comparedToList.removeAll(comparersList);

        while(!comparersList.isEmpty()) {
            Scanner comparer = comparersList.poll();
            for(int i=0; i<comparedToList.size(); i++) {
                Scanner res = comparer.matchBeacons(comparedToList.get(i));
                if(res != null) {
                    scanners.remove(comparedToList.get(i));
                    scanners.add(res);
                    comparersList.offer(res);
                    comparedToList.set(i,res);
                }
            }
            comparedToList.removeAll(comparersList);
        }

        int maxManhattanDistance = 0;

        for(int i=0; i<scanners.size(); i++) {
            for(int j=i+1; j<scanners.size(); j++) {
                maxManhattanDistance = Math.max(maxManhattanDistance, scanners.get(i).getManhattanDistance(scanners.get(j)));
            }
        }

        end = System.currentTimeMillis();

        System.out.println("Answer: " + maxManhattanDistance);
        System.out.println("Execution time: " + TimeUnit.MILLISECONDS.toSeconds(end - start) + "s");
    }
}