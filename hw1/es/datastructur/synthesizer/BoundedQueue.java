package es.datastructur.synthesizer;

import java.util.Iterator;

/**
 * @author Jacky Qu
 * Implementation of bounded queue.
 */
public interface BoundedQueue<T> extends Iterable<T> {
    /**
     * Return the size of the buffer.
     */
    public int capacity();
    /**
     * Return number of items currently in the buffer.
     */
    public int fillCount();
    /**
     * Add item x to the end.
     */
    public void enqueue(T x);
    /**
     * Delete and return item from the front.
     */
    public T dequeue();
    /**
     * Return (but not delete) the item from the front.
     */
    public T peek();
    /**
     * Return the iterator of the buffer.
     */
    public Iterator<T> iterator();
    /**
     * Return if the buffer is empty.
     */
    default public boolean isEmpty() {
        return fillCount() == 0;
    }
    /**
     * Return if the buffer is full.
     */
    default public boolean isFull() {
        return fillCount() == capacity();
    }
}
