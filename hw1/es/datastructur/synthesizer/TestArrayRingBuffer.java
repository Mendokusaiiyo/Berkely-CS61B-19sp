package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(5);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        assertTrue(arb.isFull());
        arb.dequeue();
        ArrayRingBuffer other = new ArrayRingBuffer(5);
        other.enqueue(2);
        other.enqueue(3);
        other.enqueue(4);
        other.enqueue(5);
        assertTrue(other.equals(arb));
        assertFalse(arb.isFull());
        arb.enqueue(6);
        assertEquals(2, arb.peek());
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.enqueue(7);
        assertEquals(6, arb.peek());
    }
}
