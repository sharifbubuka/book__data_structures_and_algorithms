package data_structures.stack;

import data_structures.list.SinglyLinkedList;

public class LinkedStack<T> implements Stack<T> {
    private SinglyLinkedList<T> list = new SinglyLinkedList();

    public LinkedStack() { }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(T element) {
        list.addFirst(element);
    }

    @Override
    public T top() {
        return list.first();
    }

    @Override
    public T pop() {
        return list.removeFirst();
    }
}
