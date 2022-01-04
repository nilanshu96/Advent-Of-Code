public class Cuboid {
    int x1,x2,y1,y2,z1,z2;

    Cuboid(int x1, int x2, int y1, int y2, int z1, int z2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.z1 = z1;
        this.z2 = z2;
    }

    Cuboid(Pair<Integer,Integer> xRange, Pair<Integer,Integer> yRange, Pair<Integer,Integer> zRange) {
        this(xRange.first, xRange.second, yRange.first, yRange.second, zRange.first, zRange.second);
    }

    long getVolume() {
        long length = x2 - x1 + 1;
        long width = y2 - y1 + 1;
        long height = z2 - z1 + 1;
        return length * width * height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("(" + x1 + "," + x2 + ")");
        sb.append(",");
        sb.append("(" + y1 + "," + y2 + ")");
        sb.append(",");
        sb.append("(" + z1 + "," + z2 + ")");
        sb.append("]");
        return sb.toString();
    }
}