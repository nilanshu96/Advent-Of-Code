import java.io.*;

public class Util {

    public static String readHexFromFile(String filename) {
        String hex = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            hex = reader.readLine();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return hex;
    }

    public static String getBinaryFromHex(String hex) {
        StringBuilder sb = new StringBuilder();

        char[] hexArr = hex.toCharArray();

        for(int i=0; i<hexArr.length; i++) {
            sb.append(getBinaryFromHexChar(hexArr[i]));
        }

        return sb.toString();
    }

    private static String getBinaryFromHexChar(char hex) {

        String bin = null;
        switch(hex) {
            case '0': bin = "0000"; break;
            case '1': bin = "0001"; break;
            case '2': bin = "0010"; break;
            case '3': bin = "0011"; break;
            case '4': bin = "0100"; break;
            case '5': bin = "0101"; break;
            case '6': bin = "0110"; break;
            case '7': bin = "0111"; break;
            case '8': bin = "1000"; break;
            case '9': bin = "1001"; break;
            case 'A': bin = "1010"; break;
            case 'B': bin = "1011"; break;
            case 'C': bin = "1100"; break;
            case 'D': bin = "1101"; break;
            case 'E': bin = "1110"; break;
            case 'F': bin = "1111"; break;
        }
        return bin;
    }

    public static int binaryToDecimal(String bin) {
        return Integer.parseInt(bin,2);
    }

    public static long binaryToLongDecimal(String bin) {
        return Long.parseLong(bin,2);
    }
}