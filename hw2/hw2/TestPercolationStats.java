package hw2;

import org.junit.Test;

/**
 * @author Jacky Qu
 */
public class TestPercolationStats {

    @Test
    public void testPercolationStats() {
        PercolationStats pst = new PercolationStats(10, 10000, new PercolationFactory());
        System.out.println("Mean: " + pst.mean());
        System.out.println("Stddev: " + pst.stddev());
        System.out.println("Confidence Interval: [" + pst.confidenceLow()
                + ", " + pst.confidenceHigh() + "].");
    }
}

