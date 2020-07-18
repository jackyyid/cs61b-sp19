import java.util.*;

/**
 * @author Jacky Qu
 * An implementation of Hashmap.
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Hash table contains all list of entries.
     */
    private LinkedList<Entry>[] hashTable;
    /**
     * Number of key-value mappings in the HashMap
     */
    private int size;
    /**
     * Load Factor of the hash table.
     */
    private final double loadFactor;
    /**
     * Length of the hash table.
     */
    private int tableLength;
    /**
     * Length of longest list in the hash table.
     */
    private int longestLength;
    /**
     * Set containing all the keys.
     */
    private Set<K> keySet;

    /**
     * Create a hash map with given initial size and load factor.
     */
    public MyHashMap(int initialSize, double loadFactor) {
        this.tableLength = initialSize;
        this.loadFactor = loadFactor;
        size = 0;
        longestLength = 0;
        keySet = new HashSet<>();
        hashTable = new LinkedList[tableLength];
    }
    /**
     * Create a hash map with given initial size.
     * Default load factor will be 0.75.
     */
    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }
    /**
     * Create a hash map.
     * Default initialSize will be 16, load factor 0.75.
     */
    public MyHashMap() {
        this(16, 0.75);
    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        hashTable = new LinkedList[size];
        size = 0;
        keySet = new HashSet<>();
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key should not be null.");
        }
        return keySet().contains(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key should not be null.");
        }
        if (containsKey(key)) {
            LinkedList<Entry> list = getHashcodeList(key.hashCode());
            for (Entry entry : list) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key should not be null.");
        }
        // Key exists in current map.
        if (containsKey(key)) {
            LinkedList<Entry> list = getHashcodeList(key.hashCode());
            for (Entry entry : list) {
                if (entry.key.equals(key)) {
                    entry.value = value; // update value.
                    return;
                }
            }
        }
        // Key does not exist.
        LinkedList<Entry> list = getHashcodeList(key.hashCode());
        if (list == null) {
            hashTable[hashcodeToIndex(key.hashCode())] = new LinkedList<>();
            list = getHashcodeList(key.hashCode());
        }
        if ((list.size() + 1) * 1.0 / size >= loadFactor) {
            resizing();
            list = getHashcodeList(key.hashCode());
            if (list == null) {
                hashTable[hashcodeToIndex(key.hashCode())] = new LinkedList<>();
                list = getHashcodeList(key.hashCode());
            }
        }
        list.add(new Entry(key, value));
        size += 1;
        keySet.add(key);
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Remove operation unsupported.");
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Remove operation unsupported.");
    }

    /**
     * Returns an iterator over all stored keys.
     */
    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }

    /**
     * Represents one entry in the hash table.
     */
    private class Entry {
        /** Key of the entry. */
        K key;
        /** Value of the entry. */
        V value;
        /** Create an entry with specific key and value.*/
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Transform from given hashcode to index in the hash table.
     */
    private int hashcodeToIndex(int hashcode) {
        return Math.floorMod(hashcode, tableLength);
    }
    /**
     * Return the list representing specific hashcode
     */
    private LinkedList<Entry> getHashcodeList(int hashcode) {
        return hashTable[hashcodeToIndex(hashcode)];
    }
    /**
     * Resize the hash table to be double length.
     */
    private void resizing() {
        tableLength = tableLength * 2;
        LinkedList<Entry>[] oldTable = hashTable;
        hashTable = new LinkedList[tableLength];
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null) continue;
            for(Entry entry : oldTable[i]) {
                resizingPut(entry.key, entry.value);
            }
        }
    }
    /**
     * Put operation for resizing.
     */
    private void resizingPut(K key, V value) {
        LinkedList<Entry> list = getHashcodeList(key.hashCode());
        if (list == null) {
            hashTable[hashcodeToIndex(key.hashCode())] = new LinkedList<>();
            list = getHashcodeList(key.hashCode());
        }
        list.add(new Entry(key, value));
    }

}
