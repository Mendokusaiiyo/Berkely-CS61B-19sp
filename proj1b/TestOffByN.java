import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    CharacterComparator offBy2 = new OffByN(2);
    CharacterComparator offBy20 = new OffByN(20);
    CharacterComparator offBy0 = new OffByN(0);
    @Test
    public void testEqualChars() {
        assertTrue(offBy2.equalChars('b', 'd'));
        assertTrue(offBy2.equalChars('z', 'x'));
        assertFalse(offBy2.equalChars('z', 'a'));
        assertTrue(offBy20.equalChars('c', 'w'));
        assertTrue(offBy20.equalChars('y', 'e'));
        assertFalse(offBy20.equalChars('a', 'z'));
        assertTrue(offBy0.equalChars('a', 'a'));
        assertFalse(offBy0.equalChars('A', 'a'));


    }
}
