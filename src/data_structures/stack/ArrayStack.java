package data_structures.stack;

public class ArrayStack<T> implements Stack<T>, Cloneable {
    public static final int CAPACITY = 1000;
    private final T[] data;
    private int t = -1;

    public ArrayStack() {
        data = (T[]) new Object[CAPACITY];
    }
    public ArrayStack(int capacity) {
        data = (T[]) new Object[capacity];
    }

    /**
     * O(1)
     * @return the size of the stack
     */
    @Override
    public int size() {
        return t + 1;
    }

    @Override
    public boolean isEmpty() {
        return t == -1;
    }

    @Override
    public void push(T e) throws IllegalStateException {
        if (this.size() == data.length) throw new IllegalStateException("Stack is full");
        data[++t] = e;
    }

    @Override
    public T top() {
        if (this.isEmpty()) return null;
        return data[t];
    }

    @Override
    public T pop() {
        if (this.isEmpty()) return null;
        T answer = data[t];
        data[t] = null;
        t--;
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        ArrayStack<T> other = (ArrayStack<T>) o;
        if (this.size() != other.size()) return false;
        for (int i = this.data.length -1; i >= 0; i--) {
            T element = other.pop();
            if (element != this.data[i]) return false;
        }
        return true;
    }

    @Override
    protected ArrayStack<T> clone() throws CloneNotSupportedException {
        ArrayStack<T> clone = new ArrayStack(CAPACITY);
        for (int i = 0; i < this.data.length; i++) {
            clone.push(data[i]);
        }
        return clone;
    }
}
