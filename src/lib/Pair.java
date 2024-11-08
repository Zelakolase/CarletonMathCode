package lib;
/**
 * A pair of generic type T publicly accessible
 */
public class Pair<T> {
    public T first;
    public T second;

    /**
     * Checks equality between two pairs
     * @param in The pair to be compared to
     * @return Whether the first and second elements of the first pair equal the first and second elements of the second pair, respectively.
     */
    public boolean equals(Pair<T> in) {
        return in.first.equals(first) && in.second.equals(second);
    }
}
