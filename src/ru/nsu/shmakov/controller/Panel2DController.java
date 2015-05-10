package ru.nsu.shmakov.controller;

import ru.nsu.shmakov.model.Wireframe;

/**
 * Created by kyb1k on 05.05.2015.
 */
public class Panel2DController {
    Wireframe wireframe;

    public Panel2DController(Wireframe wireframe) {
        this.wireframe = wireframe;
    }

    public void clicked(int x, int y) {
        wireframe.clicked2D(x, y);
        wireframe.apply();
    }

    public void apply() {
        wireframe.apply();
    }

    public void changeBezierStep(double step) {
        wireframe.setBezierStep(step);
    }

    public void clear() {
        wireframe.clear2D();
    }

    public void undo() {
        wireframe.undo2D();
        wireframe.apply() ;
    }
}
