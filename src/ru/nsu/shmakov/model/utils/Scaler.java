package ru.nsu.shmakov.model.utils;

import ru.nsu.shmakov.data.MyMat;

/**
 * Created by kyb1k on 28.04.2015.
 */
public class Scaler {
    public static MyMat scale(double sx, double sy, double sz) {
        MyMat result = new MyMat();
        result.set(0, 0, sx);
        result.set(1, 1, sy);
        result.set(2, 2, sz);
        result.set(3, 3, new Double(1));
        return result;
    }
}
