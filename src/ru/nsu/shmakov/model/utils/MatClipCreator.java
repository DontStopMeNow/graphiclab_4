package ru.nsu.shmakov.model.utils;

import ru.nsu.shmakov.data.MyMat;

/**
 * Created by kyb1k on 06.05.2015.
 */
public class MatClipCreator {
    public static MyMat crateClipMat() {
        MyMat result = new MyMat();
        double dvClipWidth = 2;
        double dvClipHeight = 2;
        double dvClipX = -1;
        double dvClipY = 1;
        double dvMinZ = 0;
        double dvMaxZ = 1;

        result.set(0, 0, 2./dvClipWidth);
        result.set(1, 1, 2./dvClipHeight);
        result.set(2, 2, 1./(dvMaxZ - dvMinZ));
        result.set(3, 3, 1.);
        result.set(0, 3, -1. -2.*dvClipX/dvClipWidth);
        result.set(1, 3, 1. -2.*dvClipY/dvClipHeight);
        result.set(2, 3, -dvMinZ/(dvMaxZ - dvMinZ));

        return result;
    }
}
