import com.sun.source.tree.Tree;

import javax.swing.tree.TreeCellRenderer;
import java.util.*;

/**
 * @author Jacky Qu
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    /**
     * Size of the map.
     */
    int size;
    /**
     * Creates a root node of the tree.
     */
    TreeNode root;

    /**
     * Creates a tree map.
     */
    BSTMap() {
        this.size = 0;
        this.root = null;
    }

    /**
     * TreeNode each storing one key-value mapping.
     */
    private class TreeNode {
        /**
         * Key of the node.
         */
        K key;
        /**
         * Value of the node.
         */
        V value;
        /**
         * Left child of the node.
         */
        TreeNode left;
        /**
         * Right Child of the node.
         */
        TreeNode right;

        /**
         * Creates a tree node with key, value, left child, right child.
         */
        TreeNode(K key, V value, TreeNode left, TreeNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * Creates a tree node with key and value.
         */
        TreeNode(K key, V value) {
            this(key, value, null, null);
        }
    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Return if the map contains specific key.
     * Utilize private helper function.
     */
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key should not be null.");
        }
        return find(key, root) != null;
    }

    /**
     * Return the value of given key.
     * Utilize private helper function.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key should not be null.");
        }
        TreeNode node = find(key, root);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    /**
     * Return number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associate specific key and value in this map.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key should not be null.");
        }
        TreeNode node = find(key, root);
        if (node == null) {
            root = insert(key, value, root);
            size += 1;
            return;
        }
        node.value = value;
    }

    /**
     * Prints out the BSTMap in order of increasing key.
     */
    public void printInOrder() {
        List<TreeNode> list = storeNodes(root);
        while (!list.isEmpty()) {
            TreeNode node = list.remove(0);
            System.out.println("Key: " + node.key + "  Value: " + node.value);
        }
    }

    /**
     * Return a set containing all keys in the map.
     */
    @Override
    public Set<K> keySet() {
        return storeKeys(root);
    }

    /**
     * Remove and return the tree node with specific key.
     * Return null if the key does not exist in the map.
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key should not be null.");
        }
        TreeNode node = find(key, root);
        if (node == null) {
            return null;
        }
        V returnValue = node.value;
        root = removeNode(key, root);
        return returnValue;
    }

    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key should not be null.");
        }
        TreeNode node = find(key, root);
        if (node == null || node.value != value) {
            return null;
        }
        V returnValue = get(key);
        root = removeNode(key, root);
        return returnValue;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     */
    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    /**
     * Find and return the reference of the node matching the specific key.
     * If no found, return null.
     */
    private TreeNode find(K key, TreeNode node) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        }
        if (cmp < 0) {
            return find(key, node.left);
        } else {
            return find(key, node.right);
        }
    }

    /**
     * Insert a new node in the tree, and return the reference of the new node.
     * Assert no such key in the map.
     */
    private TreeNode insert(K key, V value, TreeNode node) {
        if (node == null) {
            return new TreeNode(key, value);
        }
        int cmp = key.compareTo(node.key);
        assert cmp != 0;
        if (cmp < 0) {
            node.left = insert(key, value, node.left);
        } else {
            node.right = insert(key, value, node.right);
        }
        return node;
    }
    /**
     * Storing TreeNodes in increasing key order. (For Printing)
     */
    private List<TreeNode> storeNodes(TreeNode node) {
        if (node == null) {
            return new LinkedList<>();
        }
        List<TreeNode> list = new LinkedList<>();
        list.add(node);
        list.addAll(storeNodes(node.left));
        list.addAll(storeNodes(node.right));
        return list;
    }
    /**
     * Stores all keys in a set.
     */
    private Set<K> storeKeys(TreeNode node) {
        if (node == null) {
            return new HashSet<>();
        }
        Set<K> set = storeKeys(node.left);
        set.add(node.key);
        set.addAll(storeKeys(node.right));
        return set;
    }
    /**
     * Return a new tree where the specific key has been remove.
     */
    private TreeNode removeNode(K key, TreeNode node) {
        int cmp = key.compareTo(node.key);
        if(cmp == 0) {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                TreeNode tmp = replacementOf(node);
                node.key = tmp.key;
                node.value = tmp.value;
                node.right = removeNode(tmp.key, node.right);
                return node;
            }
        } else if (cmp < 0) {
            node.left = removeNode(key, node.left);
            return node;
        } else {
            node.right = removeNode(key, node.right);
            return node;
        }
    }
    /**
     * Find the replacement value of given TWO-CHILD node.
     */
    private TreeNode replacementOf(TreeNode node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

}
