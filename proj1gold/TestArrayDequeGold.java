import static org.junit.Assert.*;
import org.junit.Test;


public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> aDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> anotherDeque = new ArrayDequeSolution<>();
        // addFist
        for (int i = 0; i < 10; i += 1) {
            int randomNumber = StdRandom.uniform(1000);
            aDeque.addFirst(randomNumber);
            anotherDeque.addFirst(randomNumber);
        }
        for (int i = 0; i < 10; i += 1) {
            int expected = anotherDeque.get(i);
            int actual = aDeque.get(i);
            assertEquals("Oh noooo!\nThis is bad in addFirst:\n   Random number " + actual
                    + " not equal to " + expected + "!", expected, actual);
        }

        // addLast
        for (int i = 0; i < 10; i += 1) {
            int randomNumber = StdRandom.uniform(1000);
            aDeque.addLast(randomNumber);
            anotherDeque.addLast(randomNumber);
        }
        for (int i = 0; i < 20; i += 1) {
            int expected = anotherDeque.get(i);
            int actual = aDeque.get(i);
            assertEquals("Oh noooo!\nThis is bad in addLast:\n   Random number " + actual
                    + " not equal to " + expected + "!", expected, actual);
        }

        // removeFirst
        for (int i = 0; i < 10; i += 1) {
            int actual = anotherDeque.removeFirst();
            int expected = aDeque.removeFirst();
            assertEquals("Oh noooo!\nThis is bad in removeFirst:\n   Random number " + actual
                    + " not equal to " + expected + "!", expected, actual);
        }

        // removeLast
        for (int i = 0; i < 10; i += 1) {
            int actual = anotherDeque.removeLast();
            int expected = aDeque.removeLast();
            assertEquals("Oh noooo!\nThis is bad in removeLast:\n   Random number " + actual
                    + " not equal to " + expected + "!", expected, actual);
        }


    }


}
