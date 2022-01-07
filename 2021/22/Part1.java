import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Part1 {
    public static void main(String[] args) {
        List<Instruction> instructions = Util.readInstructions("input.txt");

        Set<Coordinate> onCubes = new HashSet<>();

        instructions.stream().filter(Part1::isInstructionInValidRange).forEach(instruction -> {
            executeInstruction(instruction, onCubes);
        });

        System.out.println("Answer: " + onCubes.size());
    }

    private static List<Coordinate> executeInstruction(Instruction instruction, Set<Coordinate> onCubes) {

        List<Coordinate> coordinates = new ArrayList<>();
        for(int x=instruction.xRange.first; x<=instruction.xRange.second; x++) {
            for(int y=instruction.yRange.first; y<=instruction.yRange.second; y++) {
                for(int z=instruction.zRange.first; z<=instruction.zRange.second; z++) {
                    // System.out.println(x +"," + y + "," + z);
                    if(instruction.toggle) {
                        onCubes.add(new Coordinate(x,y,z));
                    } else {
                        onCubes.remove(new Coordinate(x,y,z));
                    }
                }
            }
        }

        return coordinates;
    }

    private static boolean isInstructionInValidRange(Instruction instruction) {
        if(instruction.xRange.first < -50 || instruction.xRange.second > 50) return false;
        if(instruction.yRange.first < -50 || instruction.yRange.second > 50) return false;
        if(instruction.zRange.first < -50 || instruction.zRange.second > 50) return false;
        return true;
    }
}