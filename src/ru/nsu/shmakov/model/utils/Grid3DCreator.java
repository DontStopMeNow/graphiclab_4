package ru.nsu.shmakov.model.utils;

import ru.nsu.shmakov.data.Edge3D;
import ru.nsu.shmakov.data.Point3D;

import java.util.ArrayList;

/**
 * Created by kyb1k on 06.05.2015.
 */
public class Grid3DCreator {
    public static ArrayList<Edge3D> createGrid() {
        ArrayList<Edge3D> result = new ArrayList<>();

        for (int i = -10; i < 10; i++) {
            for (int j = -10; j < 10; j++) {
                result.add(new Edge3D(new Point3D(i*40., 100., j*50., 1.), new Point3D((i + 1)*40., 100., j*50., 1.)));
                result.add(new Edge3D(new Point3D(i*40., 100., j*50., 1.), new Point3D(i*40., 100., (j + 1)*50., 1.)));
            }
        }
        return result;
    }
}
