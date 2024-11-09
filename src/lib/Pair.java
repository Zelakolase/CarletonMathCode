package lib;
/**
 * A pair of generic type T publicly accessible
 */
public class Pair<T> {
    public T first;
    public T second;

    /**
     * General constructor to initalize the pair
     * @param f First element
     * @param s Second Element
     */
    public Pair(T f, T s) {
        first = f;
        second = s;
    }

    /**
     * Checks equality between two pairs
     * @param in The pair to be compared to
     * @return Whether the first and second elements of the first pair equal the first and second elements of the second pair, respectively.
     */
    @SuppressWarnings("unchecked") // Ignore Unckecked casts (unrecommended)
    @Override
    public boolean equals(Object in) {
        return ((Pair<T>)in).first.equals(first) && ((Pair<T>)in).second.equals(second);
    }
}
