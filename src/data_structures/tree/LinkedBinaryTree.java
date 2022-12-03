package data_structures.tree;

import data_structures.list.ArrayList;
import data_structures.list.List;
import data_structures.queue.LinkedQueue;
import data_structures.queue.Queue;
import data_structures.tree.Position;

import java.util.Iterator;

public class LinkedBinaryTree<T> extends AbstractBinaryTree<T> {
    // instance variables
    protected Node<T> root = null;
    private int size = 0;

    // constructors
    public LinkedBinaryTree() {}

    // accessor methods
    public int size() {
        return size;
    }

    public Position<T> root() {
        return root;
    }

    public Position<T> parent(Position<T> p) throws IllegalArgumentException {
        Node<T> node = validate(p);
        return node.getParent();
    }

    public Position<T> left(Position<T> p) throws IllegalArgumentException {
        Node<T> node = validate(p);
        return node.getLeft();
    }

    public Position<T> right(Position<T> p) throws IllegalArgumentException {
        Node<T> node = validate(p);
        return node.getRight();
    }

    // update methods
    public Position<T> addRoot(T e) throws IllegalArgumentException {
        if (!isEmpty()) throw new IllegalArgumentException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public Position<T> addLeft(Position<T> p, T e) throws IllegalArgumentException {
        Node<T> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        Node<T> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    public T set(Position<T> p, T e) throws IllegalArgumentException {
        Node<T> node = validate(p);
        T temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    public void attach(Position<T> p, LinkedBinaryTree<T> t1, LinkedBinaryTree<T> t2) throws IllegalArgumentException {
        Node<T> node = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size();
        if (!t1.isEmpty()) {
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            t2.root.setParent(node);
            node.setLeft(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    public T remove(Position<T> p) throws IllegalArgumentException {
        Node<T> node = validate(p);
        if (numChildren(p) == 2) throw new IllegalArgumentException("p has two children");
        Node<T> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null)
            child.setParent(node.getParent());
        if (node == root)
            root = child;
        else {
            Node<T> parent = node.getParent();
            if (node == parent.getLeft())
                parent.setLeft(child);
            else parent.setRight(child);
        }
        size--;
        T temp = node.getElement();
        node.setElement(null);
        node.setRight(null);
        node.setLeft(null);
        node.setParent(node);
        return temp;
    }

    public Position<T> addRight(Position<T> p, T e) throws IllegalArgumentException {
        Node<T> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("p already has a right child");
        Node<T> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementIterator();
    }

    @Override
    public Iterable<Position<T>> positions() {
        return inorder();
    }

    public Iterable<Position<T>> preorder() {
        List<Position<T>> snapshot = new ArrayList<Position<T>>();
        if (!isEmpty())
            preorderSubtree(root(), snapshot);
        return (Iterable<Position<T>>) snapshot;
    }

    public Iterable<Position<T>> postorder() {
        List<Position<T>> snapshot = new ArrayList<Position<T>>();
        if (!isEmpty())
            postorderSubtree(root(), snapshot);
        return (Iterable<Position<T>>) snapshot;
    }

    public Iterable<Position<T>> breadthFirst() {
        List<Position<T>> snapshot = new ArrayList<Position<T>>();
        if (!isEmpty()) {
            Queue<Position<T>> fringe = new LinkedQueue<>();
            fringe.enqueue(root());
            while (!fringe.isEmpty()) {
                Position<T> p = fringe.dequeue();
                snapshot.add(0, p);
                for (Position<T> c : children(p))
                    fringe.enqueue(c);
            }
        }
        return (Iterable<Position<T>>) snapshot;
    }

    public Iterable<Position<T>> inorder() {
        List<Position<T>> snapshot = new ArrayList<>();
        if (!isEmpty())
            inorderSubtree(root(), snapshot);
        return (Iterable<Position<T>>) snapshot;
    }

    // utility methods
    protected Node<T> createNode(T e, Node<T> parent, Node<T> left, Node<T> right) {
        return new Node<T>(e, parent, left, right);
    }

    protected Node<T> validate(Position<T> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<T> node = (Node<T>) p;
        if (node.getParent() == node)
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    private void preorderSubtree(Position<T> p, List<Position<T>> snapshot) {
        snapshot.add(0, p);
        for (Position<T> c: children(p))
            preorderSubtree(c, snapshot);
    }

    private void postorderSubtree(Position<T> p, List<Position<T>> snapshot) {
        for (Position<T> c: children(p))
            postorderSubtree(c, snapshot);
        snapshot.add(0, p);
    }

    private void inorderSubtree(Position<T> p, List<Position<T>> snapshot) {
        if (left(p) != null)
            inorderSubtree(left(p), snapshot);
        snapshot.add(0, p);
        if (right(p) != null)
            inorderSubtree(right(p), snapshot);
    }

    // inner node class
    private static class Node<T> implements Position<T> {
        private T element;
        private Node<T> parent;
        private Node<T> left;
        private Node<T> right;

        public Node(T e, Node<T> above, Node<T> leftChild, Node<T> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        // accessor methods
        @Override
        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }
    }

    // inner nested elementIterator class
    private class ElementIterator implements Iterator<T> {
        Iterator<Position<T>> posIterator = positions().iterator();

        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public T next() {
            return posIterator.next().getElement();
        }

        @Override
        public void remove() {
            posIterator.remove();
        }
    }
}
