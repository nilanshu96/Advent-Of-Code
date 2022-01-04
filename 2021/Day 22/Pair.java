public class Pair<T,V> {
    T first;
    V second;

    Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    Pair(Pair<T,V> other) {
        this.first = other.first;
        this.second = other.second;
    }

    @Override
    public String toString() {
        return "(" + this.first + "," + this.second + ")";
    }
}