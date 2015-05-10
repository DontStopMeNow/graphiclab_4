package ru.nsu.shmakov.model;

import ru.nsu.shmakov.data.Edge3D;
import ru.nsu.shmakov.data.Point3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by kyb1k on 28.04.2015.
 */
public class Renderer2D {
    public static void drawPoints(BufferedImage image, ArrayList<Point3D> points, Color color) {
        int width  = image.getWidth ();
        int height = image.getHeight();


        for (Point3D point : points) {
            image.setRGB(point.x.intValue() + width/2, (height - point.y.intValue()) - height/2, color.getRGB());
        }

    }
    public static void drawSpline(BufferedImage image, ArrayList<Edge3D > edges , Color color) {
        int width  = image.getWidth ();
        int height = image.getHeight();

        for (Edge3D edge : edges) {
            int x = edge.p1.x.intValue() + width/2;
            int y = (height - edge.p1.y.intValue()) - height/2;
            if (x >= 0 && x < width && y >= 0 && y < height)
                image.setRGB(x, y, color.getRGB());

            drawLine(image, edge.p1, edge.p2, color);

            x = edge.p2.x.intValue() + width/2;
            y = (height - edge.p2.y.intValue()) - height/2;
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
                int xx = width/2 + y;
                int yy = (height - x) - height/2;
                if (xx >= 0 && xx < width && yy >= 0 && yy < height)
                    image.setRGB(xx, yy, color.getRGB());
            } else {

                int xx = width/2 + x;
                int yy = (height - y) - height/2;
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
