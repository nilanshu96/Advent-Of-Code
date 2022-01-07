import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Coordinate implements Comparable<Coordinate>{
    int x,y,z;

    Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    Coordinate(Coordinate obj) {
        this.x = obj.x;
        this.y = obj.y;
        this.z = obj.z;
    }

    Coordinate(Coordinate obj, String order) {
        
        this(obj);
        Pattern pat = Pattern.compile("(-?\\w),(-?\\w),(-?\\w)");
        Matcher mat = pat.matcher(order);

        if(mat.matches()) {
            String x = mat.group(1);
            if(x.charAt(0) == '-') {
                this.x = -1 * obj.getAxisValue(x.charAt(1));
            } else {
                this.x = obj.getAxisValue(x.charAt(0));
            }

            String y = mat.group(2);
            if(y.charAt(0) == '-') {
                this.y = -1 * obj.getAxisValue(y.charAt(1));
            } else {
                this.y = obj.getAxisValue(y.charAt(0));
            }

            String z = mat.group(3);
            if(z.charAt(0) == '-') {
                this.z = -1 * obj.getAxisValue(z.charAt(1));
            } else {
                this.z = obj.getAxisValue(z.charAt(0));
            }
        }
    }

    Coordinate(Coordinate obj, Coordinate offset) {
        this(obj);
        this.x += offset.x;
        this.y += offset.y;
        this.z += offset.z;
    }

    private int getAxisValue(char c) {
        switch(c) {
            case 'x': return x;
            case 'y': return y;
            case 'z': return z;
        }
        return x;
    }

    Coordinate diff(Coordinate other) {
        int diffX = x - other.x;
        int diffY = y - other.y;
        int diffZ = z - other.z;

        return new Coordinate(diffX, diffY, diffZ);
    }

    @Override
    public int compareTo(Coordinate o) {
        if(x < o.x) return -1;
        if(x > o.x) return 1;

        if(y < o.y) return -1;
        if(y > o.y) return 1;

        if(z < o.z) return -1;
        if(z > o.z) return 1;

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }

        if(this.getClass() != o.getClass()) {
            return false;
        }

        final Coordinate other = (Coordinate)o;

        if(this.x == other.x && this.y == other.y && this.z == other.z) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return x + "," + y + "," + z;
    }
}