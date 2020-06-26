package es.datastructur.synthesizer;
import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(10);
        assertTrue(arb.isEmpty());
        assertEquals(10, arb.capacity());
        arb.enqueue(32.23);
        arb.enqueue(44.18);
        arb.enqueue(62.18);
        arb.enqueue(100.18);
        // 32.23 44.18 62.18 100.18
        assertEquals(4, arb.fillCount());
        Double expected = 32.23;
        assertEquals(expected, arb.dequeue());
        expected = 44.18;
        assertEquals(expected, arb.dequeue());
        // 62.18 100.18
        arb.enqueue(32.23);
        arb.enqueue(44.18);
        arb.enqueue(62.18);
        arb.enqueue(100.18);
        // 62.18 100.18 32.23 44.18 62.18 100.18
        expected = 62.18;
        assertEquals(expected, arb.peek());
        arb.enqueue(32.23);
        arb.enqueue(44.18);
        arb.enqueue(62.18);
        assertEquals(9, arb.fillCount());
        // 62.18 100.18 32.23 44.18 62.18 100.18 32.23 44.18 62.18
        arb.enqueue(62.18);
        assertTrue(arb.isFull());
        // 62.18 100.18 32.23 44.18 62.18 100.18 32.23 44.18 62.18 62.18  (Full)
        expected = 62.18;
        assertEquals(expected, arb.dequeue());
        expected = 100.18;
        assertEquals(expected, arb.dequeue());
        expected = 32.23;
        assertEquals(expected, arb.dequeue());
        expected = 44.18;
        assertEquals(expected, arb.dequeue());
        expected = 62.18;
        assertEquals(expected, arb.dequeue());
        expected = 100.18;
        assertEquals(expected, arb.dequeue());
        expected = 32.23;
        assertEquals(expected, arb.dequeue());
        expected = 44.18;
        assertEquals(expected, arb.dequeue());
        expected = 62.18;
        assertEquals(expected, arb.dequeue());
        expected = 62.18;
        assertEquals(expected, arb.dequeue());
        assertTrue(arb.isEmpty());
    }

    @Test
    public void testString() {
        ArrayRingBuffer<String> strAry = new ArrayRingBuffer<>(5);
        strAry.enqueue("hihi");
        strAry.enqueue("hihi");
        strAry.enqueue("hihi");

    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Double> s1 = new ArrayRingBuffer<>(5);
        s1.enqueue(35.23);
        s1.enqueue(35.23);
        s1.enqueue(32.23);
        s1.dequeue();
        s1.dequeue();
        // 32.23
        s1.enqueue(55.32);
        s1.enqueue(34.23);
        s1.enqueue(353.23);
        s1.enqueue(3511.23);
        // 32.23 55.32 34.23 353.23 3511.23
        Double[] cmp = new Double[]{32.23, 55.32, 34.23, 353.23, 3511.23};
        int idx = 0;
        for (Double item : s1) {
            assertEquals(cmp[idx], item);
            idx++;
        }
    }


    @Test
    public void testEquals() {
        ArrayRingBuffer<Double> s1 = new ArrayRingBuffer<>(5);
        ArrayRingBuffer<Double> s2 = new ArrayRingBuffer<>(5);
        assertFalse(s1.equals(null));
        s1.enqueue(35.23);
        s1.enqueue(32.23);
        s1.enqueue(34.23);
        s1.enqueue(353.23);
        s1.enqueue(3511.23);
        s2.enqueue(35.23);
        s2.enqueue(32.23);
        s2.enqueue(34.23);
        s2.enqueue(353.23);
        s2.enqueue(3511.23);
        assertTrue(s1.equals(s2));
        s2.dequeue();
        s2.enqueue(1.1);
        assertFalse(s1.equals(s2));

    }
}
