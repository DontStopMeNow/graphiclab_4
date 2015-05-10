package ru.nsu.shmakov.model.utils;

import ru.nsu.shmakov.data.MyMat;
import ru.nsu.shmakov.data.Screen;

/**
 * Created by kyb1k on 04.05.2015.
 */
public class MatMvsCreator {
    public static MyMat createMvsMat(Screen screen) {
        int width  = screen.getWidth();
        int height = screen.getHeight();
        MyMat result = new MyMat();
        result.set(0, 0, (double)width);
        result.set(1, 1, -(double)height);
        result.set(2, 2, 1.);
        result.set(3, 3, 1.);
        result.set(0, 3, (double)width/2);
        result.set(1, 3, (double)height/2);
        return result;
    }
}
