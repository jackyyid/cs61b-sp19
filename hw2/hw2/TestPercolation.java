package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Jacky Qu
 */
public class TestPercolation {
    @Test
    public void testPerco() {
        Percolation pc = new Percolation(3);
        assertEquals(0, pc.numberOfOpenSites());
        assertFalse(pc.isOpen(2, 2));
        pc.open(2,2);
        assertTrue(pc.isOpen(2, 2));
        assertFalse(pc.isOpen(0 ,0));
        pc.open(0, 0);
        assertTrue(pc.isOpen(0, 0));
        assertFalse(pc.isOpen(1 ,0));
        assertFalse(pc.isFull(1, 0));
        pc.open(1, 0);
        assertTrue(pc.isOpen(1, 0));
        assertTrue(pc.isFull(1, 0));
        assertEquals(3, pc.numberOfOpenSites());

        pc.open(1, 1);
        assertFalse(pc.percolates());
        pc.open(1, 2);
        assertTrue(pc.percolates());

    }
}
