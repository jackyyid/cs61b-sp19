import org.junit.Test;
import static org.junit.Assert.*;


/**
 * @author Jacky Qu
 */
public class TestUnionFind {

    @Test
    public void test1() {
        UnionFind u1 = new UnionFind(5);
        assertFalse(u1.connected(1, 2));
        u1.union(1, 2);
        u1.union(2, 3);
        assertEquals(u1.sizeOf(2), 3);
        assertEquals(u1.find(3), 2);
        assertEquals(u1.find(1), 2);
        u1.union(3, 4);
        assertEquals(u1.parent(4), 2);

    }

    @Test
    public void testCompression() {
        UnionFind u = new UnionFind(16);
        u.union(15, 11);
        u.union(12, 5);
        u.union(15, 12); // 15 -- 11 -- 5     12 -- 5
        u.union(13, 6);
        u.union(7, 1);
        u.union(13, 7);
        u.union(15, 13);



    }
}
