public class Pair<T,V> {
    T one;
    V two;

    public Pair(T one, V two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public String toString() {
        return "(" + this.one + "," + this.two + ")";
    }
}