class Pair<T,V> {
    T first;
    V second;

    Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "First: " + first + ", Second: " + second;
    }
}