package ru.nsu.shmakov.model.utils;

import ru.nsu.shmakov.data.MyMat;

/**
 * Created by kyb1k on 28.04.2015.
 */
public class Translator {
    public static MyMat translate(double tx, double ty, double tz) {
        MyMat result = new MyMat();
        result.set(0, 0, new Double(1));
        result.set(1, 1, new Double(1));
        result.set(2, 2, new Double(1));
        result.set(3, 3, new Double(1));
        result.set(3, 0, tx);
        result.set(3, 1, ty);
        result.set(3, 2, tz);
        return result;
    }
}
