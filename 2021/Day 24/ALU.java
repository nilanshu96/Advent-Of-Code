import java.util.*;
import java.util.regex.*;

public class ALU {
    Map<String,Long> variables;
    List<String> instructions;

    ALU(List<String> instructions) {
        this.instructions = new ArrayList<>(instructions);
        variables = new HashMap<>();
        variables.put("x",0l);
        variables.put("y",0l);
        variables.put("z",0l);
        variables.put("w",0l);
    }

    void executeInstructions(String input) {
        int idx = 0;
        Pattern pat = Pattern.compile("(\\w+) ([xyzw]) (-?\\w+)");
        Pattern inpPat = Pattern.compile("inp ([xyzw])");
        for(String instruction: instructions) {
            Matcher inpMat = inpPat.matcher(instruction);
            if(inpMat.matches()) {
                variables.put(inpMat.group(1), Long.parseLong(input.substring(idx,idx+1)));
                idx++;
            } else {
                Matcher mat = pat.matcher(instruction);
                mat.matches();
                String varName = mat.group(2);
                long value = 0;
                try {
                    value = Long.parseLong(mat.group(3));
                } catch(NumberFormatException e) {
                    value = variables.get(mat.group(3));
                }
                switch(mat.group(1)) {
                    case "add": variables.put(varName, variables.get(varName) + value); break;
                    case "mul": variables.put(varName, variables.get(varName) * value); break;
                    case "div": 
                        if(value != 0) {
                            variables.put(varName, variables.get(varName)/value);
                        } else {
                            throw new ArithmeticException("Attempted divide by 0");
                        }
                        break;
                    case "mod": if(variables.get(varName) >= 0 && value > 0) {
                            variables.put(varName, variables.get(varName)%value);
                        } else {
                            System.out.println(varName + "," + variables.get(varName) + "," + value);
                            throw new ArithmeticException("Attempted invalid mod operation");
                        }
                        break;
                    case "eql": variables.put(varName, variables.get(varName).equals(value) ? 1l: 0l); break;
                }
            }
        }

        variables.entrySet().stream().forEach(System.out::println);
    }
}