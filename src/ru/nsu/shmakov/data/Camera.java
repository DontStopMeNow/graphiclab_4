package ru.nsu.shmakov.data;

/**
 * Created by kyb1k on 28.04.2015.
 */
public class Camera {
    Point3D position;
    Point3D lookAt;
    Point3D up;

    public Camera(Point3D position, Point3D lookAt) {
        this.position = position;
        this.lookAt = lookAt;
        this.up       = new Point3D(new Double(0), new Double(1), new Double(0), new Double(0));
    }

    public Camera(double x1, double y1, double z1, double x2, double y2, double z2) {
        this.position = new Point3D(x1, y1, z1, new Double(0));
        this.lookAt   = new Point3D(x2, y2, z2, new Double(0));
        this.up       = new Point3D(new Double(0), new Double(1), new Double(0), new Double(0));
    }

    public Point3D getPosition() {
        return position;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public Point3D getLookAt() {
        return lookAt;
    }

    public void setLookAt(Point3D lookAt) {
        this.lookAt = lookAt;
    }

    public Point3D getUp() {
        return up;
    }

    public void setUp(Point3D up) {
        this.up = up;
    }
}
