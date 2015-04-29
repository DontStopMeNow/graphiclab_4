package ru.nsu.shmakov;

import ru.nsu.shmakov.data.MyMat;
import ru.nsu.shmakov.data.Point3D;
import ru.nsu.shmakov.model.utils.Rotator;

/**
 * Created by kyb1k on 28.04.2015.
 */
public class Main {
    public static void main(String[] args) {
        MyMat m1 = new MyMat();
        m1.set(0, 0, new Double(3));
        m1.set(0, 1, new Double(1));
        m1.set(1, 0, new Double(2));
        m1.set(3, 3, new Double(5));
        //System.out.println(m1);

        MyMat m2 = new MyMat();
        m2.set(0, 0, new Double(3));
        m2.set(3, 1, new Double(1));
        m2.set(1, 0, new Double(2));
        m2.set(3, 3, new Double(5));
        //System.out.println(m1.transposition().toString());

        Point3D point = new Point3D(new Double(1), new Double(1), new Double(1), new Double(1));
        //System.out.println(m1.multiplex(point).toString());
        System.out.println(Rotator.rotateZ(Math.PI / 2));
        //System.out.println(m1.sum(m2).toString());
        //System.out.println(m1.multiplex(m2).toString());
    }
}
