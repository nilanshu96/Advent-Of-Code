import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Paths;

//Solution assumes all binary nums have same number of bit length
public class Part1 {
    public static void main(String[] args) {
        try {
            List<String> binNums = Files.readAllLines(Paths.get("input.txt"));
            int bitLength = binNums.get(0).length(); 
            int gamma = 0;
            int epilson = 0;

            int[] countOf1s = new int[bitLength];

            for(int i=0; i<bitLength; i++) {
                for(int j=0; j<binNums.size(); j++) {
                    if(binNums.get(j).charAt(i) == '1') countOf1s[i]++;
                }
            }

            for(int i=0; i<bitLength; i++) {
                if(countOf1s[i] > (binNums.size()/2)) epilson = epilson | (1<<(bitLength-i-1));
            }

            gamma = ~epilson & ((1<<bitLength) - 1);
            System.out.println("epilson: " + epilson);
            System.out.println("Gamma: " + gamma);
            System.out.println("Answer: " + epilson*gamma);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}