import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;

public class Part1 {
    public static void main(String[] args) {
        long start,end;
        start = System.currentTimeMillis();

        List<Scanner> scanners = Util.readAllScanners("input.txt");

        ArrayDeque<Scanner> comparersList = new ArrayDeque<>();
        List<Scanner> comparedToList = new ArrayList<>(scanners);
        comparersList.offer(scanners.get(0));
        comparedToList.removeAll(comparersList);

        // Comparers transform the position of comparedTo scanners based on comparer's position. For Example: Scanner 0 will change Scanner 1's position on a match.
        // The use of queue makes sure that only those scanners are making transformation to other scanners that got initially transformed by scanner 0.
        // By transform I mean if distance between scanner A and B is (1,2,3), then all points within scanner B is added an offset of (1,2,3) as all points in B
        // would be an extra (1,2,3) distance away far from scanner A.
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

        Set<Coordinate> union = new TreeSet<>();
        scanners.stream().forEach(scanner -> union.addAll(scanner.getCoordinateList()));

        end = System.currentTimeMillis();

        System.out.println("Answer: " + union.size());
        System.out.println("Execution time: " + TimeUnit.MILLISECONDS.toSeconds(end - start) + "s");
    }
}