public class Triplet<T,V,K> {
    T first;
    V second;
    K third;

    Triplet(T first, V second, K third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + "," + third + ")";
    }
}