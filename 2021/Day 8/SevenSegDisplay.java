import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SevenSegDisplay {
    private Map<String, Integer> signalToDigit;
    private Map<Integer, String> digitToSignal;

    SevenSegDisplay(String signalPatterns) {
        signalToDigit = new HashMap<>();
        digitToSignal = new HashMap<>();
        calculateSignal(signalPatterns);
    }

    private void calculateSignal(String signalPatterns) {
        List<String> signalList = Arrays.asList(signalPatterns.split("\\s+"));
        signalList = signalList.stream().map(s -> Util.sortString(s)).collect(Collectors.toList());

        for(int i=0; i<signalList.size(); i++) {
            String signal = signalList.get(i);

            switch(signal.length()) {
                case 2: signalToDigit.put(signal, 1); digitToSignal.put(1, signal); break;
                case 4: signalToDigit.put(signal, 4); digitToSignal.put(4, signal); break;
                case 3: signalToDigit.put(signal, 7); digitToSignal.put(7, signal); break;
                case 7: signalToDigit.put(signal, 8); digitToSignal.put(8, signal); break;
            }
        }

        for(int i=0; i<signalList.size(); i++) {
            String signal = signalList.get(i);

            if(signal.length() == 6) {
                if(!Util.stringContainsAllCharOf(signal, digitToSignal.get(1))) {
                    signalToDigit.put(signal, 6);
                    digitToSignal.put(6, signal);
                } else if(Util.stringContainsAllCharOf(signal, digitToSignal.get(4))) {
                    signalToDigit.put(signal, 9);
                    digitToSignal.put(9, signal);
                } else {
                    signalToDigit.put(signal, 0);
                    digitToSignal.put(0, signal);
                }
            }
        }

        for(int i=0; i<signalList.size(); i++) {
            String signal = signalList.get(i);

            if(signal.length() == 5) {
                if(Util.stringContainsAllCharOf(signal, digitToSignal.get(1))) {
                    signalToDigit.put(signal, 3);
                    digitToSignal.put(3, signal);
                } else if(Util.stringContainsAllCharOf(digitToSignal.get(6), signal)) {
                    signalToDigit.put(signal, 5);
                    digitToSignal.put(5, signal);
                } else {
                    signalToDigit.put(signal, 2);
                    digitToSignal.put(2, signal);
                }
            }
        }
    }

    int getNumberFromSignals(String signals) {
        List<String> signalList = Arrays.asList(signals.split("\\s+"));
        signalList = signalList.stream().map(s -> Util.sortString(s)).collect(Collectors.toList());
        
        StringBuilder sb = new StringBuilder();
        signalList.stream().forEach(s -> sb.append(signalToDigit.get(s)));
        return Integer.parseInt(sb.toString());
    }
}