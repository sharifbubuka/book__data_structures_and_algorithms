package data_structures.priority_queue;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {
    // instance variables
    private Comparator<K> comp;

    // constructors
    protected AbstractPriorityQueue(Comparator<K> c) { this.comp = c; }
    protected AbstractPriorityQueue() { this(new DefaultComparator<K>()); }

    // public instance methods


    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // method to compare two entries according to key
    protected int compare(Entry<K,V> a, Entry<K,V> b) {
        return this.comp.compare(a.getKey(), b.getKey());
    }

    // method to determine whether a key is valid
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (this.comp.compare(key, key) == 0);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    // nested PQEntry
    protected static class PQEntry<K,V> implements Entry<K,V> {
        private K k;
        private V v;

        public PQEntry(K key, V value) {
            this.k = key;
            this.v = value;
        }

        // instance methods
        @Override
        public K getKey() {
            return this.k;
        }

        @Override
        public V getValue() {
            return this.v;
        }

        // utility methods of nested PQEntry class
        protected void setKey(K key) { this.k = key; }
        protected void setValue(V value) { this.v = value; }
    }
}
