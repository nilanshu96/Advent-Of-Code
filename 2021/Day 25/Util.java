import java.util.*;
import java.nio.file.*;
import java.io.*;

public class Util {
    static char[][] readCucumberHerd(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            char[][] herd = new char[lines.size()][];
            for(int i=0; i<lines.size(); i++) {
                herd[i] = lines.get(i).toCharArray();
            }
            return herd;
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static void printCucumberHerd(char[][] herd) {
        for(int i=0; i<herd.length; i++) {
            for(int j=0; j<herd[i].length; j++) {
                System.out.print(herd[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}