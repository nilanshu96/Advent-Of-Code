import java.util.*;

public class Part2 {
    public static void main(String[] args) {
        Pair<CoordinateRange, CoordinateRange> targetArea = Util.readTargetArea("input.txt");

        int xLimit = targetArea.first.getEnd();
        int yLimit = targetArea.second.getStart();

        List<Pair<Integer,Integer>> validxy = new ArrayList<>();

        int count = 0;
        for(int i=0; i<=xLimit; i++) {
            for(int j=-yLimit; j>=yLimit; j--) {
                if(checkVelocityHitsTarget(i,j,targetArea)) {
                    count++;
                    validxy.add(new Pair<>(Integer.valueOf(i), Integer.valueOf(j)));
                }
            }
        }

        System.out.println("Answer: " + count);
    }

    private static boolean checkVelocityHitsTarget(int x, int y, Pair<CoordinateRange, CoordinateRange> targetArea) {
        int x1 = targetArea.first.getStart();
        int x2 = targetArea.first.getEnd();

        int y1 = targetArea.second.getStart();
        int y2 = targetArea.second.getEnd();

        int vx = x;
        int vy = y;

        while(x<=x2 && y>=y1) {
            if(Util.isPresentInRange(x1,x2,x) && Util.isPresentInRange(y1,y2,y)) {
                return true;
            }

            vx = vx != 0? vx-1: 0;
            vy -= 1;
            
            x += vx;
            y += vy;
        }

        return false;
    }
}