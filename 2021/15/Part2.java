import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) {
        int[][] inputMap = Util.readMap("input.txt");
        int[][] riskLevelMap = Util.get5xMap(inputMap);
        System.out.println("Answer: " + calculateLowRiskRoute(riskLevelMap));
    }

    // Based on Dijkstra Algorithm
    private static int calculateLowRiskRoute(int[][] riskLevelMap) {

        int rowSize = riskLevelMap.length;
        int colSize = riskLevelMap[0].length;

        boolean[][] visited = new boolean[rowSize][colSize];
        int[][] totalRiskMap = new int[rowSize][colSize]; // each cell contains summation of risk from source to vertex (i,j)
        PriorityQueue<WeightedVertex> pq = new PriorityQueue<>(); // the weight of vertex is same as the risk calculated from source to that vertex

        Util.fillIntMatrix(totalRiskMap, Integer.MAX_VALUE);

        totalRiskMap[0][0] = 0;
        WeightedVertex source = new WeightedVertex(0,0,0);

        pq.add(source);

        while(!pq.isEmpty()) {
            WeightedVertex vert = pq.poll();
            visited[vert.getRow()][vert.getCol()] = true;
            calculateNeighbourRisk(vert, riskLevelMap, totalRiskMap);
            addUnvisitedNeighboursToQueue(pq, vert, visited, totalRiskMap);
        }

        return totalRiskMap[rowSize-1][colSize-1];
    }

    private static void calculateNeighbourRisk(WeightedVertex vert, int[][] riskLevelMap, int[][] totalRiskMap) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int rowSize = riskLevelMap.length;
        int colSize = riskLevelMap[0].length;

        for(int i=0; i<dx.length; i++) {
            int row = vert.getRow() + dx[i];
            int col = vert.getCol() + dy[i];

            if((row < 0) || (row >= rowSize)) continue;
            if((col < 0) || (col >= colSize)) continue;

            int risk = totalRiskMap[vert.getRow()][vert.getCol()] + riskLevelMap[row][col];

            if(risk < totalRiskMap[row][col]) {
                totalRiskMap[row][col] = risk;
            }
        }
    }

    private static void addUnvisitedNeighboursToQueue(PriorityQueue<WeightedVertex> pq, WeightedVertex vert, boolean[][] visited, int[][] totalRiskMap) {
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int rowSize = visited.length;
        int colSize = visited[0].length;

        for(int i=0; i<dx.length; i++) {
            int row = vert.getRow() + dx[i];
            int col = vert.getCol() + dy[i];

            if((row < 0) || (row >= rowSize)) continue;
            if((col < 0) || (col >= colSize)) continue;

            if(!visited[row][col]) {
                WeightedVertex newVert = new WeightedVertex(row,col, totalRiskMap[row][col]);
                if(!pq.contains(newVert)) {
                    pq.add(newVert);
                }
            }
        }
    }
}