/**
 * A class for palindrome operations.
 */
public class Palindrome {
    /**
     * Return a Deque where characters appear in same order as {@code word}.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> returnDeque = new LinkedListDeque<Character>();
        if (word == null) {
            return returnDeque;
        }
        for (int i = 0; i < word.length(); i++) {
            returnDeque.addLast(word.charAt(i));
        }
        return returnDeque;
    }
    /**
     * Return {@code true} if the given word is palindrome.
     */
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque);
    }
    /**
     * Helper method returning true if a deque is palindrome.
     */
    private boolean isPalindrome(Deque deque) {
        if (deque.size() < 2) {
            return true;
        }
        char char1 = (char) deque.removeFirst();
        char char2 = (char) deque.removeLast();
        if (char1 == char2) {
            return isPalindrome(deque);
        }
        return false;
    }
    /**
     * Return {@code true} if the given word is palindrome according to
     * the character comparison test provided by {@code CharacterComparator}
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque, cc);
    }
    /**
     * Helper method returning true if a deque is palindrome,
     * with character comparison test {@code CharacterComparator.}
     */
    private boolean isPalindrome(Deque deque, CharacterComparator cc) {
        if (deque.size() < 2) {
            return true;
        }
        char char1 = (char) deque.removeFirst();
        char char2 = (char) deque.removeLast();
        if (cc.equalChars(char1, char2)) {
            return isPalindrome(deque, cc);
        }
        return false;
    }

}
