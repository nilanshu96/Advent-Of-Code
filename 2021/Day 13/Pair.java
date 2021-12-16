public class Pair<T,V> {
    private T first;
    private V second;

    Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    T getFirst() {
        return first;
    }

    V getSecond() {
        return second;
    }
    
    @Override
    public String toString() {
        return "(" + first + "," + second + ")"; 
    }
}