import java.util.List;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) {

        List<String> signalList = Util.readAllSignals("input.txt");
        
        int sum = 0;

        for(int i=0; i<signalList.size(); i++) {
            String inputValues = Util.getInputValues(signalList.get(i));
            String outputValues = Util.getOutputValues(signalList.get(i));

            SevenSegDisplay disp = new SevenSegDisplay(inputValues);
            int num = disp.getNumberFromSignals(outputValues);
            sum += num;
        }

        System.out.println("Answer: " + sum);
    }
}