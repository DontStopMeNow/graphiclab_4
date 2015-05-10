package ru.nsu.shmakov;

import ru.nsu.shmakov.data.*;
import ru.nsu.shmakov.model.Renderer2D;
import ru.nsu.shmakov.model.Wireframe;
import ru.nsu.shmakov.model.bezier.BezierPath;
import ru.nsu.shmakov.model.utils.Grid2DCreator;
import ru.nsu.shmakov.model.utils.MatProjCreator;
import ru.nsu.shmakov.model.utils.MatViewCreator;
import ru.nsu.shmakov.model.utils.Rotator;
import ru.nsu.shmakov.view.MainForm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by kyb1k on 28.04.2015.
 */
public class Main {
    public static void main(String[] args) {
        /*MyMat m1 = new MyMat();
        m1.set(0, 0, new Double(3));
        m1.set(0, 1, new Double(1));
        m1.set(1, 0, new Double(2));
        m1.set(3, 3, new Double(5));
        //System.out.println(m1);

        MyMat m2 = new MyMat();
        m2.set(0, 0, new Double(3));
        m2.set(3, 1, new Double(1));
        m2.set(1, 0, new Double(2));
        m2.set(3, 3, new Double(5));
        //System.out.println(m1.transposition().toString());

        Point3D point = new Point3D(new Double(1), new Double(1), new Double(1), new Double(1));
        //System.out.println(m1.multiplex(point).toString());
        System.out.println(Rotator.rotateZ(Math.PI / 2));
        //System.out.println(m1.sum(m2).toString());
        //System.out.println(m1.multiplex(m2).toString());

        MainForm mainForm = new MainForm();
        int width2D  = mainForm.getImagePanel2DWidth ();
        int height2D = mainForm.getImagePanel2DHeight();

        BufferedImage image = new BufferedImage(width2D, height2D, BufferedImage.TYPE_4BYTE_ABGR);
        ArrayList<Point3D> points = new ArrayList<>();

        points.add(new Point3D(200., 3., 0., 1.));
        points.add(new Point3D(3., 200., 0., 1.));
        points.add(new Point3D(200., 200., 0., 1.));
        points.add(new Point3D(400., 400., 0., 1.));


        ArrayList<Edge3D> edges = BezierPath.createBezierPath(points, 0.01);
        Renderer2D.drawSpline(image, edges , Color.BLUE);
        edges = Grid2DCreator.createGrid(image.getWidth(), image.getHeight(), 10);
        Renderer2D.drawSpline(image, edges , Color.GRAY);
        edges = Grid2DCreator.createAxis(image.getWidth(), image.getHeight());
        Renderer2D.drawSpline(image, edges, Color.GREEN);
        //Renderer2D.drawPoints(image, points, Color.RED );
        mainForm.set2DImage(image);
        Camera camera = new Camera(new Point3D(100., 100., -500., 1.), new Point3D(100., 100., 500., 1.));
        MyMat mat2 = MatViewCreator.createMatView(camera);
        System.out.println(mat2.toString());
        Screen screen = new Screen(200, 200, 4., 8.);
        MyMat mat3 = MatProjCreator.createProjMat(screen);
        System.out.println(mat3.toString());*/
        Wireframe wireframe = new Wireframe();
    }
}
