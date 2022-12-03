package data_structures.tree;

import data_structures.list.ArrayList;
import data_structures.list.List;

public abstract class AbstractBinaryTree<T> extends AbstractTree<T> implements  BinaryTree<T> {
    @Override
    public Position<T> sibling(Position<T> p) {
        Position<T> parent = parent(p);
        if (parent == null) return null;
        if (p == left(parent))
            return right(parent);
        else
            return left(parent);
    }

    @Override
    public Iterable<Position<T>> children(Position<T> p) {
        List<Position<T>> snapshot = new ArrayList<>(2);
        if (right(p) != null)
            snapshot.add(0, right(p));
        if (left(p) != null)
            snapshot.add(0, left(p));
        return (Iterable<Position<T>>) snapshot;
    }

    @Override
    public int numChildren(Position<T> p) throws IllegalArgumentException {
        int count = 0;
        if (left(p) != null)
            count++;
        if (right(p) != null)
            count++;
        return count;
    }
}
