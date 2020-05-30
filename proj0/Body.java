
/**
* Calculate all parameters we need
*/
public class Body {
    /**
    *   @param   xxPos      X position
    *   @param   yyPos      Y position
    *   @param   xxVel      Velocity on X direction
    *   @param   yyVel      Velocity on Y direction
    *   @param   mass       Mass of the object
    *   @param  imgFileName Image file name of the object
    */

    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    /**
    * Initialzation
    */
    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b) {
        this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName);
    }
    /**
    * calculate distance betweeen two bodies
    */
    public double calcDistance(Body b) {
        double xxDif = xxPos - b.xxPos;
        double yyDif = yyPos - b.yyPos;
        return Math.sqrt(xxDif * xxDif + yyDif * yyDif);
    }
    /**
    * gravitational constant G = 6.67e-11
    */
    final static double Gconst = 6.67e-11;
    /**
    * calculate force exerted by given body b on this body
    */
    public double calcForceExertedBy(Body b) {
        double dis = calcDistance(b);
        return Gconst * mass * b.mass / (dis * dis);
    }
    /**
    * calculate force exerted by given body b on this body in X direction
    */
    public double calcForceExertedByX(Body b) {
        return calcForceExertedBy(b) * (b.xxPos - xxPos) / calcDistance(b);
    }
    /**
    * calculate force exerted by given body b on this body in Y direction
    */
    public double calcForceExertedByY(Body b) {
        return calcForceExertedBy(b) * (b.yyPos - yyPos) / calcDistance(b);
    }
    /**
    * Sum force exerted by ALL bodies b on this body in X direction
    */
    public double calcNetForceExertedByX(Body[] bs) {
        double forceSum = 0;
        for(Body b : bs) {
            if(equals(b)) continue;
            forceSum += calcForceExertedByX(b);
        }
        return forceSum;
    }
    /**
    * Sum force exerted by ALL bodies b on this body in Y direction
    */
    public double calcNetForceExertedByY(Body[] bs) {
        double forceSum = 0;
        for(Body b : bs) {
            if(equals(b)) continue;
            forceSum += calcForceExertedByY(b);
        }
        return forceSum;
    }
    /**
    *   Update position and velocity of the object
    */
    public void update(double dt, double xForce, double yForce) {
        double accX = xForce / mass;
        double accY = yForce / mass;
        xxVel = xxVel + dt * accX;
        yyVel = yyVel + dt * accY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }
    /**
    *   Draw the Body at appropriate position
    */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
