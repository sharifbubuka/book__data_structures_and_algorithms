package data_structures;

/**
 * for instance, used to implement round-robin scheduling
 * algorithm for allocating computational in an operating system.
 */
public class CircularlyLinkedList<T> implements Cloneable{
    private Node<T> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {}

    // access methods
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T first() {
        if (this.isEmpty()) return null;
        return this.tail.getNext().getElement();
    }

    public T last() {
        if (this.isEmpty()) return null;
        return this.tail.getElement();
    }

    // update methods
    public void rotate() {
        if (this.tail != null)
            this.tail = this.tail.getNext();
    }

    public void addFirst(T element) {
        if (this.size == 0) {
            Node implicitHead = new Node(element, null);
            this.tail.setNext(implicitHead);
        } else {
            Node<T> implicitHead = new Node(element, this.tail.getNext());
            this.tail.setNext(implicitHead);
        }
        size++;
    }

    public void addLast(T element) {
        this.addFirst(element);
        this.tail = this.tail.getNext();
    }

    public T removeFirst() {
        if (this.isEmpty()) return null;
        Node<T> formerHead = this.tail.getNext();
        if (formerHead == this.tail) this.tail = null;
        else this.tail.setNext(formerHead.getNext());
        this.size--;
        return formerHead.getElement();
    }

    private static class Node<T> {
        private T element;
        private Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return this.element;
        }

        public CircularlyLinkedList.Node<T> getNext() {
            return this.next;
        }

        public void setNext(CircularlyLinkedList.Node<T> next) {
            this.next = next;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        CircularlyLinkedList other = (CircularlyLinkedList) o;
        if (this.size != other.size()) return false;
        // check for equivalence per node pair starting from tail
        Node walkA = this.tail;
        Node walkB = other.tail;
        while (walkA != null) {
            if (!walkA.getElement().equals(walkB.getElement())) return false;
            walkA = walkA.getNext();
            walkB = walkB.getNext();
        }
        return true;
    }

    @Override
    public CircularlyLinkedList<T> clone() throws CloneNotSupportedException {
        CircularlyLinkedList<T> other = (CircularlyLinkedList<T>) super.clone();
        if (this.size > 0) {
            other.tail = new Node(this.tail.getElement(), null);
            Node<T> walkOriginal = this.tail.getNext();
            Node<T> otherTail = other.tail;
            int sizeCount = 1;
            while (sizeCount <= this.size) {
                Node<T> newest = new Node(walkOriginal.getNext(), null);
                otherTail.setNext(newest);
                otherTail = newest;
                walkOriginal = walkOriginal.getNext();
                sizeCount++;
            }
        }
        return other;
    }
}
