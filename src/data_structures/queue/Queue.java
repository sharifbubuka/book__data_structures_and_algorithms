package data_structures.queue;

public interface Queue<T> {
    /**
     * Returns the number of elements in the queue
     * @return the number of elements in the queue.
     */
    int size();

    /**
     * Tetss whether the queue is empty
     * @return true if the queue is empty, other false
     */
    boolean isEmpty();

    /**
     * Inserts an element at the rear of the queue
     * @param e element to insert into the queue
     */
    void enqueue(T e);

    /**
     * returns, but does not remove, the first element of the queue (null if empty)
     * @return
     */
    T first();

    /**
     * Removes and returns the first element of the queue (null if empty)
     * @return
     */
    T dequeue();
}
