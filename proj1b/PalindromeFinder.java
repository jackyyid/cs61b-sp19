import org.junit.Test;

/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    @Test
    public void findOffByZero() {
        int minLength = 4;
        In in = new In("../library-sp19/data/words.txt");
        Palindrome palindrome = new Palindrome();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word)) {
                System.out.println(word);
            }
        }
    }

    @Test
    public void findOffByThree() {
        int minLength = 4;
        int charDiff = 3;
        CharacterComparator cc = new OffByN(charDiff);
        In in = new In("../library-sp19/data/words.txt");
        Palindrome palindrome = new Palindrome();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                System.out.println(word);
            }
        }
    }

    @Test
    public void findOffByFive() {
        int minLength = 4;
        int charDiff = 5;
        CharacterComparator cc = new OffByN(charDiff);
        In in = new In("../library-sp19/data/words.txt");
        Palindrome palindrome = new Palindrome();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                System.out.println(word);
            }
        }
    }
}