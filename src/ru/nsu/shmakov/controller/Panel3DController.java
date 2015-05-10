package ru.nsu.shmakov.controller;

import ru.nsu.shmakov.model.Wireframe;

/**
 * Created by kyb1k on 06.05.2015.
 */
public class Panel3DController {
    Wireframe wireframe;

    public Panel3DController(Wireframe wireframe) {
        this.wireframe = wireframe;
    }

    public void changeRouteParam(int t) {
        wireframe.setRouteParam(t);
    }

    public void changeParams(Double[] p) {
        wireframe.changeParams(p);
    }

    public void changeCurrentFigure(int t) {
        wireframe.changeCurrentFigure(t);
    }
}
