package ru.nsu.shmakov.model.bezier;

import ru.nsu.shmakov.data.Edge3D;
import ru.nsu.shmakov.data.Point3D;

import java.util.ArrayList;

/**
 * Created by kyb1k on 05.05.2015.
 */
public class BezierPath {
    public static ArrayList<Edge3D> createBezierPath(ArrayList<Point3D> points, double step) {
        ArrayList<Edge3D> result = new ArrayList<>();
        if (points.size() < 4 || points.size()%3 != 1)
            throw new RuntimeException("Invalid count of points" + points.size());
        for (int i = 0; i < points.size()-1; i += 3) {
            result.addAll(BezierCurve.createBezierCurve(points.get(i), points.get(i+1), points.get(i+2), points.get(i+3), step));
        }
        return result;
    }
}
