package ru.nsu.shmakov.model;

import ru.nsu.shmakov.data.*;
import ru.nsu.shmakov.model.utils.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by kyb1k on 06.05.2015.
 */
public class Renderer3D {
    public static void drawPoints(BufferedImage image, MyMat world, Camera camera, double near, double far, ArrayList<Point3D> points, Color color) {
        int width  = image.getWidth ();
        int height = image.getHeight();
        for (Point3D point : points) {
            Point3D newPoint = transform(point, image, world, camera, near, far);
            image.setRGB(newPoint.x.intValue() + width/2, (height - newPoint.y.intValue()) - height/2, color.getRGB());
        }

    }

    public static Point3D transform(Point3D point, BufferedImage image, MyMat world, Camera camera, double near, double far) {
        int width  = image.getWidth ();
        int height = image.getHeight();

        Screen screen = new Screen(width, height, near, far);
        MyMat view = MatViewCreator.createMatView(camera).transposition();
        MyMat proj = MatProjCreator.createProjMat(screen).transposition();
        MyMat clip = MatClipCreator.crateClipMat().transposition();
        MyMat mvs  = MatMvsCreator .createMvsMat(screen).transposition();

        Point3D newPoint = world.multiplex(point);
        newPoint = view.multiplex(newPoint);
        //System.out.println(mvs.toString());

        newPoint = proj.multiplex(newPoint);
        newPoint = clip.multiplex(newPoint);
        newPoint = mvs .multiplex(newPoint);

        System.out.println(newPoint.a);
        double w = newPoint.a;

        //System.out.println(newPoint.z);
        newPoint.x = newPoint.x/w;
        newPoint.y = newPoint.y/w;
        newPoint.z = newPoint.z/w;



        /*newPoint.x = newPoint.x/w;
        newPoint.y = newPoint.y/w;
        newPoint.z = newPoint.z/w;*/
        return newPoint;
    }

    public static void drawSpline(BufferedImage image, MyMat world, Camera camera, double near, double far, ArrayList<Edge3D> edges , Color color) {
        int width  = image.getWidth ();
        int height = image.getHeight();

        for (Edge3D edge : edges) {
            edge.p1 = transform(edge.p1, image, world, camera, near, far);
            edge.p2 = transform(edge.p2, image, world, camera, near, far);

            if(!Clipper.isDraw(edge, 100., 1000.))
                continue;

//            int x = edge.p1.x.intValue() + width/2;
//            int y = (height - edge.p1.y.intValue()) - height/2;
            int x = edge.p1.x.intValue();
            int y = edge.p1.y.intValue();

            if (x >= 0 && x < width && y >= 0 && y < height)
                image.setRGB(x, y, color.getRGB());

            drawLine(image, edge.p1, edge.p2, color);

            //x = edge.p2.x.intValue() + width/2;
            //y = (height - edge.p2.y.intValue()) - height/2;
            x = edge.p2.x.intValue();
            y = edge.p2.y.intValue();


            if (x >= 0 && x < width && y >= 0 && y < height)
                image.setRGB(x, y, color.getRGB());

        }
    }
    public static void drawLine(BufferedImage image, Point3D p1, Point3D p2, Color color) {
        int width  = image.getWidth ();
        int height = image.getHeight();

        int x1 = p1.x.intValue();
        int x2 = p2.x.intValue();
        int y1 = p1.y.intValue();
        int y2 = p2.y.intValue();

        boolean steep = false;
        int tmp = 0;
        if (Math.abs(x1 - x2)<Math.abs(y1 - y2)) {
            tmp = x1;
            x1 = y1;
            y1 = tmp;

            tmp = x2;
            x2 = y2;
            y2 = tmp;
            steep = true;
        }

        if (x1 > x2) {
            tmp = x1;
            x1 = x2;
            x2 = tmp;

            tmp = y1;
            y1 = y2;
            y2 = tmp;
        }

        int dx = x2 - x1;
        int dy = y2 - y1;
        int derror = Math.abs(dy)*2;
        int error = 0;

        int y = y1;
        for (int x = x1; x<=x2; x++) {
            if (steep) {
                int xx = y;
                int yy = x;
                if (xx >= 0 && xx < width && yy >= 0 && yy < height)
                    image.setRGB(xx, yy, color.getRGB());
            } else {

                int xx = x;
                int yy = y;
                if (xx >= 0 && xx < width && yy >= 0 && yy < height)
                    image.setRGB(xx, yy, color.getRGB());
            }
            error += derror;

            if (error > dx) {
                y += (y2 > y1 ? 1:-1);
                error -= dx*2;
            }
        }
    }
}
