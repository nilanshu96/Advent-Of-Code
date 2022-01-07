import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Part1 {
    public static void main(String[] args) {
        List<String> chunks = Util.readAllChunks("input.txt");
        Map<Character, Integer> corruptScore = new HashMap<>();

        corruptScore.put(')', 3);
        corruptScore.put(']', 57);
        corruptScore.put('}', 1197);
        corruptScore.put('>', 25137);

        int sum = 0;

        for(int i=0; i<chunks.size(); i++) {
            Character corruptChar = getCorruptCharacter(chunks.get(i));
            if(corruptChar != null) {
                sum += corruptScore.get(corruptChar);
            } 
        }

        System.out.println("Answer: " + sum);
    }

    private static Character getCorruptCharacter(String chunk) {
        ArrayDeque<Character> adq = new ArrayDeque<>();

        Map<Character,Character> bracketMap = new HashMap<>();
        bracketMap.put(')', '(');
        bracketMap.put('}', '{');
        bracketMap.put(']', '[');
        bracketMap.put('>', '<');
        
        char[] chunkArray = chunk.toCharArray();
        for(int i=0; i<chunkArray.length; i++) {
            Character c = chunkArray[i];
            boolean closing = bracketMap.containsKey(c);

            if(closing && (!bracketMap.get(c).equals(adq.peek()))) {
                return c;
            } else if(!closing) {
                adq.push(c);
            } else {
                adq.pop();
            }
        }

        return null;
    }
}