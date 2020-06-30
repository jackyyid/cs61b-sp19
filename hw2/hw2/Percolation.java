package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {
    /**
     * A grid tracking all sites either blocked(0) or opened(1).
     */
    private final int[][] grid;
    /**
     * UnionFind instance recording all connection.
     */
    private final WeightedQuickUnionUF uf;
    /**
     * Bound of indices of the gird.
     */
    private final int IDXBOUND;
    /**
     * Block site represented by 0.
     */
    private final int BLOCKED = 0;
    /**
     * Opened site represented by 1.
     */
    private final int OPENED = 1;
    /**
     * Index of virtual top in UnionFind.
     */
    private final int TOP = 0;
    /**
     * Index of virtual bottom in UnionFind.
     */
    private final int BOTTOM = 1;
    /**
     * Store four direction for searching neighbours: TOP, LEFT, RIGHT, BOTTOM.
     */
    private final int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    /**
     * Number of open sites.
     */
    private int numOfOpenSite = 0;


    /**
     * Create N-by-N grid, with all sites initially blocked.
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be larger than 0.");
        }
        grid = new int[N][N];
        IDXBOUND = N;
        for (int i = 0; i < N; i++) {
            Arrays.fill(grid[i], BLOCKED); // All initially blocked(0).
        }
        uf = new WeightedQuickUnionUF(N * N + 2);
    }

    /**
     * Open the site (row, col) if it is not opened already.
     */
    public void open(int row, int col) {
        idxValidate(row, col);
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = OPENED;
        unionNeigh(row, col);
        numOfOpenSite += 1;
    }
    /**
     * Return if the site (row, col) is opened.
     */
    public boolean isOpen(int row, int col) {
        idxValidate(row, col);
        return grid[row][col] == OPENED;
    }
    /**
     * Return if the site (row, col) is full.
     */
    public boolean isFull(int row, int col) {
        idxValidate(row, col);
        return uf.connected(xyTo1D(row, col), TOP);
    }
    /**
     * Return number of open sites.
     */
    public int numberOfOpenSites() {
        return numOfOpenSite;
    }
    /**
     * Return if the system percolates.
     */
    public boolean percolates() {
        return uf.connected(TOP, BOTTOM);
    }

    /**
     * Validate the given index.
     */
    private void idxValidate(int row, int col) {
        if (row >= IDXBOUND || col >= IDXBOUND || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException("Index must between 0 and " + (IDXBOUND - 1) + ".");
        }
    }
    /**
     * Union the given site with neighbour opened site.
     */
    private void unionNeigh(int row, int col) {
        if (row == 0) {
            uf.union(xyTo1D(row, col), TOP);
        }
        if (row == IDXBOUND - 1) {
            uf.union(xyTo1D(row, col), BOTTOM);
        }
        for(int[] dir : dirs) {
            int neighRow = row + dir[0];
            int neighCol = col + dir[1];
            try{
                if (isOpen(neighRow, neighCol)) {
                    uf.union(xyTo1D(neighRow, neighCol), xyTo1D(row, col));
                }
            }
            catch(IndexOutOfBoundsException ignored){}
        }
    }
    /**
     * Convert the x-y index in grid to 1 dimension index in UnionFind.
     */
    private int xyTo1D(int row, int col) {
        return row * IDXBOUND + col + 2;
    }
    /**
     * Main class for testing.
     */
    public static void main(String[] args) {
    }
}
