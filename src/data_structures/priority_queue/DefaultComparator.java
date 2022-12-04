package data_structures.priority_queue;

import java.util.Comparator;

public class DefaultComparator<T> implements Comparator<T> {

    @Override
    public int compare(T a, T b) throws ClassCastException {
        return ((Comparable<T>)a).compareTo(b);
    }
}
