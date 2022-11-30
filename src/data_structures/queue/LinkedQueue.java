package data_structures.queue;

import data_structures.list.SinglyLinkedList;

public class LinkedQueue<T> implements Queue<T> {
    private SinglyLinkedList<T> list = new SinglyLinkedList();

    public LinkedQueue() {};

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(T element) {
        list.addLast(element);
    }

    @Override
    public T first() {
        return list.first();
    }

    @Override
    public T dequeue() {
        return list.removeFirst();
    }
}
