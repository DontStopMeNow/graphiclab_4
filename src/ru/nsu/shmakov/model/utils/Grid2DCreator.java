package ru.nsu.shmakov.model.utils;

import ru.nsu.shmakov.data.Edge3D;
import ru.nsu.shmakov.data.Point3D;

import java.util.ArrayList;

/**
 * Created by kyb1k on 05.05.2015.
 */
public class Grid2DCreator {
    public static ArrayList<Edge3D> createGrid(int width, int height, int k) {
        int dx = width /k;
        int dy = height/k;

        ArrayList<Edge3D> result = new ArrayList<>();
        for (int y = height/2; y < height; y += 20) {
            result.add(new Edge3D(new Point3D((double) width/2, (double)y - height/2, 0., 1.),
                                  new Point3D((double)-width/2, (double)y - height/2, 0., 1.)));

            result.add(new Edge3D(new Point3D((double) width/2, -((double)y - height/2), 0., 1.),
                                  new Point3D((double)-width/2, -((double)y - height/2), 0., 1.)));
        }
        for (int x = width/2; x < width; x += 20) {
            result.add(new Edge3D(new Point3D((double)x - width/2, (double) height/2, 0., 1.),
                                  new Point3D((double)x - width/2, (double)-height/2, 0., 1.)));

            result.add(new Edge3D(new Point3D(-((double)x - width/2), (double) height/2, 0., 1.),
                                  new Point3D(-((double)x - width/2), (double)-height/2, 0., 1.)));
        }

        return result;
    }
    public static ArrayList<Edge3D> createAxis(int width, int height) {
        ArrayList<Edge3D> result = new ArrayList<>();
        result.add(new Edge3D(new Point3D((double)-width/2, 0., 0., 1.), new Point3D((double)width/2, 0., 0., 1.)));
        result.add(new Edge3D(new Point3D(0., (double)-height/2, 0., 1.), new Point3D(0., (double)height/2, 0., 1.)));
        return result;
    }
}
