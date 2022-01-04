import java.util.List;
import java.util.ArrayList;

public class Part2 {
    public static void main(String[] args) {
        List<Instruction> instructions = Util.readInstructions("input.txt");

        List<Pair<Integer,Cuboid>> reactorCore = new ArrayList<>();

        // Below solution calculates the answer using the formula for union of sets (see: https://en.wikipedia.org/wiki/Inclusion%E2%80%93exclusion_principle#Statement)
        for(Instruction instruction: instructions) {
            List<Pair<Integer,Cuboid>> cuboidsToAddInCore = new ArrayList<>();
            Cuboid cuboidToProcess = new Cuboid(instruction.xRange, instruction.yRange, instruction.zRange);

            if(instruction.toggle) {
                cuboidsToAddInCore.add(new Pair<>(1, cuboidToProcess));
            }

            for(Pair<Integer,Cuboid> reactorCuboid: reactorCore) {
                Cuboid intersection = getIntersection(cuboidToProcess, reactorCuboid.second);
                if(intersection != null) {
                    cuboidsToAddInCore.add(new Pair<>(-reactorCuboid.first, intersection));
                    if(cuboidsToAddInCore.size() > 91380) {
                        System.out.println(intersection);
                    }
                }
            }

            reactorCore.addAll(cuboidsToAddInCore);
        }

        long answer = reactorCore.stream().mapToLong(x -> x.first * x.second.getVolume()).sum();
        System.out.println("Answer: " + answer);
    }

    private static Cuboid getIntersection(Cuboid A, Cuboid B) {
        Cuboid intersection = new Cuboid(Math.max(A.x1,B.x1), Math.min(A.x2,B.x2), Math.max(A.y1,B.y1), Math.min(A.y2,B.y2), Math.max(A.z1,B.z1), Math.min(A.z2,B.z2));
        if((intersection.x1 > intersection.x2) || (intersection.y1 > intersection.y2) || (intersection.z1 > intersection.z2)) {
            return null;
        } else {
            return intersection;
        }
    }

}