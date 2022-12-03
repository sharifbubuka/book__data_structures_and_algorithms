package data_structures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrayList<T> implements List<T> {
    // instance variables
    public static final int CAPACITY = 16;
    protected T[] data;
    protected int size = 0;

    // constructors
    public ArrayList() {
        this(CAPACITY);
    }

    public ArrayList(int capacity) {
        data = (T[]) new Object[capacity];
    }

    // public methods
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public T get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    @Override
    public T set(int i, T e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        T temp = data[i];
        data[i] = e;
        return temp;
    }

    @Override
    public void add(int i, T e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        if (size == data.length)
            throw new IllegalStateException("Array is full");
        for (int k = size - 1; k >= i; k--)
            data[k + 1] = data[k];
        data[i] = e;
        size++;
    }

    @Override
    public T remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        T temp = data[i];
        for (int k = i; k < size - 1; k++)
            data[k] = data[k + 1];
        data[size - 1] = null;
        size--;
        return temp;
    }

    //utility methods
    /** Checks whether the given index is in the range [0, n−1]. */
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
    }

    // iteration class
    private class ArrayIterator implements Iterator<T> {
        private int j = 0;
        private boolean removable = false;

        /**
         * Tests whether the iterator has a next object
         * @return true if there are further objects, false otherwise
         */
        @Override
        public boolean hasNext() {
            return j < size;
        }

        /**
         * Returns the next object in the iterator
         * @return next object
         * @throws NoSuchElementException if there are no further elements
         */
        @Override
        public T next() throws NoSuchElementException {
            if (j == size) throw new NoSuchElementException("No next element");
            removable = true;
            return data[j++];
        }

        /**
         * Removes the element returned from the recent call to next
         * @throws IllegalStateException if next has not yet been called
         * @throws IllegalStateException if remove was already called since recent next
         */
        @Override
        public void remove() throws IllegalStateException {
            if (!removable) throw new IllegalStateException("Nothing to remove");
            ArrayList.this.remove(j - 1);
            j--;
            removable = false;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayIterator();
    }
}
