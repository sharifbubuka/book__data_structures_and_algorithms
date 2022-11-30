package data_structures.queue;

public class ArrayQueue<T> implements Queue<T> {
    public int CAPACITY;
    private T[] data;
    private int f = 0;
    private int size = 0;

    public ArrayQueue() {
        data = (T[]) new Object[this.CAPACITY];
    }
    public ArrayQueue(int capacity) {
        data = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void enqueue(T element) {
        if (this.size == data.length) throw new IllegalStateException("Queue is full");
        int avail = (this.f + size) % this.data.length;
        this.data[avail] = element;
        size++;
    }

    @Override
    public T first() {
        if (this.isEmpty()) return null;
        return this.data[f];
    }

    @Override
    public T dequeue() {
        if (this.isEmpty()) return null;
        T answer = this.data[f];
        this.data[f] = null;
        this.f = (this.f + 1) % data.length;
        this.size--;
        return answer;
    }
}
