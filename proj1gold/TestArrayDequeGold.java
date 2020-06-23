import static org.junit.Assert.*;

import org.junit.Test;

import java.lang.reflect.Array;

/**
 * Test StudentArrayDeque.
 */
public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque0() {
        testAddLast();
        testAddFirst();
        testRemoveLast();
        testRemoveFirst();
    }

    /**
     * Method to test addLast().
     */
    private void testAddLast() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        for (int i = 0; i < 10; i += 1) {
            sad1.addLast(i);
            ads1.addLast(i);
        }
        for (int i = 0; i < 10; i++) {
            Integer actual = sad1.get(i);
            Integer expected = ads1.get(i);
            assertEquals("Ooop! Something wrong with addLast()! Expected: "
                    + expected + ". But got: " + actual + "!", expected, actual);
        }
    }

    /**
     * Method to test addFirst().
     */
    private void testAddFirst() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        for (int i = 0; i < 10; i += 1) {
            sad1.addFirst(i);
            ads1.addFirst(i);
        }
        for (int i = 0; i < 10; i++) {
            Integer actual = sad1.get(i);
            Integer expected = ads1.get(i);
            assertEquals("Ooop! Something wrong with addFirst()! Expected: "
                    + expected + ". But got: " + actual + "!", expected, actual);
        }
    }

    /**
     * Method to test removeLast().
     */
    private void testRemoveLast() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                ads1.addLast(i);
            } else {
                sad1.addFirst(i);
                ads1.addFirst(i);
            }
        }
        for (int i = 0; i < 11; i++) {
            Integer actual = sad1.removeLast();
            Integer expected = ads1.removeLast();
            assertEquals("Ooop! Something wrong with removeLast()! Expected: "
                    + expected + ". But got: " + actual + "!", expected, actual);
        }
    }

    /**
     * Method to test removeFirst().
     */
    private void testRemoveFirst() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                ads1.addLast(i);
            } else {
                sad1.addFirst(i);
                ads1.addFirst(i);
            }
        }
        for (int i = 0; i < 11; i++) {
            Integer actual = sad1.removeFirst();
            Integer expected = ads1.removeFirst();
            assertEquals("Ooop! Something wrong with removeFirst()! Expected: "
                    + expected + ". But got: " + actual + "!", expected, actual);
        }
    }


    static StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
    static ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
    /**
     * Massage contains all operations.
     */
    StringBuilder msg = new StringBuilder("\n");

    @Test
    public void testStudentArrayDequeRandom() {
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                testAddLastRandom();
            } else {
                testAddFirstRandom();
            }
        }
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                testRemoveFirstRandom();
            } else {
                testRemoveLastRandom();
            }
        }
    }

    private void testAddLastRandom() {
        int ranNum = StdRandom.uniform(10);
        sad.addLast(ranNum);
        ads.addLast(ranNum);
        msg.append("addLast(" + ranNum + ")\n");
        assertEquals(msg.toString(), ads.get(ads.size() - 1), sad.get(sad.size() - 1));
    }

    private void testAddFirstRandom() {
        int ranNum = StdRandom.uniform(10);
        sad.addFirst(ranNum);
        ads.addFirst(ranNum);
        msg.append("addFirst(" + ranNum + ")\n");
        assertEquals(msg.toString(), ads.get(0), sad.get(0));
    }

    private void testRemoveLastRandom() {
        Integer actual = sad.removeLast();
        Integer expected = ads.removeLast();
        msg.append("removeLast()\n");
        assertEquals(msg.toString(), expected, actual);
    }

    private void testRemoveFirstRandom() {
        Integer actual = sad.removeFirst();
        Integer expected = ads.removeFirst();
        msg.append("removeFirst()\n");
        assertEquals(msg.toString(), expected, actual);
    }
}
