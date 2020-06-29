import java.util.Arrays;
import java.util.Stack;

public class UnionFind {

    // TODO - Add instance variables?
    /** Store parents of all vertices. */
    private final int[] parents;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parents = new int[n];
        // initialized with -1 (root).
        Arrays.fill(parents, -1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= parents.length) {
            throw new IllegalArgumentException("Invalid vertex.");
        }
    }
    /* Return if v1 is root of the tree. */
    private boolean isRoot(int v1) {
        validate(v1);
        return parents[v1] < 0;
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        if (isRoot(v1)) {
            return -parents[v1];
        }
        return sizeOf(parent(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return parents[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (connected(v1, v2)) {
            return;
        }
        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);
        if (size1 > size2) {
            int tmp = v1;
            v1 = v2;
            v2 = tmp;
            size1 = size2;
        }
        int root1 = find(v1);
        int root2 = find(v2);
        parents[root1] = root2;
        parents[root2] -= size1;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        if (isRoot(vertex)) {
            return vertex;
        }
        if (isRoot(parent(vertex))) {
            return parent(vertex);
        }
        return findCompression(vertex);
    }

    /* Return the root of set V while doing path-compression. */
    private int findCompression(int vertex) {
        Stack<Integer> popped = new Stack<>();
        while (!isRoot(vertex)) {
            popped.push(vertex);
            vertex = parent(vertex);
        }
        int root = vertex;
        while (!popped.isEmpty()) {
            parents[popped.pop()] = root;
        }
        return root;
    }

}
