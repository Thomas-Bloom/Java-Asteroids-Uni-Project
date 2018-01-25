package utilities;

import game1.Constants;

// mutable 2D vectors
public final class Vector2D {
    public double x, y;
    Vector2D newVec;

    // constructor for zero vector
    public Vector2D() {
        x = 0;
        y = 0;
    }

    // constructor for vector with given coordinates
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // constructor that copies the argument vector
    public Vector2D(Vector2D v) {
        newVec = new Vector2D(v.x, v.y);
        this.x = newVec.x;
        this.y = newVec.y;
    }

    // set coordinates
    public Vector2D set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    // set coordinates based on argument vector
    public Vector2D set(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
        return this;
    }

    // compare for equality (note Object type argument)
    public boolean equals(Object o) {
        Vector2D otherVec = (Vector2D)o;

        if(this.x == otherVec.x){
            if(this.y == otherVec.y){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    // String for displaying vector as text
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    //  magnitude (= "length") of this vector
    public double mag() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    // angle between vector and horizontal axis in radians in range [-PI,PI]
// can be calculated using Math.atan2
    public double angle() {
        double theAngle = (Math.atan2(this.y, this.x) - Math.atan2(0, 10));

        if (theAngle < -Math.PI)
            theAngle += 2 * Math.PI;
        if (theAngle > Math.PI)
            theAngle -= 2 * Math.PI;

        return theAngle;
    }

    // angle between this vector and another vector in range [-PI,PI]
    public double angle(Vector2D other) {
        /*
        for (int i : new int[] { -1, 0, 1 }) {
            for (int j : new int[] { -1, 0, 1 }) {
                v = new Vector2D(vx * i, vy * j);
                vCopy = new Vector2D(vx * i, vy * j);
                approxEquals(v.angle(), Math.atan2(vy * j, vx * i));
                approxEquals(v, vCopy);
            }
        }
        */

        double theAngle = (Math.atan2(y, x) - Math.atan2(other.y, other.x));

        if (theAngle < -Math.PI)
            theAngle += 2 * Math.PI;
        if (theAngle > Math.PI)
            theAngle -= 2 * Math.PI;

        return -theAngle;
    }

    // add argument vector
    public Vector2D add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    // add values to coordinates
    public Vector2D add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    // weighted add - surprisingly useful
    public Vector2D addScaled(Vector2D v, double fac) {
        this.x = this.x + v.x * fac;
        this.y = this.y + v.y * fac;
        return this;
        //vx + factor * wx, vy + factor * wy)
    }

    // subtract argument vector
    public Vector2D subtract(Vector2D v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    // subtract values from coordinates
    public Vector2D subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    // multiply with factor
    public Vector2D mult(double fac) {
        this.x *= fac;
        this.y *= fac;
        return this;
    }

    // rotate by angle given in radians
    public Vector2D rotate(double angle) {
        double x = this.x * Math.cos(angle) - this.y * Math.sin(angle);
        double y = this.x * Math.sin(angle) + this.y * Math.cos(angle);
        this.set(x, y);
        return this;
    }

    // "dot product" ("scalar product") with argument vector
    public double dot(Vector2D v) {
        return (this.x * v.x + this.y * v.y);
    }

    // distance to argument vector
    public double dist(Vector2D v) {
        return Math.hypot(v.x - this.x, v.y - this.y);
    }

    // normalise vector so that magnitude becomes 1
    public Vector2D normalise() {
        double x = this.x / mag();
        double y = this. y / mag();
        this.x = x;
        this.y = y;
        return this;
    }

    // wrap-around operation, assumes w> 0 and h>0
// remember to manage negative values of the coordinates
    public Vector2D wrap(double w, double h) {
        this.x %= w;
        this.y %= h;

        if(this.x < 0)
            this.x += w;
        if(this.y < 0)
            this.y += h;

        return this;
    }

    // construct vector with given polar coordinates
    public static Vector2D polar(double angle, double mag) {
        Vector2D newVec = new Vector2D();
        newVec.x = mag * Math.cos(angle);
        newVec.y = mag * Math.sin(angle);
        return newVec;
    }

}