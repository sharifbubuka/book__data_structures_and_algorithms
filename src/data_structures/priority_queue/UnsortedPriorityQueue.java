package data_structures.priority_queue;

import data_structures.list.LinkedPositionalList;
import data_structures.list.Position;
import data_structures.list.PositionalList;

import java.util.Comparator;

public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    // instance variables
    private LinkedPositionalList<Entry<K,V>> list = new LinkedPositionalList<>();

    // constructors
    public UnsortedPriorityQueue() { super(); }
    public UnsortedPriorityQueue(Comparator<K> comp) { super(comp); }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key, value);
        list.addLast(newest);
        return newest;
    }

    @Override
    public Entry<K, V> min() {
        if (list.isEmpty()) return null;
        return findMin().getElement();
    }

    @Override
    public Entry<K, V> removeMin() {
        if (list.isEmpty()) return null;
        return list.remove(findMin());
    }

    // utility methods
    private Position<Entry<K,V>> findMin() {
        Position<Entry<K,V>> small = list.first();
        for (Position<Entry<K,V>> walk: list.positions())
            if (compare(walk.getElement(), small.getElement()) < 0)
                small = walk;
        return small;
    }
}
