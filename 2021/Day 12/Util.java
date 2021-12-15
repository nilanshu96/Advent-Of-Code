import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Util {
    static Map<String, List<String>> readGraph(String filename) {
        Map<String, List<String>> graph = new HashMap<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            lines.stream().forEach(line -> {
                String[] parts = line.split("-");
                String point1 = parts[0];
                String point2 = parts[1];

                addEdge(graph, point1, point2);
                addEdge(graph, point2, point1);
            });
        } catch(IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    private static void addEdge(final Map<String, List<String>> graph, final String from, final String to) {

        if(graph == null) return;

        if(graph.containsKey(from)) {
            graph.get(from).add(to);
        } else {
            List<String> connections = new ArrayList<>();
            connections.add(to);
            graph.put(from, connections);
        }
    }

    static boolean isNotAllUppercase(String s) {
        return s.codePoints().anyMatch(Character::isLowerCase);
    }
}