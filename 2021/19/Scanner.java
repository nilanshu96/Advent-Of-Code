import java.util.List;
import java.util.ArrayList;

public class Scanner {

    private List<Coordinate> scanner;
    private Coordinate relativePosition;

    Scanner(List<Coordinate> scanner) {
        this.scanner = scanner;
        relativePosition = new Coordinate(0,0,0);
    }

    Scanner(Scanner obj, String order) {
        scanner = new ArrayList<>();
        for(Coordinate c: obj.scanner) {
            scanner.add(new Coordinate(c,order));
        }
        relativePosition = new Coordinate(0,0,0);
    }

    Scanner(Scanner obj, Coordinate offset) {
        scanner = new ArrayList<>();
        for(Coordinate c: obj.scanner) {
            scanner.add(new Coordinate(c, offset));
        }
        relativePosition = new Coordinate(0,0,0);
    }

    public List<Coordinate> getCoordinateList() {
        return new ArrayList<>(scanner);
    }

    public int getManhattanDistance(Scanner obj) {
        int distX = Util.getAbsInt(this.relativePosition.x - obj.relativePosition.x);
        int distY = Util.getAbsInt(this.relativePosition.y - obj.relativePosition.y);
        int distZ = Util.getAbsInt(this.relativePosition.z - obj.relativePosition.z);

        return distX + distY + distZ;
    }

    public Scanner matchBeacons(Scanner obj) {
        List<String> orders = Util.orderPermutations;

        for(int i=0; i<orders.size(); i++) {
            Scanner rotatedObj = new Scanner(obj, orders.get(i));
            for(int j=0; j<scanner.size(); j++) {
                Coordinate a = scanner.get(j);
                for(int k=0; k<obj.scanner.size(); k++) {
                    Coordinate b = rotatedObj.scanner.get(k);
                    Coordinate offset = new Coordinate(a.x - b.x, a.y - b.y, a.z - b.z);
                    Scanner temp = new Scanner(rotatedObj, offset);
                    if(minBeaconsMatch(temp)) {
                        temp.relativePosition = offset;
                        return temp;
                    }
                }
            }
        }
        return null;
    }

    private boolean minBeaconsMatch(Scanner obj) {
        List<Coordinate> intersection = new ArrayList<>();

        for(int i=0; i<obj.scanner.size(); i++) {
            if(scanner.contains(obj.scanner.get(i))) {
                intersection.add(obj.scanner.get(i));
            }
        }

        if(intersection.size() >= Util.MIN_BEACONS_COMPARE) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Coordinate c: scanner) {
            sb.append(c.toString() + "\n");
        }
        return sb.toString();
    }

}