package ru.nsu.shmakov.model.utils;

import ru.nsu.shmakov.data.Camera;
import ru.nsu.shmakov.data.MyMat;
import ru.nsu.shmakov.data.Point3D;

import java.util.Calendar;

/**
 * Created by kyb1k on 28.04.2015.
 */
public class MatViewCreator {
    public static MyMat createMatView(Camera camera) {
        Point3D zaxis = camera.getLookAt().sub(camera.getPosition()).normalize3();
        Point3D xaxis = camera.getUp().cross3(zaxis).normalize3();
        Point3D yaxis = zaxis.cross3(xaxis);

        MyMat result = new MyMat();
        result.set(0, 0, xaxis.x);
        result.set(0, 1, xaxis.y);
        result.set(0, 2, xaxis.z);

        result.set(1, 0, yaxis.x);
        result.set(1, 1, yaxis.y);
        result.set(1, 2, yaxis.z);

        result.set(2, 0, zaxis.x);
        result.set(2, 1, zaxis.y);
        result.set(2, 2, zaxis.z);

        result.set(3, 0, new Double(0));
        result.set(3, 1, new Double(0));
        result.set(3, 2, new Double(0));
        result.set(3, 3, new Double(1));

        result.set(0, 3, -xaxis.dot3(camera.getPosition()));
        result.set(1, 3, -yaxis.dot3(camera.getPosition()));
        result.set(2, 3, -zaxis.dot3(camera.getPosition()));

        return result;
    }
}
