import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class Util {
    static List<String> readAllLines(String filename) {
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get(filename));
            lines.removeAll(Arrays.asList("", null));
        } catch(IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    static <T> void printArray (T[] arr) {
        for(int i=0; i<arr.length; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }

    static Map<String, String> getPolymerTemplate(List<String> input) {
        Map<String, String> polymerTemplate = new HashMap<>();

        for(int i=1; i<input.size(); i++) {
            String[] parts = input.get(i).split("->");
            polymerTemplate.put(parts[0].trim(), parts[1].trim());
        }

        return polymerTemplate;
    }

    static Pair<String,String> getPolymersFromAPair(String pair, String element) {
        String first, second;
        first = pair.charAt(0) + element;
        second = element + pair.charAt(1);

        return new Pair<>(first, second);
    }

    static Map<String,Long> getPairCountMap(Map<String,String> polymerTemplate) {
        Map<String,Long> pairCountMap = new HashMap<>();
        polymerTemplate.forEach((key, value) -> {
            pairCountMap.put(key, 0l);
        });
        return pairCountMap;
    }

    static Map<String,Long> getElementCountMap(Map<String,String> polymerTemplate) {
        Map<String,Long> pairCountMap = new HashMap<>();
        polymerTemplate.forEach((key, value) -> {
            pairCountMap.putIfAbsent(value, 0l);
        });
        return pairCountMap;
    }

    static <T extends Comparable<T>> Pair<T,T> getMinAndMax(Collection<T> collection) {
        Iterator<T> it = collection.iterator();
        T min, max;

        if(it.hasNext()){
            min = max = it.next();
        } else {
            return null;
        }

        while(it.hasNext()) {
            T num = it.next();
            if(num.compareTo(min) < 0) {
                min = num;
            } else if(num.compareTo(max) > 0) {
                max = num;
            }
        }

        return new Pair<>(min, max);
    }
}
