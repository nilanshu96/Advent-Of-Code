import java.util.*;

public class Part1 {
    public static void main(String[] args) {
        List<String> instructions = Util.readALUInstructions("input.txt");
        ALU alu = new ALU(instructions);

        List<IntTriplet> keyValues = new ArrayList<>();

        //Every 18 instruction gets repeated in a similar pattern
        //Reducing those patterns results in the 6th and 16th instruction deciding the outcome
        //of a set of 18 instruction. Hence, only the values at 6th and 16th instruction are getting stored below
        for(int idx=0, i=5, j=15; i<instructions.size() && j<instructions.size(); idx++, i+=18, j+=18) {
            int x = Integer.parseInt(instructions.get(i).split(" ")[2]);
            int y = Integer.parseInt(instructions.get(j).split(" ")[2]);

            keyValues.add(new IntTriplet(idx,x,y));
        }

        //The result of a set of 18 instruction is either (z = 26z + input + y) whenever x>=10 or (z = z/26)
        //Because z is either getting multiplied or divided by 26, we consider only the (input + y) portion for calculations
        //whenever x is < 10, the current iteration of input should satisy the condition: (input == z_previous%26 + x) to reach the ALU
        //instruction (z=z/26) or else z will again be calculated as (z = 26z + input + y).
        //For x >= 10, there are no situations where (input == z_previous%26 + x) will ever be true as input ranges from 1 to 9
        //Assuming the given input instructions will always have single digit inputs to satisfy the condition (input == z_previous%26 + x),
        //the answer is calculated using stack. Why stack? because suppose for 2 iterations z1 = c1, z2 = 26z1 + c2, then for third iteration
        //z3 = z2/26 which after truncating is same as z1 as constants c1,c2.. are always less than 26 for the given input instructions
        //Below calculations try to maximize the value of the more significant digits
        int[] MONAD = new int[14];
        ArrayDeque<IntTriplet> stack = new ArrayDeque<>();
        for(IntTriplet keyValue: keyValues) {
            if(keyValue.getX() >= 10) {
                stack.push(keyValue);
            } else {
                int currentIdx = keyValue.getIdx();
                IntTriplet prev = stack.pop();

                if((prev.getY() + keyValue.getX()) >= 0) {
                    MONAD[currentIdx] = 9;
                    MONAD[prev.getIdx()] = MONAD[currentIdx] - (prev.getY() + keyValue.getX());
                } else {
                    MONAD[prev.getIdx()] = 9;
                    MONAD[currentIdx] = MONAD[prev.getIdx()] + (prev.getY() + keyValue.getX());
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        Arrays.stream(MONAD).forEach(answer::append);
        System.out.println("Answer: " + answer);

        System.out.println("ALU execution for " + answer + ": ");
        alu.executeInstructions(answer.toString());
    }
}