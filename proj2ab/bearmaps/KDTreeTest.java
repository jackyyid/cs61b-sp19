package bearmaps;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class KDTreeTest {

    @Test
    public void testKDTree() {
        Point p1 = new Point(2.0, 3.0);
        Point p2 = new Point(4.0, 2.0);
        Point p3 = new Point(4.0, 2.0);
        Point p4 = new Point(4.0, 5.0);
        Point p5 = new Point(3.0, 3.0);
        Point p6 = new Point(1.0, 5.0);
        Point p7 = new Point(4.0, 4.0);
        KDTree kdt = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
    }

    @Test
    public void testNearest() {
        Point p1 = new Point(2.0, 3.0);
        Point p2 = new Point(4.0, 2.0);
        Point p3 = new Point(4.0, 2.0);
        Point p4 = new Point(4.0, 5.0);
        Point p5 = new Point(3.0, 3.0);
        Point p6 = new Point(1.0, 5.0);
        Point p7 = new Point(4.0, 4.0);
        KDTree kdt = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        Point pn = kdt.nearest(0, 7);
        assertEquals(1.0, pn.getX(), 0.01);
        assertEquals(5.0, pn.getY(), 0.01);
    }

    Random r = new Random(500);
    double min = -10;
    double max = 10;

    private Point randomPoint() {
        double x = min + (max - min) * r.nextDouble();
        double y = min + (max - min) * r.nextDouble();
        return new Point(x, y);
    }

    private List<Point> randomPoints(int count) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            points.add(randomPoint());
        }
        return points;
    }

    @Test
    public void testWithNaiveAndKD() {
        int pointsCount = 100000;
        int queryCount = 50000;
        List<Point> kdPoints = randomPoints(pointsCount);
        List<Point> queryPoints = randomPoints(queryCount);
        KDTree kdt = new KDTree(kdPoints);
        NaivePointSet nps = new NaivePointSet(kdPoints);
        for (Point qp : queryPoints) {
            Point p1 = kdt.nearest(qp.getX(), qp.getY());
            Point p2 = nps.nearest(qp.getX(), qp.getY());
            assertEquals(p1, p2);
        }
    }

    @Test
    public void testTimingWithNaiveAndKD() {
        int pointsCount = 100000;
        int queryCount = 50000;
        List<Point> kdPoints = randomPoints(pointsCount);
        List<Point> queryPoints = randomPoints(queryCount);
        KDTree kdt = new KDTree(kdPoints);
        NaivePointSet nps = new NaivePointSet(kdPoints);
        long start = System.currentTimeMillis();
        for (Point p : queryPoints) {
            nps.nearest(p.getX(), p.getY());
        }
        long end = System.currentTimeMillis();
        System.out.println("Naive " + queryCount + " queries on " + pointsCount + " points: " +
                (end - start) / 1000.0 + " seconds.");

        start = System.currentTimeMillis();
        for (Point p : queryPoints) {
            kdt.nearest(p.getX(), p.getY());
        }
        end = System.currentTimeMillis();
        System.out.println("KDTree " + queryCount + " queries on " + pointsCount + " points: " +
                (end - start) / 1000.0 + " seconds.");
    }




}
