
public class BubbleGrid {
    /**
     * Grid storing balloons positions.
     */
    private final int[][] grid;
    /**
     * Union Find instance for connection.
     */
    private UnionFind uf;
    /**
     * Total number of columns in grid.
     */
    private final int colNum;
    /**
     * Total number of rows in grid.
     */
    private final int rowNum;
    /** Index of Ceiling. */
    private final int TOP = 0;
    /** Four direction to search: TOP, LEFT, RIGHT, BOTTOM. */
    private final int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        this.rowNum = grid.length;
        this.colNum = grid[0].length;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        int dartCount = 0;
        int[] returnCount = new int[darts.length];
        // Throw a null dart : count when no dart is thrown.
        int[] nullDart = {rowNum + 1, colNum + 1};
        uf = unionNeigh(nullDart);
        int bubbleNoDart = uf.sizeOf(TOP);
        for (int[] dart : darts) {
            uf = unionNeigh(dart);
            // Number of balloons connected to the ceiling after a dart is thrown.
            int bubbleDarted = uf.sizeOf(TOP);
            int dartFall = bubbleNoDart - bubbleDarted;
            // dartFall = 1 || dartFall = 0, only the bubble being darted has been eliminated.
            // no bubbles fall.
            if (dartFall < 2) {
                returnCount[dartCount] = 0;
            }
            returnCount[dartCount] = dartFall - 1; // minus the bubbles being eliminated by dart.
            dartCount += 1;
        }
        return returnCount;
    }

    /**
     * Create a union find for given grid.
     */
    private UnionFind unionNeigh(int[] dart) {
        uf = new UnionFind(colNum * rowNum + 1);
        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                unionNeigh(uf, row, col, dart);
            }
        }
        return uf;
    }

    /**
     * Helper function for creating Union Find (with DART).
     */
    private void unionNeigh(UnionFind uf, int row, int col, int[] dart) {
        if (grid[row][col] != 1) {
            return; // Do not have a balloon in this grid.
        }
        if (isDart(row, col, dart)) {
            return; // If dart is thrown, no balloon in this place.
        }
        if (row == 0) {
            uf.union(idxToUf(row, col), TOP); // Topmost elements always stuck.
        }
        for (int[] dir : dirs) {
            int neighRow = row + dir[0];
            int neighCol = col + dir[1];
            if (neighCol < 0 || neighRow < 0 || neighCol >= colNum || neighRow >= rowNum) {
                continue; // Out of index.
            }
            if (isDart(neighRow, neighCol, dart)) {
                continue;
            }
            if (grid[neighRow][neighCol] == 1) {
                uf.union(idxToUf(neighRow,neighCol), idxToUf(row, col));
            }
        }
    }
    /** Return if dart is thrown in this index. */
    private boolean isDart(int row, int col, int[] dart) {
        return row == dart[0] && col == dart[1];
    }
    /**
     * Return index in UnionFind given row and column index.
     */
    private int idxToUf(int row, int col) {
        return row * colNum + col + 1;
    }
}
