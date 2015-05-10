package ru.nsu.shmakov.model.bezier;

import ru.nsu.shmakov.data.Edge3D;
import ru.nsu.shmakov.data.Point3D;

import java.util.ArrayList;

/**
 * Created by kyb1k on 05.05.2015.
 */
public class BezierCurve {
    public static ArrayList<Edge3D> createBezierCurve(Point3D p1, Point3D p2, Point3D p3, Point3D p4, double step) {
        ArrayList<Edge3D>  result = new ArrayList<>();
        ArrayList<Point3D> points = new ArrayList<>();

        if (step <= 0 || step > 0.2)
            throw new RuntimeException("Invalid Bezier step");
        for (double t = 0; t <= 1; t+= step) {
            Point3D p = new Point3D(0., 0., 0., 1.);
            double mum1 = 1 - t;
            double mum13 = mum1 * mum1 * mum1;
            double mu3 = t * t * t;

            p.x = mum13*p1.x + 3*t*mum1*mum1*p2.x + 3*t*t*mum1*p3.x + mu3*p4.x;
            p.y = mum13*p1.y + 3*t*mum1*mum1*p2.y + 3*t*t*mum1*p3.y + mu3*p4.y;
            p.z = mum13*p1.z + 3*t*mum1*mum1*p2.z + 3*t*t*mum1*p3.z + mu3*p4.z;

            points.add(p);
        }
        for (int i = 1; i < points.size(); ++i) {
            result.add(new Edge3D(points.get(i - 1), points.get(i)));
        }
        result.add(new Edge3D(result.get(result.size()-1).p2, p4));
        return result;
    }

}
