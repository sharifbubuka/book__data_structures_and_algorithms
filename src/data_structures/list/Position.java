package data_structures.list;

public interface Position<T> {
    /**
     * Returns the element stored at position
     * @return the stored element
     * @throws IllegalStateException if position is no longer valid
     */
    T getElement() throws IllegalStateException;
}
