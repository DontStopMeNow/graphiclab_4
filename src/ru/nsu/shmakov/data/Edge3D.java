package ru.nsu.shmakov.data;

/**
 * Created by kyb1k on 28.04.2015.
 */
public class Edge3D implements Cloneable {
    public Point3D p1;
    public Point3D p2;

    public Edge3D(Point3D p1, Point3D p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Edge3D(Double x1, Double y1, Double z1, Double a1, Double x2, Double y2, Double z2, Double a2) {
        this.p1 = new Point3D(x1, y1, z1, a1);
        this.p2 = new Point3D(x2, y2, z2, a2);
    }

    public boolean equals(Edge3D another) {
        return this.p1.equals(another.p1) && this.p2.equals(another.p2);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }

    public Point3D getP1() {
        return p1;
    }

    public void setP1(Point3D p1) {
        this.p1 = p1;
    }

    public Point3D getP2() {
        return p2;
    }

    public void setP2(Point3D p2) {
        this.p2 = p2;
    }
}
