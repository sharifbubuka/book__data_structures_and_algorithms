package data_structures.list;

import javax.swing.text.ElementIterator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<T> implements PositionalList<T> {
    // instance variables
    private Node<T> header;
    private  Node<T> trailer;
    private int size = 0;

    // constructors
    public LinkedPositionalList() {
        this.header = new Node<>(null, null, null);
        this.trailer = new Node<>(null, header, null);
        this.header.setNext(trailer);
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
    public Position first() {
        return position(header.getNext());
    }

    @Override
    public Position last() {
        return position(trailer.getPrev());
    }

    @Override
    public Position before(Position p) throws IllegalArgumentException {
        Node<T> node = validate(p);
        return position((node.getPrev()));
    }

    @Override
    public Position after(Position p) throws IllegalArgumentException {
        Node<T> node = validate(p);
        return position((node.getNext()));
    }

    @Override
    public Position addFirst(T e) {
        return addBetween(e, header, header.getNext());
    }

    @Override
    public Position addLast(T e) {
        return addBetween(e, trailer, trailer.getNext());
    }

    @Override
    public Position addBefore(Position<T> p, T e) throws IllegalArgumentException {
        Node<T> node = validate(p);
        return addBetween(e, node.getNext(), node);
    }

    @Override
    public Position addAfter(Position<T> p, T e) throws IllegalArgumentException {
        Node<T> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    @Override
    public T set(Position<T> p, T e) throws IllegalArgumentException {
        Node<T> node = validate(p);
        T answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    @Override
    public T remove(Position<T> p) throws IllegalArgumentException {
        Node<T> node = validate(p);
        Node<T> predecessor = node.getPrev();
        Node<T> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        T answer = node.getElement();
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return answer;
    }

    public Iterable<Position<T>> positions() {
        return new PositionIterable();
    }

    public Iterator<T> iterator() {
        return new ElementIterator();
    }

    public static void insertionSort(PositionalList<Integer> list) {
        Position<Integer> marker = list.first();
        while (marker != list.last()) {
            Position<Integer> pivot = list.after(marker);
            int value = pivot.getElement();
            if (value > marker.getElement())
                marker = pivot;
            else {
                Position<Integer> walk = marker;
                while (walk != list.first() && list.before(walk).getElement() > value)
                    walk = list.before(walk);
                list.remove(pivot);
                list.addBefore(walk, value);
            }
        }
    }

    // private utility methods
    /** Validates the position and returns it as a node */
    private Node<T> validate(Position<T> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid position p");
        Node<T> node = (Node<T>) p;
        if (node.getNext() == null)
            throw new IllegalArgumentException("Position p is no longer in the list");
        return node;
    }

    /** Returns the given node as a Position (or null, if it is a sentinel) */
    private Position<T> position(Node<T> node) {
        if (node == header || node == trailer)
            return null;
        return node;
    }

    /** Adds element e to the linked list between the given nodes */
    private Position<T> addBetween(T e, Node<T> pred, Node<T> succ) {
        Node<T> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    // Node inner class
    private static class Node<T> implements Position<T> {
        private T element;
        private Node<T> prev;
        private Node<T> next;
        public Node(T e, Node<T> p, Node<T> n) {
            element = e;
            prev = p;
            next = n;
        }

        public T getElement() throws IllegalStateException {
            if (next == null)
                throw new IllegalStateException("Position no longer valid");
            return element;
        }

        public Node<T> getPrev() {
            return this.prev;
        }

        public Node<T> getNext() {
            return this.next;
        }

        public void setElement(T e) { element = e; }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    // PositionIterator nested class
    private class PositionIterator implements Iterator<Position<T>> {
        private Position<T> cursor = first();
        private Position<T> recent = null;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public Position<T> next() throws NoSuchElementException {
            if (cursor == null) throw new NoSuchElementException("No more elements");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        @Override
        public void remove() throws IllegalStateException {
            if (recent == null) throw new IllegalStateException("No more elements to remove");
            LinkedPositionalList.this.remove(recent);
            recent = null;
        }
    }

    // PositionIterable nested class
    private class PositionIterable implements Iterable<Position<T>> {

        @Override
        public Iterator<Position<T>> iterator() {
            return new PositionIterator();
        }
    }

    // ElementIterator nested class
    private class ElementIterator implements Iterator<T> {
        Iterator<Position<T>> postItrator = new PositionIterator();

        @Override
        public boolean hasNext() {
            return postItrator.hasNext();
        }

        @Override
        public T next() {
            return postItrator.next().getElement();
        }

        @Override
        public void remove() {
            postItrator.remove();
        }
    }
}
