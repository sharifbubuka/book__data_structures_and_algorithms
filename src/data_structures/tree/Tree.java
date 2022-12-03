package data_structures.tree;

import java.util.Iterator;

public interface Tree<T> extends Iterable<T> {
    Position<T> root();

    Position<T> parent(Position<T> child) throws IllegalArgumentException;

    Iterable<Position<T>> children(Position<T> p) throws IllegalArgumentException;

    int numChildren(Position<T> p) throws IllegalArgumentException;

    boolean isInternal(Position<T> p) throws IllegalArgumentException;

    boolean isExternal(Position<T> p) throws IllegalArgumentException;

    boolean isRoot(Position<T> p) throws IllegalArgumentException;

    int size();

    boolean isEmpty();

    Iterator<T> iterator();

    Iterable<Position<T>> positions();
}
