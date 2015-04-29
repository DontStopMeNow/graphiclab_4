package ru.nsu.shmakov.model.utils;

import ru.nsu.shmakov.data.MyMat;
import ru.nsu.shmakov.data.Screen;

/**
 * Created by kyb1k on 28.04.2015.
 */
public class MatProjCreator {
    public static MyMat createProjMat(Screen screen) {
        MyMat result = new MyMat();
        result.set(0, 0, 2*screen.getNearPanelDist()/screen.getWidth());
        result.set(1, 1, 2*screen.getNearPanelDist()/screen.getHeight());
        result.set(2, 2, screen.getFarPanelDist()/(screen.getFarPanelDist()-screen.getNearPanelDist()));
        result.set(2, 3, -screen.getNearPanelDist()*screen.getFarPanelDist()/(screen.getFarPanelDist()-screen.getNearPanelDist()));
        result.set(3, 2, new Double(1));
        return result;
    }
    public static MyMat createOrthProjMat(Screen screen) {
        MyMat result = new MyMat();
        result.set(0, 0, (2./screen.getWidth()));
        result.set(1, 1, (2./screen.getHeight()));
        result.set(2, 2, 1./(screen.getFarPanelDist() - screen.getNearPanelDist()));
        result.set(2, 3, -screen.getNearPanelDist()/(screen.getFarPanelDist() - screen.getNearPanelDist()));
        result.set(3, 2, new Double(1));
        return result;
    }
}
