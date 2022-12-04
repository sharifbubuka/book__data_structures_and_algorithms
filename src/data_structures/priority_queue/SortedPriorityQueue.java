package data_structures.priority_queue;

import data_structures.list.LinkedPositionalList;
import data_structures.list.Position;
import data_structures.list.PositionalList;

import java.util.Comparator;

public class SortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    // instance variables
    private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();

    // constructors
    public SortedPriorityQueue() { super(); }
    public SortedPriorityQueue(Comparator<K> comp) { super(comp); }

    // public instance methods
    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key, value);
        Position<Entry<K,V>> walk = list.last();
        while (walk != null && compare(newest, walk.getElement()) < 0)
            walk = list.before(walk);
        if (walk == null)
            list.addFirst(newest);
        else
            list.addAfter(walk, newest);
        return newest;
    }

    @Override
    public Entry<K, V> min() {
        if (list.isEmpty()) return null;
        return list.first().getElement();
    }

    @Override
    public Entry<K, V> removeMin() {
        if (list.isEmpty()) return null;
        return list.remove(list.first());
    }
}
