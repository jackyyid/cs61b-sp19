import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Implementation of a Tries.
 * @author Junjie Qu
 */
public class MyTrieSet implements TrieSet61B {

    private Node root;

    public MyTrieSet() {
        root = new Node('\0', false);
    }

    /**
     * Clears all items out of Trie.
     */
    @Override
    public void clear() {
        root.links = new HashMap<>();
    }

    /**
     * Returns true if the Trie contains KEY, false otherwise
     */
    @Override
    public boolean contains(String key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        return contains(root.links.get(key.charAt(0)), key, 0);
    }

    private boolean contains(Node node, String key, int idx) {
        if (node == null) return false;
        if ((idx + 1) == key.length()) {
            return node.isKey;
        }
        return contains(node.links.get(key.charAt(idx + 1)), key, idx + 1);
    }

    /**
     * Inserts string KEY into Trie
     */
    @Override
    public void add(String key) {
        if (key == null) throw new IllegalArgumentException("Key should not be null.");
        if (contains(key)) return;
        Node link = root.links.get(key.charAt(0));
        root.links.put(key.charAt(0), add(link, key, 0));
    }

    private Node add(Node link, String key, int idx) {
        Node returnNode = new Node(key.charAt(idx), key.length() == (idx + 1));
        if (link != null) {
            returnNode.links = link.links;
        }
        if ((idx + 1) != key.length()) {
            Node subLink = returnNode.links.get(key.charAt(idx + 1));
            returnNode.links.put(key.charAt(idx + 1), add(subLink, key, idx + 1));
        }
        return returnNode;
    }

    /**
     * Returns a list of all words that start with PREFIX
     */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n ; i++) {
            curr = curr.links.get(prefix.charAt(i));
            if (curr == null) {
                return null;
            }
        }
        return collect(curr, prefix);
    }

    List<String> collcet() {
        return collect(root, "");
    }

    private List<String> collect(Node node, String prefix) {
        Collection<Node> links = node.links.values();
        List<String> returnList = new ArrayList<>();
        if (node.isKey) {
            returnList.add(prefix);
        }
        for (Node n : links) {
            String newStr = prefix + n.ch;
            if (n.isKey) {
                returnList.add(newStr);
            }
            returnList.addAll(collect(n, newStr));
        }
        return returnList;
    }

    /**
     * Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    /**
     * Representation of a single node in the trie.
     */
    private static class Node {
        char ch;
        boolean isKey;
        HashMap<Character, Node> links;

        Node(char nodeChar, boolean isKey) {
            this.ch = nodeChar;
            this.isKey = isKey;
            links = new HashMap<>();
        }
    }
}
