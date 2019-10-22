package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> listOfPoints;

    public NaivePointSet(List<Point> points) {
        listOfPoints = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point aimP = new Point(x, y);
        Point nearestP = new Point(listOfPoints.get(0).getX(), listOfPoints.get(0).getY());
        double min = Point.distance(aimP, nearestP);
        for (Point currP : listOfPoints) {
            double dis = Point.distance(aimP, currP);
            if (min >= dis) {
                min = dis;
                nearestP = currP;
            }
        }
        return nearestP;
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(4, 4);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(2, 7);
        NaivePointSet np = new NaivePointSet(List.of(p1, p2, p3, p4, p5, p6, p7));
        Point ret = np.nearest(0, 7); // returns p6
        System.out.println(ret.getX()); // evaluates to 2.0
        System.out.println(ret.getY()); // evaluates to 7.0
    }
}
