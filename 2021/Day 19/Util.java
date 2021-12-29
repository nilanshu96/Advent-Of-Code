import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.regex.Matcher;

public class Util {

    public static final int MIN_BEACONS_COMPARE = 12;
    public static final List<String> orderPermutations;

    static {
        orderPermutations = getAllCoordinatePermutations();
    }

    public static List<Scanner> readAllScanners(String filename) {
        List<Scanner> scanners = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {

            Pattern pat = Pattern.compile("(-?\\d+),(-?\\d+),(-?\\d+)");
            List<Coordinate> scannerList = null;
            String line = null;

            while((line = reader.readLine()) != null) {
                
                Matcher mat = pat.matcher(line);

                if(line.matches("-{3} scanner \\d+ -{3}")) {
                    scannerList = new ArrayList<>();
                } else if(mat.matches()) {
                    scannerList.add(new Coordinate(Integer.parseInt(mat.group(1)), Integer.parseInt(mat.group(2)), Integer.parseInt(mat.group(3))));
                } else {
                    scanners.add(new Scanner(scannerList));
                } 
            }
            
            if(scannerList != null || !scannerList.isEmpty()) {
                scanners.add(new Scanner(scannerList));
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

        return scanners;
    }

    public static void printScanners(List<Scanner> scanners) {
        if(scanners == null) return;

        for(int i=0; i<scanners.size(); i++) {
            System.out.printf("--- scanner %d ---\n",i);
            System.out.println(scanners.get(i));
        }
    }

    private static List<String> getAllCoordinatePermutations() {
        String x[] = {"x","-x"};
        String y[] = {"y","-y"};
        String z[] = {"z","-z"};

        List<String> orders = new ArrayList<>();

        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                for(int k=0; k<2; k++) {
                    String order = x[i] + "," + y[j] + "," + z[k];
                    orders.add(order);
                }
            }

            for(int j=0; j<2; j++) {
                for(int k=0; k<2; k++) {
                    String order = x[i] + "," + z[j] + "," + y[k];
                    orders.add(order);
                }
            }
        }

        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                for(int k=0; k<2; k++) {
                    String order = y[i] + "," + x[j] + "," + z[k];
                    orders.add(order);
                }
            }

            for(int j=0; j<2; j++) {
                for(int k=0; k<2; k++) {
                    String order = y[i] + "," + z[j] + "," + x[k];
                    orders.add(order);
                }
            }
        }

        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                for(int k=0; k<2; k++) {
                    String order = z[i] + "," + x[j] + "," + y[k];
                    orders.add(order);
                }
            }

            for(int j=0; j<2; j++) {
                for(int k=0; k<2; k++) {
                    String order = z[i] + "," + y[j] + "," + x[k];
                    orders.add(order);
                }
            }
        }
        
        //to remove configurations that are not rotations but mirror. Example: x,y,-z is a mirror of x,y,z and not a rotation. Hence it's invalid.
        return orders.stream().filter(s -> isDeterminant1(s)).collect(Collectors.toList()); 
    }

    private static boolean isDeterminant1(String order) {
        int[][] mat = new int[3][];

        String parts[] = order.split(",");

        for(int i=0; i<3; i++) {
            if(parts[i].matches("-?x")) {
                if(parts[i].charAt(0) == '-') {
                    mat[i] = new int[]{-1,0,0};
                } else {
                    mat[i] = new int[]{1,0,0};
                }
            } else if(parts[i].matches("-?y")) {
                if(parts[i].charAt(0) == '-') {
                    mat[i] = new int[]{0,-1,0};
                } else {
                    mat[i] = new int[]{0,1,0};
                }
            } else {
                if(parts[i].charAt(0) == '-') {
                    mat[i] = new int[]{0,0,-1};
                } else {
                    mat[i] = new int[]{0,0,1};
                }
            }
        }

        /* Example: mat for (x,z,y) is
        *  [1,0,0]
        *  [0,0,1]
        *  [0,1,0]
        */

        int determinant = mat[0][0] * (mat[1][1] * mat[2][2] - mat[1][2] * mat[2][1]);
        determinant -= mat[0][1] * (mat[1][0]*mat[2][2] - mat[1][2] * mat[2][0]);
        determinant += mat[0][2] * (mat[1][0]*mat[2][1] - mat[1][1] * mat[2][0]);

        // determinant of rotational matrices are 1 (as cos^2(x)+sin^(x) = 1) (check rotational matrix online). For reflections determinant is -1.
        return determinant == 1;
    }

    public static int getAbsInt(int x) {
        return x >= 0 ? x : -x;
    }
}