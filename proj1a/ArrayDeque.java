/**
 * Double-ended Queue in Array form.
 * @param <T> Data type of items in the queue.
 */
public class ArrayDeque<T> {
    private T[] items;
    private int size, nextFirst, nextLast;

    /**
     * Create an empty array deque.
     * Index of first item is plusOne(nextFirst).
     * Index of last item is minusOne(nextLast).
     */
    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    /**
     * Create an array deque with a deep copy of other.
     * @param other A given array deque to be copied.
     */
    public ArrayDeque(ArrayDeque other) {
        this();
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    /**
     * Doing minus one operation of given index.
     */

    private int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    /**
     * Doing plus one operation of given index.
     */
    private int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        }
        return index + 1;
    }

    /**
     * Return the array index of first item.
     */
    private int indexFirst() {
        return plusOne(nextFirst);
    }

    /**
     * Return the array index of last item.
     */
    private int indexLast() {
        return minusOne(nextLast);
    }

    /**
     * Resize the array deque with given capacity.
     * @param capacity Capacity of new array deque.
     */
    private void resize(int capacity) {
        T[] newItems = (T []) new Object[capacity];
        int firstPasteIndex = ((capacity - size) / 2);
        // Copied items from old array to new array.
        if (indexFirst() < indexLast()) {
            System.arraycopy(items, indexFirst(), newItems, firstPasteIndex, size);
        } else {
            System.arraycopy(items, indexFirst(), newItems, firstPasteIndex, items.length - indexFirst());
            int secondPasteIndex = firstPasteIndex + items.length - indexFirst();
            System.arraycopy(items, 0, newItems, secondPasteIndex, indexLast() + 1);
        }
        items = newItems;
        nextFirst = firstPasteIndex - 1;
        nextLast = firstPasteIndex + size;
    }

    /**
     * Add an item at the front of deque.
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    /**
     * Add an item at the last of deque.
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = plusOne(nextLast);
    }

    /**
     * Return whether the Arraydeque is empty or not.
     * @return True if empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return size of the array deque.
     */
    public int size() {
        return size;
    }

    /**
     * Print the items in deque from first to last.
     */
    public void printDeque() {
        if (size == 0) {
            System.out.println();
        }
        int tmpIndex = indexFirst();
        for (int i = 0; i < size - 1; i++) {
            System.out.print(items[tmpIndex] + " ");
            tmpIndex = plusOne(tmpIndex);
        }
        System.out.println(items[tmpIndex]);
    }

    /**
     * Remove and return the first item of the array deque.
     * @return The first item of array deque.
     */
    public T removeFirst() {
        nextFirst = indexFirst();
        T tmpItem = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        double usageFactor = (size * 1.0) / items.length;
        if (items.length >= 16 && usageFactor < 0.25) {
            resize(items.length / 2);
        }
        return tmpItem;
    }

    /**
     * Remove and return the last item of the array deque.
     * @return The last item of the array deque.
     */
    public T removeLast() {
        nextLast = indexLast();
        T tmpItem = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        double usageFactor = (size * 1.0) / items.length;
        if (items.length >= 16 && usageFactor < 0.25) {
            resize(items.length / 2);
        }
        return tmpItem;
    }

    /**
     * Return the actual array index of given index.
     * @param index Index of deque.
     * @return Actual index of array.
     */
    private int findArrayIndex(int index) {
        return (index + indexFirst()) % items.length;
    }
    /**
     * Get the item at given index.
     */
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return items[findArrayIndex(index)];
    }

}
