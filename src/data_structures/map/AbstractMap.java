package data_structures.map;

import java.util.Iterator;

public abstract class AbstractMap<K,V> implements Map<K,V> {
    @Override
    public Iterable<K> keySet() {
        return new KeyIterable();
    }

    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }

    // nested MapEntry class
    protected static class MapEntry<K,V> implements Entry<K,V> {
        // instance variables
        private K k;
        private V v;

        // constructors
        public MapEntry(K key, V value) {
            k = key;
            v = value;
        }

        // instance methods
        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        // utility methods
        protected void setKey(K key) { k = key; }
        protected V setValue(V value) {
            V old = v;
            v = value;
            return old;
        }
    }

    private class KeyIterator implements Iterator<K> {
        private Iterator<Entry<K,V>> entries = entrySet().iterator();

        // instance methods
        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public K next() {
            return (K) entries.next().getKey();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class KeyIterable implements Iterable<K> {
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    private class ValueIterator implements Iterator {
        private Iterator<Entry<K,V>> entries = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public Object next() {
            return entries.next().getValue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class ValueIterable implements Iterable<V> {
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }
}
