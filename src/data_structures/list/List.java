package data_structures.list;

public interface List<T> {
    /** Returns the number of elements in this list */
    int size();

    /** Returns whether the list is empty */
    boolean isEmpty();

    /** Returns, but does not remove, the element at index i */
    T get(int i) throws IndexOutOfBoundsException;

    /** Replaces the element at index i with e, and returns the replaced element. */
    T set(int i, T e) throws IndexOutOfBoundsException;

    /** Inserts element e to be at index i, shifting all subsequent elements later. */
    void add(int i, T e) throws IndexOutOfBoundsException;

    /** Removes/returns the element at index i, shifting subsequent elements earlier. */
    T remove (int i) throws IndexOutOfBoundsException;
}
