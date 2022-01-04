import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Util {
    static List<Instruction> readInstructions(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            List<Instruction> instructions = new ArrayList<>();

            Pattern instructionPattern = Pattern.compile("(on|off) (x=-?\\d+..-?\\d+),(y=-?\\d+..-?\\d+),(z=-?\\d+..-?\\d+)");
            Pattern rangePattern = Pattern.compile("[xyz]=(-?\\d+)..(-?\\d+)");
            for(String line: lines) {
                Matcher mat = instructionPattern.matcher(line);
                mat.matches();
                String toggleStr = mat.group(1);
                boolean toggle = toggleStr.equals("on") ? true: false;

                List<Pair<Integer,Integer>> ranges = new ArrayList<>();
                for(int i=2; i<=4; i++) {
                    Matcher rangeMat = rangePattern.matcher(mat.group(i));
                    rangeMat.matches();
                    ranges.add(new Pair<>(Integer.decode(rangeMat.group(1)), Integer.decode(rangeMat.group(2))));
                }

                Instruction instruction = new Instruction(toggle, ranges.get(0), ranges.get(1), ranges.get(2));
                instructions.add(instruction);
            }

            return instructions;
        } catch(IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}