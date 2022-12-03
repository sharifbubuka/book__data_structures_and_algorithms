package data_structures.tree;

public interface BinaryTree<T> extends Tree<T> {
    Position<T> left(Position<T> p) throws IllegalArgumentException;

    Position<T> right(Position<T> p) throws IllegalArgumentException;

    Position<T> sibling(Position<T> p) throws IllegalArgumentException;
}
