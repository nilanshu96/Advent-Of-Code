public class Instruction {
    boolean toggle;
    Pair<Integer,Integer> xRange, yRange, zRange;

    Instruction(boolean toggle, Pair<Integer,Integer> xRange, Pair<Integer,Integer> yRange, Pair<Integer,Integer> zRange) {
        this.toggle = toggle;
        this.xRange = new Pair<>(xRange);
        this.yRange = new Pair<>(yRange);
        this.zRange = new Pair<>(zRange);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(toggle?"on":"off");
        sb.append(" ");
        sb.append("x=" + xRange.first + ".." + xRange.second);
        sb.append(",");
        sb.append("y=" + yRange.first + ".." + yRange.second);
        sb.append(",");
        sb.append("z=" + zRange.first + ".." + zRange.second);

        return sb.toString();
    }
}