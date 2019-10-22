package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void testNearestWIth100000PointsAnd10000Queries() {
        Random r = new Random(100);
        List<Point> listP = new ArrayList<>();
        List<Point> listQ = new ArrayList<>();
        for (int i = 0; i < 100000; i += 1) {
            double x = r.nextDouble();
            double y = r.nextDouble();
            Point p = new Point(x, y);
            listP.add(p);
        }
        NaivePointSet nps = new NaivePointSet(listP);
        KDTree kd = new KDTree(listP);
        for (int i = 0; i < 10000; i += 1) {
            double x = r.nextDouble();
            double y = r.nextDouble();
            Point q = new Point(x, y);
            listQ.add(q);
        }
        for (Point point : listQ) {
            Point expected = nps.nearest(point.getX(), point.getY());
            Point actual = kd.nearest(point.getX(), point.getY());
            assertEquals(expected, actual);
        }
        Stopwatch sw1 = new Stopwatch();
        for (Point point : listQ) {
            Point expected = nps.nearest(point.getX(), point.getY());
        }
        System.out.println("Total time is " + sw1.elapsedTime() + " seconds to run nearest() 10000 times in " +
                "NaivePointSet with 100000 points." );
        Stopwatch sw2 = new Stopwatch();
        for (Point point : listQ) {
            Point actual = kd.nearest(point.getX(), point.getY());
        }
        System.out.println("Total time is " + sw2.elapsedTime() + " seconds to run nearest() 10000 times in " +
                "KDTree with 100000 points." );



    }

}
