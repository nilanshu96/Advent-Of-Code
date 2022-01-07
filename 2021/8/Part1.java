import java.util.List;

public class Part1 {
    public static void main(String[] args) {
        List<String> signalList = Util.readAllSignals("input.txt");
        
        int count = 0;
        for(int i = 0; i<signalList.size(); i++) {
            count += count1478(Util.getOutputValuesArray(signalList.get(i)));
        }

        System.out.println("Answer: " + count);
    }

    private static int count1478(String[] outputVals) {
        int count = 0;

        for(int i=0; i<outputVals.length; i++) {
            switch(outputVals[i].length()) {
                case 2: count++; break; // for 1
                case 4: count++; break; // for 4
                case 3: count++; break; // for 7
                case 7: count++; break; // for 8
            }
        }

        return count;
    }
}