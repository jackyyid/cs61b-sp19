import org.junit.Test;
import static org.junit.Assert.*;


public class TestOffByN {
    static CharacterComparator cc3 = new OffByN(3);
    static CharacterComparator cc5 = new OffByN(5);

    @Test
    public void testOffByN() {
        // diff == 3
        assertTrue(cc3.equalChars('a','d'));
        assertTrue(cc3.equalChars('t','q'));
        assertFalse(cc3.equalChars('a','a'));
        assertFalse(cc3.equalChars('z','e'));
        assertFalse(cc3.equalChars('a','n'));
        // diff == 5
        assertTrue(cc5.equalChars('a','f'));
        assertTrue(cc5.equalChars('v','q'));
        assertFalse(cc5.equalChars('a','a'));
        assertFalse(cc5.equalChars('e','z'));
        assertFalse(cc5.equalChars('a','n'));
    }


}
