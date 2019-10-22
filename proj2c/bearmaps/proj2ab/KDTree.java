package bearmaps.proj2ab;

import java.util.List;

public class KDTree implements PointSet {
    private List<Point> listOfPoints;
    private Node root;

    public class Node {
        private Node left, right;
        private boolean xBased;
        private Point p;

        public Node(Point p, boolean xBased) {
            this.p = p;
            this.xBased = xBased;
        }
    }


    public KDTree(List<Point> points) {
        listOfPoints = points;
        for (Point p : listOfPoints) {
            insert(p);
        }
    }
    /** Inserts items in the KDTree. */
    private void insert(Point point) {
        root = insert(root, point, true);
    }

    /** Helper method of insert(). */
    private Node insert(Node n, Point point, boolean isX) {
        if (n == null) {
            return new Node(point, isX);
        }
        if (n.xBased) {
            if (point.getX() < n.p.getX()) {
                n.left = insert(n.left, point, !isX);
            } else {
                n.right = insert(n.right, point, !isX);
            }
        }
        if (!n.xBased) {
            if (point.getY() < n.p.getY()) {
                n.left = insert(n.left, point, !isX);
            } else {
                n.right = insert(n.right, point, !isX);
            }
        }
        return n;
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        return nearest(root, goal, root).p;
    }

    /** Helper method of nearest(). */
    private Node nearest(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        if (Point.distance(n.p, goal) < Point.distance(best.p, goal)) {
            best = n;
        }
        Node goodSide, badSide;
        if (n.xBased) {
            if (goal.getX() < n.p.getX()) {
             goodSide = n.left;
             badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
        } else {
            if (goal.getY() < n.p.getY()) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
        }
        best = nearest(goodSide, goal, best);
        if (badSideWorthConsidering(n, goal, best)) {
            best = nearest(badSide, goal, best);
        }
        return best;
    }

    /** Checks whether the bad side is worth considering. 
    If the potential shortest distance is smaller than current shortest distance, 
    it's worth considering, not otherwise. */
    private boolean badSideWorthConsidering(Node n, Point goal, Node best) {
        double currShortestDis = Point.distance(goal, best.p);
        double ptShortestDis; //potential shortest distance
        if (n.xBased) {
            ptShortestDis = Point.distance(new Point(n.p.getX(), goal.getY()), goal);
        } else {
            ptShortestDis = Point.distance(new Point(goal.getX(), n.p.getY()), goal);
        }
        return ptShortestDis - currShortestDis < 0;
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(4, 4);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(2, 7);
        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        Point ret = kd.nearest(0, 7); // returns p7
        System.out.println(ret.getX()); // evaluates to 2.0
        System.out.println(ret.getY()); // evaluates to 7.0
    }
}
