package ru.nsu.shmakov.model;

import ru.nsu.shmakov.data.Edge3D;
import ru.nsu.shmakov.data.MyMat;
import ru.nsu.shmakov.data.Point3D;
import ru.nsu.shmakov.model.utils.Rotator;

import java.util.ArrayList;

/**
 * Created by kyb1k on 06.05.2015.
 */
public class RotatingBodyCreator {
    public static ArrayList<Edge3D> createBody(ArrayList<Edge3D> spline, int step, int k) {
        double da = Math.PI*2/step;
        ArrayList<Edge3D> result = new ArrayList<>();
        ArrayList<ArrayList<Edge3D>> splines = new ArrayList<>();
        int l = 0;
        for (int i = 0; i < step; i++) {

            MyMat rotate = Rotator.rotateY(da * i);
            splines.add(new ArrayList());
            for (Edge3D edge: spline) {
                splines.get(i).add(new Edge3D(rotate.multiplex(edge.p1), rotate.multiplex(edge.p2)));
            }
        }
        int mainPoints = spline.size()/k;
        for (int i = 1; i < splines.size() + 1; i++) {
            for (int j = 0; j < mainPoints; j++) {
                Edge3D edge = new Edge3D(splines.get(i - 1).get(j*k).p1, splines.get(i%splines.size()).get(j*k).p1);
                result.add(edge);
            }

            result.addAll(splines.get(i % splines.size()));
        }
        if (spline.size() >= 4)
            for (int i = 1; i < splines.size() + 1; i++) {
                Edge3D edge = new Edge3D(splines.get(i - 1).get(spline.size() - 1).p2, splines.get(i%splines.size()).get(spline.size() - 1).p2);
                result.add(edge);
            }
        return result;
    }
}
