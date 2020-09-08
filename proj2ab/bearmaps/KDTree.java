package bearmaps;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Junjie Qu
 */
public class KDTree implements PointSet {
    private Node root;

    private final boolean HORIZONTAL = true;
    private final boolean VERTICAL = false;

    public KDTree(List<Point> points) {
        for (Point point : points) {
            root = insert(point, root, HORIZONTAL);
        }
    }

    private Node insert(Point point, Node node, boolean orientation) {
        if (node == null) {
            node = new Node(point, orientation);
        }
        if (point.equals(node.getPoint())) {
            return node;
        }
        int cmp;
        if (orientation == HORIZONTAL) {
            cmp = Double.compare(point.getX(), node.getPoint().getX());
        } else {
            cmp = Double.compare(point.getY(), node.getPoint().getY());
        }
        if (cmp >= 0) {
            node.right = insert(point, node.rightChild(), !orientation);
        } else {
            node.left = insert(point, node.leftChild(), !orientation);
        }
        return node;
    }

    Comparator<Node> nodeComparator = (Node n1, Node n2) -> {
        if (n2.getOrient() == HORIZONTAL) {
            return Double.compare(n1.getPoint().getX(), n2.getPoint().getX());
        } else {
            return Double.compare(n1.getPoint().getY(), n2.getPoint().getY());
        }
    };
    @Override
    public Point nearest(double x, double y) {
        return nearest(root, new Point(x, y), root).getPoint();
    }

    private Node nearest(Node n, Point goal, Node best) {
        if (n == null) return best;
        if (n.distance(goal) < best.distance(goal)) {
            best = n;
        }
        int cmp = nodeComparator.compare(new Node(goal, n.getOrient()), n);
        Node goodSide, badSide;
        if (cmp < 0) {
            goodSide = n.leftChild();
            badSide = n.rightChild();
        } else {
            goodSide = n.rightChild();
            badSide = n.leftChild();
        }
        best = nearest(goodSide, goal, best);
        if (worthLooking(n, goal, best)) { // pruning rule
            best = nearest(badSide, goal, best);
        }
        return best;
    }

    private boolean worthLooking(Node n, Point goal, Node best) {
        double disToBest = Point.distance(goal, best.getPoint());
        double disToCurr;
        if (n.getOrient() == HORIZONTAL) {
            disToCurr = Point.distance(goal, new Point(n.p.getX(), goal.getY()));
        } else {
            disToCurr = Point.distance(goal, new Point(goal.getX(), n.p.getY()));
        }
        return Double.compare(disToCurr, disToBest) < 0;
    }

    private static class Node {
        private final Point p;
        private final boolean orientation;
        private Node left;
        private Node right;

        Node(Point point, boolean orientation) {
            p = new Point(point.getX(), point.getY());
            this.orientation = orientation;
            left = null;
            right = null;
        }

        double distance(Node n) {
            return Point.distance(this.getPoint(), n.getPoint());
        }
        double distance(Point p) {
            return Point.distance(this.getPoint(), p);
        }
        Point getPoint() {
            return p;
        }
        boolean getOrient() {
            return orientation;
        }
        Node leftChild() {
            return left;
        }
        Node rightChild() {
            return right;
        }
    }

    public static void main(String[] args) {
        Point p1 = new Point(2.0, 3.0);
        Point p2 = new Point(4.0, 2.0);
        Point p3 = new Point(4.0, 2.0);
        Point p4 = new Point(4.0, 5.0);
        Point p5 = new Point(3.0, 3.0);
        Point p6 = new Point(1.0, 5.0);
        Point p7 = new Point(4.0, 4.0);

        KDTree kdt = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
    }
}
