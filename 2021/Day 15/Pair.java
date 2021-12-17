public class Pair<T,V> {
    T first;
    V second;

    Pair(T t, V v) {
        first = t;
        second = v;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(obj.getClass() != this.getClass()) {
            return false;
        }

        final Pair<?,?> other = (Pair<?,?>)obj;

        if(first.equals(other.first) && second.equals(other.second)) {
            return true;
        }

        return false;
    }
}