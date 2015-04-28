package ru.nsu.shmakov.data;

/**
 * Created by kyb1k on 28.04.2015.
 */
public class Point3D implements Cloneable {
    public Double x;
    public Double y;
    public Double z;
    public Double a;

    public Point3D(Double x, Double y, Double z, Double a) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.a = a;
    }

    public boolean equals(Point3D another) {
        return this.x.equals(another.x) && this.y.equals(another.y) && this.z.equals(another.z) && this.a.equals(another.a);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }
}
