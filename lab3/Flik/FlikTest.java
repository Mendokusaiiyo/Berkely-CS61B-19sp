import org.junit.Test;
import static org.junit.Assert.*;
public class FlikTest {
	@Test
	public void flikTest() {
		int a = 128;
		int b = 128;
		boolean expected = true;
		boolean actual = Flik.isSameNumber(a, b);
		assertTrue(expected == actual);
	}
}