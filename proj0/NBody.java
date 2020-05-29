/**
*   Running the simulation
*/
public class NBody {
    /**
    * return the radius of the universe
    */
    public static double readRadius(String filename) {
        In in = new In(filename);
        int numOfPlanets = in.readInt();
        double radOfUniverse = in.readDouble();
        return radOfUniverse;
    }
    /**
    * Return an array of bodies in the given file
    */
    public static Body[] readBodies(String filename) {
        In in = new In(filename);
        int numOfPlanets = in.readInt();
        double radOfUniverse = in.readDouble();
        Body[] bs = new Body[numOfPlanets];
        for(int i = 0; i < numOfPlanets; i += 1) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            bs[i] = new Body(xP, yP, xV, yV, m, img);
        }
        return bs;
    }
}
