/** This class compare two characters with given difference. */
public class OffByN implements CharacterComparator {
    private final int charDiff;
    /** Constructor with given character difference diff. */
    public OffByN(int diff) {
        charDiff = diff;
    }
    /** Return true if two characters are different exactly by one.*/
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return (diff == charDiff) || (diff == -charDiff);
    }
}
