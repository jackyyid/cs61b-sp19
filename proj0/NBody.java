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
    /**
    * Draw the universe
    */
    public static String imgBlackground = "images/starfield.jpg";
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        // read all the numbers in the given file
        double radOfUniverse = readRadius(filename);
        Body[] bodies = readBodies(filename);


        StdDraw.enableDoubleBuffering();
        // set scale of the canvas
        StdDraw.setScale(-radOfUniverse, radOfUniverse);

        for (double time = 0; time <= T; time = time + dt){
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for(int i = 0; i < bodies.length; i += 1) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for(int i = 0; i < bodies.length; i += 1) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.clear();
            // Draw the background
            StdDraw.picture(0, 0,  imgBlackground);
            // Draw all bodies
            for (Body b : bodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radOfUniverse);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
            bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }

    }
}
