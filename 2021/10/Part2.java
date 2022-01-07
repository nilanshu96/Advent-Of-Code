import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class Part2 {
    public static void main(String[] args) {
        List<String> chunks = Util.readAllChunks("input.txt");
        List<Long> fixScores = new ArrayList<>();

        for(int i=0; i<chunks.size(); i++) {
            if(isNotCorrupt(chunks.get(i))) {
                String fix = getIncompleteChunkFix(chunks.get(i));
                fixScores.add(getFixScore(fix));
            }
        }

        Collections.sort(fixScores);

        System.out.println("Answer: " + fixScores.get(fixScores.size()/2));

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

    private static boolean isNotCorrupt(String chunk) {
        return getCorruptCharacter(chunk) == null;
    }

    private static String getIncompleteChunkFix(String chunk) {
        List<Character> closingChars = Arrays.asList(')','}',']','>');
        Map<Character,Character> bracketMap = new HashMap<>();
        bracketMap.put('(',')');
        bracketMap.put('{','}');
        bracketMap.put('[',']');
        bracketMap.put('<','>');

        char[] chunkArray = chunk.toCharArray();

        ArrayDeque<Character> adq = new ArrayDeque<>();
        for(int i=0; i<chunkArray.length; i++) {
            Character c = chunkArray[i];
            if(closingChars.contains(c)) {
                adq.pop();
            } else {
                adq.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!adq.isEmpty()) {
            sb.append(bracketMap.get(adq.pop()));
        }

        return sb.toString();
    }

    private static long getFixScore(String fix) {
        Map<Character,Integer> bracketMap = new HashMap<>();
        bracketMap.put(')', 1);
        bracketMap.put(']', 2);
        bracketMap.put('}', 3);
        bracketMap.put('>', 4);

        long sum = 0;
        char[] fixArray = fix.toCharArray();

        for(int i=0; i<fixArray.length; i++) {
            sum *= 5;
            sum += bracketMap.get(fixArray[i]);
        }

        return sum;
    }
}