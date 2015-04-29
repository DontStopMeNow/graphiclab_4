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
    public Point3D sum(Point3D another) {
        return new Point3D(this.x + another.x, this.y + another.y, this.z + another.z, this.a + another.a);
    }

    public Point3D sub(Point3D another) {
        return new Point3D(this.x - another.x, this.y - another.y, this.z - another.z, this.a - another.a);
    }

    public boolean equals(Point3D another) {
        return this.x.equals(another.x) && this.y.equals(another.y) && this.z.equals(another.z) && this.a.equals(another.a);
    }

    public Double length4() {
        return Math.sqrt(x*x + y*y + z*z + a*a);
    }

    public Double length3() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    public Point3D normalize4() {
        Double k = length4();
        return new Point3D(x/k, y/k, z/k, a/k);
    }

    public Point3D normalize3() {
        Double k = length4();
        return new Point3D(x/k, y/k, z/k, new Double(0));
    }

    public Double dot3(Point3D another) {
        Double result = this.x*another.x + this.y*another.y + this.z*another.z;
        return result;
    }

    public Point3D cross3(Point3D another) {
        return new Point3D(this.y*another.z - this.z*another.y, this.x*another.z - this.z*another.x, this.x*another.y - this.y*another.x, new Double(0));
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(x).append('\n').append(y).append('\n').append(z).append('\n').append(a).append('\n').toString();
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
