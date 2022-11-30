package data_structures.queue;

public interface CircularQueue<T> extends Queue<T> {

    /**
     * Rotates the front element of the queue to the back of the queue.
     * This does nothing if the queue is empty.
     */
    void rotate();
}
