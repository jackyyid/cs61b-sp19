package es.datastructur.synthesizer;


import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    /**
     * Capacity of the buffer.
     */
    private final int capacity;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.rb = (T[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Return the size of the buffer.
     */
    @Override
    public int capacity() {
        return this.capacity;
    }

    /**
     * Return number of items currently in the buffer.
     */
    @Override
    public int fillCount() {
        return this.fillCount;
    }

    /**
     * Return the index incremented by 1, return 0 when reach the end of the bufer.
     */
    private int idxIncrement(int idx) {
        if (idx == capacity - 1) {
            return 0;
        }
        return idx + 1;
    }

    /**
     * Return the index decreased by 1, return {@code capacity - 1} when reach 0.
     */
    private int idxMinusOne(int idx) {
        if (idx == 0) {
            return capacity - 1;
        }
        return idx - 1;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = idxIncrement(last);
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T returnItem = rb[first];
        rb[first] = null;
        first = idxIncrement(first);
        fillCount -= 1;
        return returnItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    /**
     * Return if the buffer equals the given buffer {@code o}.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) { // Null pointer
            return false;
        }
        if (o.getClass() != this.getClass()) { // Not the same class
            return false;
        }
        if (o == this) {
            return true;
        }
        ArrayRingBuffer<T> cmpBuffer = (ArrayRingBuffer<T>) o;
        if (this.fillCount != cmpBuffer.fillCount) {
            return false;
        }
        Iterator<T> curr = this.iterator();
        Iterator<T> cmp = cmpBuffer.iterator();
        while (curr.hasNext()) {
            if (!cmp.next().equals(curr.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Iterator of the ring buffer.
     */
    @Override
    public Iterator<T> iterator() {
        return new ringBufferIterator();
    }

    private class ringBufferIterator implements Iterator<T> {
        /**
         * Current idx of the iteration.
         */
        int idxCurr;
        /**
         * Count of iteration
         */
        int iterCount;

        /**
         * Construct an iterator
         */
        public ringBufferIterator() {
            idxCurr = first;
            iterCount = 0;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         */
        @Override
        public boolean hasNext() {
            return iterCount < fillCount;
        }

        /**
         * Returns the next element in the iteration.
         */
        @Override
        public T next() {
            T returnItem = rb[idxCurr];
            idxCurr = idxIncrement(idxCurr);
            iterCount += 1;
            return returnItem;
        }
    }
}