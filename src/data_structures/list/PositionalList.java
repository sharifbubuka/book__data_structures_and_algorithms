package data_structures.list;

/** An interface for positional lists. */
public interface PositionalList<T> {
    /** Returns the number of elements in the list. */
    int size();

    /** Tests whether the list is empty. */
    boolean isEmpty();

    /** Returns the first Position in the list (or null, if empty). */
    Position<T> first();

    /** Returns the last Position in the list (or null, if empty). */
    Position<T> last();

    /** Returns the Position immediately before Position p (or null, if p is first). */
    Position<T> before(Position<T> p) throws IllegalArgumentException;

    /** Returns the Position immediately after Position p (or null, if p is first). */
    Position<T> after(Position<T> p) throws IllegalArgumentException;

    /** Inserts element e at the front of the list and returns its new Position */
    Position<T> addFirst(T e);

    /** Inserts element e at the back of the list and returns its new Position */
    Position<T> addLast(T e);

    /** Inserts element e immediately before Position p and returns its new Position. */
    Position<T> addBefore(Position<T> p, T e) throws IllegalArgumentException;

    /** Inserts element e immediately after Position p and returns its new Position. */
    Position<T> addAfter(Position<T> p, T e) throws IllegalArgumentException;

    /** Replaces the element stored at Position p and returns the replaced element. */
    T set(Position<T> p, T e) throws IllegalArgumentException;

    /** Removes the element stored at Position p and returns it (invalidating p). */
    T remove(Position<T> p) throws IllegalArgumentException;
}
