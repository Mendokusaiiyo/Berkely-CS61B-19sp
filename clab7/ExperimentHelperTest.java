import org.junit.Test;
import static org.junit.Assert.*;

public class ExperimentHelperTest {
    @Test
    public void optimalIPLTest() {
        assertEquals(0, ExperimentHelper.optimalIPL(1));
        assertEquals(1, ExperimentHelper.optimalIPL(2));
        assertEquals(2, ExperimentHelper.optimalIPL(3));
        assertEquals(4, ExperimentHelper.optimalIPL(4));
        assertEquals(6, ExperimentHelper.optimalIPL(5));
        assertEquals(8, ExperimentHelper.optimalIPL(6));
        assertEquals(10, ExperimentHelper.optimalIPL(7));
        assertEquals(13, ExperimentHelper.optimalIPL(8));
    }

    @Test
    public void optimalAverageDepthTest() {
        assertEquals(0, ExperimentHelper.optimalAverageDepth(1), 0.01);
        assertEquals(1.2, ExperimentHelper.optimalAverageDepth(5), 0.01);
        assertEquals(1.625, ExperimentHelper.optimalAverageDepth(8), 0.01);
    }

    @Test
    public void averageDepthTest() {
        BST<Integer> BST1 = new BST<>();
        BST1.add(5);
        BST1.add(3);
        BST1.add(8);
        BST1.add(2);
        BST1.add(7);
        BST1.add(9);
        BST1.add(1);
        BST1.add(10);
        assertEquals(1.75, BST1.averageDepth(), 0.01);
    }
}
