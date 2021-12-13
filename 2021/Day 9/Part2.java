import java.util.PriorityQueue;

public class Part2 {
    public static void main(String[] args) {
        int[][] heightMap = Util.readFloorHeightMap("input.txt");
        Integer[] largestBasins = get3LargestBasins(heightMap);

        Util.printArray(largestBasins);

        long answer = 1;
        for(int i=0; i<largestBasins.length; i++) {
            answer *= largestBasins[i].longValue();
        }

        System.out.println("Answer: " + answer);
    }

    private static Integer[] get3LargestBasins(int[][] heightMap) {
        if(heightMap == null || heightMap[0] == null) return null;
        boolean[][] visitedMap = new boolean[heightMap.length][heightMap[0].length];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i=0; i<heightMap.length; i++) {
            for(int j=0; j<heightMap[i].length; j++) {
                if(heightMap[i][j] != 9 && !visitedMap[i][j]) {
                    int basinSize = travelAndGetBasinSize(heightMap, visitedMap, i, j);
                    if(pq.size() < 3) {
                        pq.offer(basinSize);
                    } else {
                        int currLeastBasinSize = pq.peek();
                        if(basinSize > currLeastBasinSize) {
                            pq.poll();
                            pq.offer(basinSize);
                        }
                    }
                }
            }
        }

        return pq.toArray(new Integer[0]);
    } 

    private static int travelAndGetBasinSize(int[][] heightMap, boolean[][] visitedMap, int rowPos, int colPos) {
        
        if((rowPos < 0) || (rowPos >= heightMap.length) ) return 0;

        if((colPos < 0) || (colPos >= heightMap[rowPos].length)) return 0;

        if(visitedMap[rowPos][colPos]) return 0;
        
        if(heightMap[rowPos][colPos] == 9) {
            visitedMap[rowPos][colPos] = true;
            return 0;
        }

        visitedMap[rowPos][colPos] = true;
        return 1 + travelAndGetBasinSize(heightMap, visitedMap, rowPos + 1, colPos) + travelAndGetBasinSize(heightMap, visitedMap, rowPos - 1, colPos)
            + travelAndGetBasinSize(heightMap, visitedMap, rowPos, colPos + 1) + travelAndGetBasinSize(heightMap, visitedMap, rowPos, colPos - 1);
    }
}