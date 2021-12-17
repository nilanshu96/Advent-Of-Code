import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Part2 {
    public static void main(String[] args) {
        List<String> inputLines = Util.readAllLines("input.txt");
        int steps = 40;

        if(inputLines.isEmpty()) {
            System.out.println("No input provided");
            return;
        }

        String initalPolymer = inputLines.get(0);
        Map<String,String> polymerTemplate = Util.getPolymerTemplate(inputLines);
        
        Map<String,Long> elementCountMap = Util.getElementCountMap(polymerTemplate);
        Map<String, Long> pairCountMap = Util.getPairCountMap(polymerTemplate);
        
        processInitialPolymer(initalPolymer, pairCountMap, elementCountMap);
        
        while(steps-- > 0) {
            growPolymer(polymerTemplate, elementCountMap, pairCountMap);
        }

        Pair<Long,Long> minMaxPair = Util.getMinAndMax(elementCountMap.values());

        System.out.println("Answer: " + (minMaxPair.getSecond() - minMaxPair.getFirst())); 
    }

    private static void processInitialPolymer(String initalPolymer, Map<String,Long> pairCountMap, Map<String,Long> elementCountMap) {
        for(int i=0; i<initalPolymer.length() - 1; i++) {
            String pair = initalPolymer.substring(i, i+2);
            pairCountMap.put(pair, pairCountMap.get(pair) + 1);
        }

        for(int i=0; i<initalPolymer.length(); i++) {
            String element = initalPolymer.substring(i,i+1);
            elementCountMap.put(element, elementCountMap.get(element) + 1);
        }
    }

    private static void growPolymer(Map<String,String> polymerTemplate, Map<String,Long> elementCountMap, Map<String, Long> pairCountMap) {
        Map<String,Long> oldPairCountMap = new HashMap<>(pairCountMap);
        oldPairCountMap.forEach((key, value) -> {
            String insertElement = polymerTemplate.get(key);
            elementCountMap.put(insertElement, elementCountMap.get(insertElement) + value);

            Pair<String,String> children = Util.getPolymersFromAPair(key, insertElement);
            String child1 = children.getFirst();
            String child2 = children.getSecond();

            pairCountMap.put(key, pairCountMap.get(key) - value);
            pairCountMap.put(child1, pairCountMap.get(child1) + value);
            pairCountMap.put(child2, pairCountMap.get(child2) + value);
        });
    }
    
}