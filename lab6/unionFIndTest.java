import org.junit.Test;
import static org.junit.Assert.*;

public class unionFIndTest {
    @Test
    public void testAll() {
        UnionFind set = new UnionFind(10);
        set.union(0, 1);
        set.union(2, 3);
        set.union(3, 1);
        set.union(4, 5);
        set.union(6, 7);
        set.union(7, 5);
        set.union(7, 3);
        set.union(8, 9);
        assertEquals(8, set.sizeOf(7));
        assertEquals(2, set.sizeOf(8));
        set.union(9, 6);
        assertEquals(10, set.sizeOf(6));
        assertEquals(1, set.find(4));
        assertEquals(1, set.find(6));
        assertEquals(1, set.find(5));
        assertEquals(1, set.find(2));


    }
}
