import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("aaab"));
        assertTrue(palindrome.isPalindrome("abba"));
        assertTrue((palindrome.isPalindrome("a")));
        assertTrue(palindrome.isPalindrome(" "));
        assertTrue(palindrome.isPalindrome("AAAaAAA"));
        assertTrue(palindrome.isPalindrome("ABBA"));
        assertFalse(palindrome.isPalindrome("Aa"));
        assertFalse(palindrome.isPalindrome("ACDC"));
        assertFalse(palindrome.isPalindrome("yang"));
    }

    @Test
    public void testIsOffByOnePalindrome() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("zyzy", obo));
        assertTrue(palindrome.isPalindrome("yyxz", obo));
        assertTrue(palindrome.isPalindrome("yyyxz", obo));
        assertFalse(palindrome.isPalindrome("aa", obo));
        assertFalse(palindrome.isPalindrome("xyz", obo));
        assertFalse(palindrome.isPalindrome("aa", obo));
        assertFalse(palindrome.isPalindrome("zxzx", obo));
    }
}