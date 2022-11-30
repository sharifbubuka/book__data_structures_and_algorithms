package data_structures.queue;

public interface Deque<T> {
    /** Returns the number of elements in the deque */
    int size();

    /** Tests whether the deque is empty */
    boolean isEmpty();

    /** Returns, but does not remove, the first element of the deque (null if empty) */
    T first();

    /** Returns, but does not remove, the last element of the deque (null if empty) */
    T last();

    /** Inserts an element at the front of the deque */
    void addFirst(T e);

    /** Inserts an element at the back of the deque */
    void addLast(T e);

    /** Removes and returns the first element of the deque (null if empty) */
    T removeFirst();

    /** removes and returns the last element of the deque (null if empty) */
    T removeLast();
}
