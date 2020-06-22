/**
 * A deque interface defining deque operations.
 */
public interface Deque<T> {
    /**
     * Add an item of type T to the front of the deque.
     */
    public void addFirst(T item);
    /**
     * Add an item of type T to the back of the deque.
     */
    public void addLast(T item);
    /**
     * Return if the deque is empty.
     */
    default boolean isEmpty() {
        return size() == 0;
    };
    /**
     * Return the size of the deque.
     */
    public int size();
    /**
     * Print the deque from first to last.
     */
    public void printDeque();
    /**
     * Removes and returns the item at the front of the deque.
     */
    public T removeFirst();
    /**
     * Removes and returns the item at the last of the deque.
     */
    public T removeLast();
    /**
     * get the item at given index.
     */
    public T get(int index);

}
