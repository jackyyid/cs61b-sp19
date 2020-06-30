package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

import static edu.princeton.cs.introcs.StdRandom.uniform;

public class PercolationStats {
    /**
     * Dimension of testing percolation grid.
     */
    private final int dim;
    /**
     * Repeating time of percolation tests.
     */
    private final int repT;
    /**
     * PercolationFactory instance for creating percolation instances.
     */
    private final PercolationFactory pf;
    /**
     * Storing all threshold in T independent tests.
     */
    private double[] thresholds;

    /**
     * Perform T independent experiments on an N-by-N grid.
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be larger than 0.");
        }
        dim = N;
        repT = T;
        this.pf = pf;
        thresholds = new double[T];
        for (int t = 0; t < T; t++) {
            thresholds[t] = percolationTest();
        }
    }

    /**
     * Sample mean of percolation threshold.
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }
    /**
     * Sample standard deviation of percolation threshold.
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }
    /**
     * Low endpoint of 95% confidence interval.
     */
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(repT));
    }
    /**
     * high endpoint of 95% confidence interval.
     */
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(repT));
    }


    /**
     * Independent experiment for percolation.
     * @return Percolation threshold: (Number of open sites) / (Number of total sites).
     */
    private double percolationTest() {
        Percolation pc = pf.make(dim);
        while (!pc.percolates()) {
            int row = StdRandom.uniform(dim);
            int col = StdRandom.uniform(dim);
            if (!pc.isOpen(row, col)) {
                pc.open(row, col);
            }
        }
        return 1.0 * pc.numberOfOpenSites() / (dim * dim);
    }
}
