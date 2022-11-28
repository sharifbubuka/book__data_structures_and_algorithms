package data_structures;

public class SinglyLinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    public SinglyLinkedList() {}

    // access methods
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
      return this.size == 0;
    }

    public T first() {
        if (this.isEmpty()) return null;
        return head.getElement();
    }

    public T last() {
        if (this.isEmpty()) return null;
        return this.tail.getElement();
    }

    // update methods
    public void addFirst(T element) {
        Node<T> newHead = new Node(element, this.head);
        if (this.size == 0)
            this.tail = this.head;
        size++;
    }

    public void addLast(T element) {
        Node<T> newTail = new Node(element, null);
        if (this.isEmpty())
            this.head = newTail;
        else
            this.tail.setNext(newTail);
        this.tail = newTail;
        this.size++;
    }

    public T removeFirst() {
        if (this.isEmpty()) return null;
        T answer = head.getElement();
        this.head = this.head.getNext();
        this.size--;
        if (this.size == 0)
            this.tail = null;
        return answer;
    }

    private static class Node<T> {
        private T element;
        private Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
