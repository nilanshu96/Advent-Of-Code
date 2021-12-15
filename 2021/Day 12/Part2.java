import java.util.*;

public class Part2 {
    public static void main(String[] args) {
        Map<String, List<String>> caveGraph = Util.readGraph("input.txt");
        System.out.println("Answer: " + countAllPaths(caveGraph));
    } 

    private static int countAllPaths(Map<String, List<String>> graph) {
        List<String> currentPath = new ArrayList<>();
        return countPaths("start", graph, currentPath, false);
    }

    private static int countPaths(String currentPoint, Map<String, List<String>> graph, List<String> currentPath, boolean isSmallVisitedTwice) {
        
        if(currentPath.contains(currentPoint) && isStartPoint(currentPoint)) {
            return 0; 
        }

        if(currentPath.contains(currentPoint) && isSmallCave(currentPoint)) {
            if(isSmallVisitedTwice) {
                return 0;
            } else {
                isSmallVisitedTwice = true;
            }
        }

        if(isEndPoint(currentPoint)) {
            // System.out.println(currentPath);
            return 1;
        }

        int totalPaths = 0;
        currentPath.add(currentPoint);

        List<String> connectedVertices = graph.get(currentPoint);
        for(int i=0; i<connectedVertices.size(); i++) {
            String connectedVertex = connectedVertices.get(i);
            totalPaths += countPaths(connectedVertex, graph, currentPath, isSmallVisitedTwice);
        }

        currentPath.remove(currentPoint);

        return totalPaths;
    }

    private static boolean isEndPoint(String point) {
        return "end".equals(point);
    }

    private static boolean isStartPoint(String point) {
        return "start".equals(point);
    }

    private static boolean isSmallCave(String cave) {
        return Util.isNotAllUppercase(cave);
    }
}