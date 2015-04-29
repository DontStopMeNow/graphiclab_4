package ru.nsu.shmakov.model.utils;

import ru.nsu.shmakov.data.MyMat;

/**
 * Created by kyb1k on 28.04.2015.
 */
public class Rotator {
    public static MyMat rotateX(Double angle) {
        MyMat result = new MyMat();
        result.set(0, 0, new Double(1));
        result.set(1, 1,  Math.cos(angle));
        result.set(2, 1, -Math.sin(angle));
        result.set(1, 2,  Math.sin(angle));
        result.set(2, 2,  Math.cos(angle));
        result.set(3, 3, new Double(1));
        return result;
    }

    public static MyMat rotateY(Double angle) {
        MyMat result = new MyMat();
        result.set(0, 0, Math.cos(angle));
        result.set(1, 1, new Double(1));
        result.set(2, 2, Math.cos(angle));
        result.set(3, 3, new Double(1));
        result.set(0, 2, -Math.sin(angle));
        result.set(2, 0, Math.sin(angle));
        return result;
    }

    public static MyMat rotateZ(Double angle) {
        MyMat result = new MyMat();
        result.set(0, 0, Math.cos(angle));
        result.set(1, 1, Math.cos(angle));
        result.set(2, 2, new Double(1));
        result.set(3, 3, new Double(1));
        result.set(1, 0, -Math.sin(angle));
        result.set(0, 1, Math.sin(angle));
        return result;
    }
}
