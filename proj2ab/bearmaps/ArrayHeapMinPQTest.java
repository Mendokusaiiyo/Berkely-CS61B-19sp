package bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Test
    public void pqTest() {
    ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
    pq.add("cxk1", 1);
    pq.add("cxk2", 2);
    pq.add("cxk3", 3);
    pq.add("cxk4", 4);
    pq.add("cxk5", 5);
    pq.add("cxk6", 6);
    assertEquals(6, pq.size());
    pq.add("cxk", 0.5);
    pq.add("qbl", 1);
    assertTrue(pq.contains("cxk3"));
    assertEquals( "cxk", pq.getSmallest());
    pq.removeSmallest();
    assertEquals("cxk1", pq.getSmallest());
    pq.changePriority("cxk1", 5);
    assertEquals("qbl", pq.getSmallest());
    assertEquals(7, pq.size());
    pq.removeSmallest();
    assertEquals("cxk2", pq.getSmallest());
    pq.changePriority("cxk6", 1000);
    pq.removeSmallest();
    pq.removeSmallest();
    pq.removeSmallest();
    pq.removeSmallest();
    pq.removeSmallest();
    assertEquals("cxk6", pq.getSmallest());
    }

    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 10000; i += 1) {
            pq.add(i, i + 1);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Total add() time elapsed: " + (end1 - start1) / 1000.0 + " seconds");
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i += 1) {
            pq.changePriority(i, i + 2);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total changePriority() time elapsed: " + (end2 - start2) / 1000.0 + " seconds");
    }
}
