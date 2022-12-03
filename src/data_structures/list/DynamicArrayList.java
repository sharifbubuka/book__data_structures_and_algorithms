package data_structures.list;

public class DynamicArrayList<T> extends ArrayList<T> {

    // constructors

    public DynamicArrayList() { super(); };

    public DynamicArrayList(int capacity) { super(capacity); }

    @Override
    public void add(int i, T e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        if (size == data.length)
            resize(2 * data.length);
        for (int k = size - 1; k >= i; k--)
            data[k + 1] = data[k];
        data[i] = e;
        size++;
    }

    // utility methods
    /** Resizes internal array to have given capacity >= size. */
    protected void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        for (int k = 0; k < size; k++)
            temp[k] = data[k];
        data = temp;
    }

    public static void main(String[] args) {
        DynamicArrayList<Integer> d = new DynamicArrayList(2);
    }
}
