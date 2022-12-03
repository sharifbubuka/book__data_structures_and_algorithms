package data_structures.tree;

public abstract class AbstractTree<T> implements Tree<T> {

    @Override
    public boolean isInternal(Position<T> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(Position<T> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    @Override
    public boolean isRoot(Position<T> p) throws IllegalArgumentException {
        return p == root();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public int depth(Position<T> p) {
        if (isRoot(p))
            return 0;
        else
            return 1 + depth(parent(p));
    }

    private int heightBad() { // works, but quadratic worst-case time O(n^2)
        int h = 0;
        for (Position<T> p: positions())
            if (isExternal(p))
                h = Math.max(h, depth(p));
        return h;
    }

    public int height(Position<T> p) {
        int h = 0;
        for (Position<T> c: children(p))
            h = Math.max(h, 1 + height(c));
        return h;
    }
}
