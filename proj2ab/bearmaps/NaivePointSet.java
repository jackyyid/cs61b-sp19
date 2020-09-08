package bearmaps;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junjie Qu
 */
public class NaivePointSet implements PointSet {

    private List<Point> myPoints;

    public NaivePointSet(List<Point> points) {
        myPoints = new ArrayList<>();
        myPoints.addAll(points);
    }

    /**
     * Returns the closest point to the inputted coordinates.
     */
    @Override
    public Point nearest(double x, double y) {
        double mindis = 9999;
        Point nearestPoint = null;
        Point tar = new Point(x, y);
        for (Point point : myPoints) {
            double dis = Point.distance(point, tar);
            if (dis < mindis) {
                mindis = dis;
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }
}
