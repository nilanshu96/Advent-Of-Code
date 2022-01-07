import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) {
        try {
            List<String> numStrings = Files.readAllLines(Paths.get("input.txt"));
            int bitLength = numStrings.get(0).length();
            List<Integer> numbers = numStrings.stream().mapToInt(s -> Integer.parseInt(s,2)).boxed().collect(Collectors.toList());
            int oxygenRating, co2rating;

            //Oxygen Rating calculation
            int currentBit = bitLength - 1;
            List<Integer> temp = new ArrayList<>(numbers);
            while(temp.size() > 1 && currentBit >= 0) {
                int setBit = 1 << currentBit;
                List<Integer> numsAboveSetBit = temp.stream().filter(x ->(x & setBit)==setBit).collect(Collectors.toList());
                double countOf1s = numsAboveSetBit.size();

                if(countOf1s >= (double)temp.size()/2) {
                    temp = numsAboveSetBit;
                } else {
                    temp.removeAll(numsAboveSetBit);
                }
                currentBit--;
            }
            oxygenRating = temp.get(0);

            //CO2 rating calculation
            currentBit = bitLength - 1;
            temp = new ArrayList<>(numbers);
            while(temp.size() > 1 && currentBit >= 0) {
                int setBit = 1 << currentBit;
                List<Integer> numsBelowSetBit = temp.stream().filter(x -> (x & setBit)==0).collect(Collectors.toList());
                double countOf0s = numsBelowSetBit.size();

                if(countOf0s <= (double)temp.size()/2) {
                    temp = numsBelowSetBit;
                } else {
                    temp.removeAll(numsBelowSetBit);
                }
                currentBit--;
            }
            co2rating = temp.get(0);

            System.out.println("oxygen generator rating: " + oxygenRating);
            System.out.println("CO2 scrubber rating: " + co2rating);
            System.out.println("Answer: " + oxygenRating * co2rating);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}