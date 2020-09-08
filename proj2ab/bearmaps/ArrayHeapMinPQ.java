package bearmaps;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    /**
     * Storing value of the queue.
     */
    private Node[] keys;

    /**
     * Size of the array.
     */
    private int size;
    /**
     * Storing all the keys in a set.
     */
    private final Map<T, Integer> keyMap = new HashMap<>();
    /**
     * Create a MinPQ with initial size 16.
     */
    public ArrayHeapMinPQ() {
        int initialSize = 16;
        keys = new ArrayHeapMinPQ.Node[initialSize];
        size = 0;
    }

    /**
     * Representation of a minPQ node with key and priority.
     */
    private class Node {
        T key;
        double priority;
        Node(T key, double priority) {
            this.key = key;
            this.priority = priority;
        }
    }

    /**
     * Adds an item of type {@code T} with the given priority.
     */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Item have already existed.");
        }
        if (size + 1 >= keys.length) {
            resize();
        }
        keys[size + 1] = new Node(item, priority);
        keyMap.put(item, size + 1);
        size += 1;
        swim(size);
    }

    /**
     * Returns true if the PQ contains the given item
     */
    @Override
    public boolean contains(T item) {
        return keyMap.containsKey(item);
    }

    /**
     * Return item with smallest priority.
     */
    @Override
    public T getSmallest() {
        return keys[1].key;
    }

    /**
     * Remove and return the item with smallest priority.
     */
    @Override
    public T removeSmallest() {
        Node returnItem = keys[1];
        swap(1, size);
        keyMap.put(keys[1].key, 1);
        keyMap.remove(returnItem.key);
        keys[size] = null;
        size -= 1;
        if ((size * 1.0 / keys.length) < 0.25 )
            resizeDown();
        sink(1);
        return returnItem.key;
    }

    /**
     * Return the number of items.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Sets the priority of the given item to the given value.
     */
    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("Item does not exist.");
        }
        int idx = keyMap.get(item);
        double oldPriority = keys[idx].priority;
        keys[idx].priority = priority;
        if (priority < oldPriority) {
            swim(idx);
        } else {
            sink(idx);
        }
    }

    /**
     * Resize when the array is full.
     */
    private void resize() {
        if (size + 1 >= keys.length) {
            Node[] tmp =  new ArrayHeapMinPQ.Node[keys.length * 2];
            System.arraycopy(keys, 1, tmp, 1, size);
            keys = tmp;
        }
    }
    /**
     * Resize when the array is less than 1/4.
     */
    private void resizeDown() {
        if ((size * 1.0 / keys.length ) < 0.25) {
            Node[] tmp = new ArrayHeapMinPQ.Node[keys.length / 2];
            System.arraycopy(keys, 1, tmp, 1, size);
            keys = tmp;
        }
    }
    /**
     * Return true if it is root.
     */
    private boolean isRoot(int index) {
        return index == 1;
    }
    /**
     * Return the idx of parent node.
     */
    private int parent(int index) {
        if (isRoot(index)) {
            throw new IndexOutOfBoundsException("Root node has not parent.");
        } // root
        return index / 2;
    }
    /**
     * Return the index of left child.
     */
    private int leftChild(int index) {
        return index * 2;
    }
    /**
     * Return the index of right child.
     */
    private int rightChild(int index) {
        return index * 2 + 1;
    }
    /**
     * Rearrange when adding an item.
     */
    private void swim(int index) {
        if (isRoot(index)) {
            return;
        }
        if (keys[parent(index)].priority > keys[index].priority) {
            swap(parent(index), index);
            swim(parent(index));
        }
    }
    /**
     * Switch item of two given indices.
     */
    private void swap(int index1, int index2) {
        Node tmp = keys[index1];
        keys[index1] = keys[index2];
        keys[index2] = tmp;
        keyMap.put(keys[index1].key, index1);
        keyMap.put(keys[index2].key, index2);

    }
    /**
     * Rearrange from top when removing items
     */
    private void sink(int index) {
        if ( index * 2 >= keys.length) return;
        if (keys[leftChild(index)] == null && keys[rightChild(index)] == null) return;
        if (keys[rightChild(index)] == null) {
            if (keys[leftChild(index)].priority < keys[index].priority) {
                swap(index, leftChild(index));
                sink(leftChild(index));
            }
            return;
        }
        if (keys[index].priority > keys[leftChild(index)].priority
                || keys[index].priority > keys[rightChild(index)].priority) {
            if (keys[rightChild(index)].priority < keys[leftChild(index)].priority) {
                swap(index, rightChild(index));
                sink(rightChild(index));
            } else {
                swap(index, leftChild(index));
                sink(leftChild(index));
            }
        }
    }

}
