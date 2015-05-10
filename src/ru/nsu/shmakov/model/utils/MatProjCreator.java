package ru.nsu.shmakov.model.utils;

import ru.nsu.shmakov.data.MyMat;
import ru.nsu.shmakov.data.Screen;

/**
 * Created by kyb1k on 28.04.2015.
 */
public class MatProjCreator {
    public static MyMat createProjMat(Screen screen) {
        MyMat result = new MyMat();
        double angle = Math.PI/2;

        double as = (double)screen.getWidth()/(double)screen.getHeight();
        //System.out.println(as);
        double yScale = 1 / Math.tan(angle / 2);  // 1/tan(x) == cot(x)
        double xScale = yScale / as;

        double zn = screen.getNearPanelDist();
        double zf = screen.getFarPanelDist ();

        result.set(0, 0, xScale);
        result.set(1, 1, yScale);
        result.set(2, 2, zf/(zf-zn));
        result.set(2, 3, -zn*zf/(zf-zn));
        result.set(3, 2, 1.);

        return result;
    }
    /*public static MyMat createProjMat(Screen screen) {
        MyMat result = new MyMat();
        result.set(0, 0, 2*screen.getNearPanelDist()/screen.getWidth());
        result.set(1, 1, 2*screen.getNearPanelDist()/screen.getHeight());
        result.set(2, 2, screen.getFarPanelDist()/(screen.getFarPanelDist()-screen.getNearPanelDist()));
        result.set(2, 3, -screen.getNearPanelDist()*screen.getFarPanelDist()/(screen.getFarPanelDist()-screen.getNearPanelDist()));
        result.set(3, 2, new Double(1));
        return result;
    }*/
    /*public static MyMat createOrthProjMat(Screen screen) {
        MyMat result = new MyMat();
        result.set(0, 0, (2./screen.getWidth()));
        result.set(1, 1, (2./screen.getHeight()));
        result.set(2, 2, 1./(screen.getFarPanelDist() - screen.getNearPanelDist()));
        result.set(2, 3, -screen.getNearPanelDist()/(screen.getFarPanelDist() - screen.getNearPanelDist()));
        result.set(3, 2, new Double(1));
        return result;
    }*/
}
