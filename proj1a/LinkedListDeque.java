/** Double-Ended Queue in SLList Form.
 * @param <T> Type of input item.
 */
public class LinkedListDeque<T> {
    /**
     * Sentinel: First item in this Double-ended Queue, if exists, is sentinel.next.
     */
    private LinkedNode sentinel;
    private int size;
    /**
     * Basic class LinkedNode.
     */
    private class LinkedNode {
        private T item;
        private LinkedNode next, prev;
        /**
         * Construct a basic linked list.
         * @param item Initial item.
         * @param nextLinkedNode Next linked list.
         * @param prevLinkedNode Previous linked list.
         */
        private LinkedNode(T item, LinkedNode nextLinkedNode, LinkedNode prevLinkedNode) {
            this.item = item;
            this.next = nextLinkedNode;
            this.prev = prevLinkedNode;
        }
    }

    /**
     * Construct a deque.
     */
    public LinkedListDeque() {
        sentinel = new LinkedNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Construct a deque with one item.
     */
    public LinkedListDeque(T item) {
        this();
        sentinel.next = new LinkedNode(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size += 1;
    }

    /**
     * Construct a deque by creating a deep copy of other.
     * @param other the deque to be copied.
     */
    public LinkedListDeque(LinkedListDeque other) {
        this();
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }

    }

    /**
     * Add item of type T at the front of deque.
     */
    public void addFirst(T item) {
        sentinel.next = new LinkedNode(item, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }
    /**
     * Add item of type T at the back of deque.
     */
    public void addLast(T item) {
        sentinel.prev = new LinkedNode(item, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /**
     * Return whether the deque is empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Return the size of deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints all item in deque from first to last separated by a space.
     */
    public void printDeque() {
        if (size == 0) {
            System.out.println();
        }
        LinkedNode p = sentinel.next;
        while (p.next != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println(p.item);
    }

    /**
     * Remove and return the item at the front of deque.
     * @return null if deque is empty, otherwise return item at the front.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T tmp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return tmp;
    }

    /**
     * Remove and return the item at the last of deque.
     * @return null if deque is empty, otherwise return item at the last.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T tmp = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return tmp;
    }

    /**
     * Get the item at given index using iteration.
     * @return null if out of range, otherwise return item at given index.
     */
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        LinkedNode p = sentinel.next;
        while (index != 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }
    /**
     * Recursive Helper function for getRecursive().
     */
    private T getRecursive(int index, LinkedNode p) {
        if (index >= size) {
            return null;
        }
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index - 1, p.next);
    }

    /**
     * Get the item at given index using Recursion.
     */
    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

}
