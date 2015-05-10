package ru.nsu.shmakov.model.utils;

import ru.nsu.shmakov.data.Edge3D;

/**
 * Created by kyb1k on 06.05.2015.
 */
public class Clipper {
    public static boolean isDraw(Edge3D edge, double zn, double zf) {
        if(edge.p1.a < zn || edge.p1.a > zf || edge.p2.a < zn || edge.p2.a > zf)
            return false;
        return true;
    }
}
