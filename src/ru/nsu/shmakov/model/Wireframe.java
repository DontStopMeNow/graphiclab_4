package ru.nsu.shmakov.model;

import ru.nsu.shmakov.controller.Panel2DController;
import ru.nsu.shmakov.controller.Panel3DController;
import ru.nsu.shmakov.data.Camera;
import ru.nsu.shmakov.data.Edge3D;
import ru.nsu.shmakov.data.MyMat;
import ru.nsu.shmakov.data.Point3D;
import ru.nsu.shmakov.model.bezier.BezierPath;
import ru.nsu.shmakov.model.utils.*;
import ru.nsu.shmakov.view.MainForm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by kyb1k on 05.05.2015.
 */
public class Wireframe {
    private BufferedImage image2D;
    private BufferedImage image3D;
    private MainForm mainForm;

    private ArrayList<ArrayList<Edge3D >> figuresEdges2D;
    private ArrayList<ArrayList<Edge3D >> figuresEdges3D;
    private ArrayList<ArrayList<Point3D>> figuresPoints;
    private ArrayList<Double[]> params = new ArrayList<>();
    private ArrayList<Color> colors = new ArrayList<>();

    private Camera camera;

    private int currentFigure = 0;
    private double bezierStep = 0.2;
    int routeParam = 15;

    public Wireframe() {
        mainForm = new MainForm();
        figuresEdges2D = new ArrayList<>();
        figuresEdges3D = new ArrayList<>();
        figuresPoints  = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            figuresEdges2D.add(new ArrayList<>());
            figuresEdges3D.add(new ArrayList<>());
            figuresPoints .add(new ArrayList<>());
            params.add(new Double[9]); //translates, scales, routes
            params.get(i)[0] = 0.;
            params.get(i)[1] = 0.;
            params.get(i)[2] = 0.;
            params.get(i)[3] = 1.;
            params.get(i)[4] = 1.;
            params.get(i)[5] = 1.;
            params.get(i)[6] = 0.;
            params.get(i)[7] = 0.;
            params.get(i)[8] = 0.;
        }
        initColors();
        int image2DHeight = mainForm.getImagePanel2DHeight();
        int image2DWidth  = mainForm.getImagePanel2DWidth();

        image2D = new BufferedImage(image2DWidth, image2DHeight, BufferedImage.TYPE_4BYTE_ABGR);
        mainForm.setPanel2DController(new Panel2DController(this));
        mainForm.setPanel3DController(new Panel3DController(this));
        camera = new Camera(new Point3D(0., 0., 0., 1.),
                            new Point3D(1.  , 1.  , 1.  , 1.));

        rerender2D();
        rerender3D();
    }

    private void initColors() {
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);
        colors.add(Color.YELLOW);
        colors.add(Color.PINK);
        colors.add(Color.BLACK);
        colors.add(Color.ORANGE);
        colors.add(Color.WHITE);
    }

    public void clear2D() {
        figuresPoints.get(currentFigure).clear();
        figuresEdges2D.get(currentFigure).clear();
        rerender2D();
        rerender3D();
    }

    public void undo2D() {
        ArrayList<Point3D> currentFigurePoints = figuresPoints.get(currentFigure);
        if (currentFigurePoints.size() < 2)
            currentFigurePoints.clear();
        else
            currentFigurePoints.remove(currentFigurePoints.size() - 1);
        generateEdges();
    }

    public void clicked2D(int x, int y) {
        x = x - image2D.getWidth()/2;
        y = (image2D.getHeight() - y) - image2D.getHeight()/2;
        ArrayList<Point3D> currentFigurePoints = figuresPoints.get(currentFigure);
        currentFigurePoints.add(new Point3D((double) x, (double) y, 0., 1.));
        generateEdges();
    }

    private void generateEdges() {
        ArrayList<Point3D> currentFigurePoints = figuresPoints.get(currentFigure);
        figuresEdges2D.get(currentFigure).clear();
        if(currentFigurePoints.size() < 4 && currentFigurePoints.size() > 1) {
            for (int i = 1; i < currentFigurePoints.size(); ++i) {
                figuresEdges2D.get(currentFigure).add(new Edge3D(figuresPoints.get(currentFigure).get(i - 1), figuresPoints.get(currentFigure).get(i)));
            }
        }
        else if(currentFigurePoints.size() != 1 && currentFigurePoints.size() != 0){
            int last = currentFigurePoints.size() - 1;
            int curves = last/3;
            int linear = currentFigurePoints.size() - (curves*3 + 1);
            ArrayList<Point3D> curvesPoints = new ArrayList<>(currentFigurePoints.subList(0, curves*3 + 1));
            ArrayList<Point3D> linearPoints = new ArrayList<>(currentFigurePoints.subList(currentFigurePoints.size()-linear-1, currentFigurePoints.size()));

            figuresEdges2D.set(currentFigure, BezierPath.createBezierPath(curvesPoints, bezierStep));
            for (int i = 1; i < linearPoints.size(); ++i) {
                figuresEdges2D.get(currentFigure).add(new Edge3D(linearPoints.get(i - 1), linearPoints.get(i)));
            }
        }

        //figuresEdges2D.set(currentFigure, BezierPath.createBezierPath(currentFigurePoints, bezierStep));

        rerender2D();

    }

    public void apply() {
        rerender3D();
    }

    public void changeParams(Double[] p) {
        params.set(currentFigure, p);
        rerender3D();
    }

    public void changeCurrentFigure(int t) {
        currentFigure = t;
        generateEdges();
        rerender3D();
    }

    private MyMat generateWorld(int i) {
        Double[] p = params.get(i);
        MyMat tmp = MyMat.getIdentity();
        MyMat translate = Translator.translate(p[0], p[1], p[2]);
        MyMat scale = Scaler.scale(p[3], p[4], p[5]);
        MyMat routeX = Rotator.rotateX(p[6]);
        MyMat routeY = Rotator.rotateY(p[7]);
        MyMat routeZ = Rotator.rotateZ(p[8]);
        return tmp.multiplex(translate).multiplex(scale).multiplex(routeX).multiplex(routeY).multiplex(routeZ);
    }

    private void rerender3D() {
        int image3DHeight = mainForm.getImagePanel3DHeight();
        int image3DWidth  = mainForm.getImagePanel3DWidth();
        camera = new Camera(new Point3D(-600., -60., 10., 1.),
                 new Point3D(0.  , 0.  , 0.  , 1.));

        image3D = new BufferedImage(image3DWidth, image3DHeight, BufferedImage.TYPE_4BYTE_ABGR);
        for (int i = 0; i < 10; i++) {
            figuresEdges3D.set(i, RotatingBodyCreator.createBody(figuresEdges2D.get(i), routeParam, (int)Math.round(1/bezierStep)));
            Renderer3D.drawSpline(image3D, generateWorld(i), camera, 1, 10, figuresEdges3D.get(i), colors.get(i));
        }

        ArrayList<Edge3D> edges = Grid3DCreator.createGrid();

        //figuresEdges3D.get(currentFigure).add(new Edge3D(new Point3D(-1., 1., 0., 1.), new Point3D(1., 1., 0., 1.)));
        //figuresEdges3D.set(currentFigure, RotatingBodyCreator.createBody(figuresEdges3D.get(currentFigure), 4));
        //ArrayList<Edge3D> edges = new ArrayList<>();
/*

        edges.add(new Edge3D(new Point3D(1. - 1.5, 1. - 1.5, 1. - 1.5, 1.), new Point3D(2. - 1.5, 1. - 1.5, 1. - 1.5, 1.)));
        edges.add(new Edge3D(new Point3D(1. - 1.5, 1. - 1.5, 1. - 1.5, 1.), new Point3D(1. - 1.5, 2. - 1.5, 1. - 1.5, 1.)));
        edges.add(new Edge3D(new Point3D(1. - 1.5, 1. - 1.5, 1. - 1.5, 1.), new Point3D(1. - 1.5, 1. - 1.5, 2. - 1.5, 1.)));

        edges.add(new Edge3D(new Point3D(2. - 1.5, 1. - 1.5, 1. - 1.5, 1.), new Point3D(2. - 1.5, 2. - 1.5, 1. - 1.5, 1.)));
        edges.add(new Edge3D(new Point3D(2. - 1.5, 1. - 1.5, 1. - 1.5, 1.), new Point3D(2. - 1.5, 1. - 1.5, 2. - 1.5, 1.)));

        edges.add(new Edge3D(new Point3D(2. - 1.5, 2. - 1.5, 1. - 1.5, 1.), new Point3D(2. - 1.5, 2. - 1.5, 2. - 1.5, 1.)));
        edges.add(new Edge3D(new Point3D(2. - 1.5, 2. - 1.5, 1. - 1.5, 1.), new Point3D(1. - 1.5, 2. - 1.5, 1. - 1.5, 1.)));

        edges.add(new Edge3D(new Point3D(1. - 1.5, 2. - 1.5, 1. - 1.5, 1.), new Point3D(1. - 1.5, 2. - 1.5, 2. - 1.5, 1.)));

        edges.add(new Edge3D(new Point3D(1. - 1.5, 1. - 1.5, 2. - 1.5, 1.), new Point3D(2. - 1.5, 1. - 1.5, 2. - 1.5, 1.)));
        edges.add(new Edge3D(new Point3D(1. - 1.5, 1. - 1.5, 2. - 1.5, 1.), new Point3D(1. - 1.5, 2. - 1.5, 2. - 1.5, 1.)));

        edges.add(new Edge3D(new Point3D(1. - 1.5, 2. - 1.5, 2. - 1.5, 1.), new Point3D(2. - 1.5, 2. - 1.5, 2. - 1.5, 1.)));

        edges.add(new Edge3D(new Point3D(2. - 1.5, 2. - 1.5, 2. - 1.5, 1.), new Point3D(2. - 1.5, 1. - 1.5, 2. - 1.5, 1.)));
*/
        Renderer3D.drawSpline(image3D, MyMat.getIdentity(), camera, 1, 10,  edges , Color.GRAY);
        mainForm.set3DImage(image3D);
    }

    public double getBezierStep() {
        return bezierStep;
    }

    public void setBezierStep(double bezierStep) {
        this.bezierStep = bezierStep;
        generateEdges();
        rerender3D();
    }

    public int getRouteParam() {
        return routeParam;
    }

    public void setRouteParam(int routeParam) {
        this.routeParam = routeParam;
        rerender3D();
    }

    private void rerender2D() {
        ArrayList<Edge3D> edges = Grid2DCreator.createGrid(image2D.getWidth(), image2D.getHeight(), 10);
        int image2DHeight = mainForm.getImagePanel2DHeight();
        int image2DWidth  = mainForm.getImagePanel2DWidth();

        image2D = new BufferedImage(image2DWidth, image2DHeight, BufferedImage.TYPE_4BYTE_ABGR);
        Renderer2D.drawSpline(image2D, edges , Color.GRAY);
        edges = Grid2DCreator.createAxis(image2D.getWidth(), image2D.getHeight());
        Renderer2D.drawSpline(image2D, edges, Color.GREEN);
        Renderer2D.drawSpline(image2D, figuresEdges2D.get(currentFigure), colors.get(currentFigure));
        Renderer2D.drawPoints(image2D, figuresPoints.get(currentFigure), Color.RED);
        mainForm.set2DImage(image2D);
    }
}
