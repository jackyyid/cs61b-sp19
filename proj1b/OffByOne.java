public class OffByOne implements CharacterComparator{
    /** Return true if two characters are different exactly by one.*/
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == 1 || diff == -1;
    }
}
