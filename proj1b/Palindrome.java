public class Palindrome {

    /** Returns a Deque where the characters appear in the same order as in the String. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> L = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            L.addLast(word.charAt(i));
        }
        return L;
    }

    /** Returns true if the word is a palindrome, false otherwise. */
    private boolean isPalindrome(Deque<Character> Adeque) {
        if (Adeque.size() == 0 || Adeque.size() == 1) {
            return true;
        }
        return Adeque.removeFirst() == Adeque.removeLast() && isPalindrome(Adeque);
    }

    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    /** Returns true if the word is an off by one palindrome, false otherwise. */
    private boolean isPalindrome(Deque<Character> Adeque, CharacterComparator cc) {
        if (Adeque.size() == 0 || Adeque.size() == 1) {
            return true;
        }
        return cc.equalChars(Adeque.removeFirst(), Adeque.removeLast()) && isPalindrome(Adeque, cc);
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }
}
