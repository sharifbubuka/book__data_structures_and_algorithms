package data_structures.list;

/**
 * implemented using sentinels (header and trailer nodes)
 */
public class DoublyLinkedList<T> implements Cloneable {
    private Node<T> header;
    private Node<T> trailer;
    private int size = 0;

    public DoublyLinkedList() {
        this.header = new Node(null, null, null);
        this.trailer = new Node(null, null, null);
        this.header.setNext(trailer);
    }

    public int size() {
        return this.size();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T first() {
        if (this.isEmpty()) return null;
        return this.header.getNext().getElement();
    }

    public T last() {
        if (this.isEmpty()) return null;
        return this.trailer.getNext().getElement();
    }
    public void addFirst(T element) {
        addBetween(element, this.header, this.header.getNext());
    }

    public void addLast(T element) {
        addBetween(element, this.trailer.getPrev(), this.trailer);
    }

    public T removeFirst() {
        if (this.isEmpty()) return null;
        return this.remove(this.header.getNext());
    }

    public T removeLast() {
        if (this.isEmpty()) return null;
        return this.remove(this.trailer.getPrev());
    }

    // private update methods
    public void addBetween(T element, Node<T> predecessor, Node<T> successor) {
        Node<T> newest = new Node(element, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    private T remove(Node<T> node) {
        Node<T> predecessor = node.getPrev();
        Node<T> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        this.size--;
        return node.getElement();
    }

    // inner node class
    private static class Node<T> {
        private T element;
        private Node<T> next;
        private Node<T> prev;

        public Node(T element, DoublyLinkedList.Node<T> prev, DoublyLinkedList.Node<T> next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public DoublyLinkedList.Node<T> getPrev() {
            return this.prev;
        }

        public DoublyLinkedList.Node<T> getNext() {
            return this.next;
        }

        public void setPrev(DoublyLinkedList.Node<T> next) {
            this.prev = next;
        }

        public void setNext(DoublyLinkedList.Node<T> next) {
            this.next = next;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        DoublyLinkedList other = (DoublyLinkedList) o;
        if (this.size != other.size()) return false;
        // check for equivalence per node pair starting from tail
        Node walkA = this.trailer.getNext();
        Node walkB = other.trailer.getNext();
        while (walkA.getElement() != null) {
            if (!walkA.getElement().equals(walkB.getElement())) return false;
            walkA = walkA.getNext();
            walkB = walkB.getNext();
        }
        return true;
    }

    @Override
    public DoublyLinkedList<T> clone() throws CloneNotSupportedException {
        DoublyLinkedList<T> other = (DoublyLinkedList<T>) super.clone();
        if (this.size > 0) {
            other.header = new Node(null, null, null);
            other.trailer = new Node(null, null, null);
            other.header.setNext(other.trailer);
            Node<T> walkOriginal = this.header.getNext();
            while (walkOriginal.getElement() != null) {
                T newest = walkOriginal.getElement();
                other.addFirst(newest);
                walkOriginal = walkOriginal.getNext();
            }
        }
        return other;
    }
}
