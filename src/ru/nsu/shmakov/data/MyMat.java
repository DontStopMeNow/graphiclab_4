package ru.nsu.shmakov.data;

import java.awt.*;

/**
 * Created by kyb1k on 28.04.2015.
 */
public class MyMat implements Cloneable {
    private Double[][] mat;

    public MyMat() {
        this.mat = new Double[4][4];
        for (int i = 0; i < 4; i++) {
            mat[i] = new Double[4];
            for (int j = 0; j < 4; j++) {
                mat[i][j] = new Double(0);
            }
        }
    }

    public MyMat(Double[][] mat) {
        if(mat.length != 4 || mat[0].length != 4) {
            throw new RuntimeException("Invalid mat size");
        }
        this.mat = mat;
    }

    public Double get(int x, int y) {
        if(x > mat.length || x < 0 || y > mat[0].length || y < 0) {
            throw new RuntimeException("Out of mat range");
        }
        return mat[x][y];
    }

    public void set(int x, int y, Double value) {
        if(x > mat.length || x < 0 || y > mat[0].length || y < 0) {
            throw new RuntimeException("Out of mat range");
        }
        mat[x][y] = value;
    }

    public Double[][] getAsDoubleArray() {
        return mat;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }

    public MyMat sum(MyMat another) {
        MyMat result = new MyMat();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                result.mat[i][j] = this.mat[i][j] + another.mat[i][j];
            }
        }
        return result;
    }

    public MyMat multiplex(MyMat another) {
        MyMat result = new MyMat();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                double tmp = 0;
                for (int k = 0; k < 4; k++) {
                    tmp += this.mat[k][j]*another.mat[i][k];
                }
                result.mat[i][j] = tmp;
            }
        }
        return result;
    }

    public Point3D multiplex(Point3D point) {
        Double[] res = new Double[4];
        Double[] vect = new Double[4];
        vect[0] = point.x; vect[1] = point.y; vect[2] = point.z; vect[3] = point.a;



        for (int i = 0; i < 4; i++) {
            double tmp = 0;
            for (int j = 0; j < 4; j++) {
                tmp += this.mat[j][i]*vect[j];
            }
            res[i] = new Double(tmp);
        }
        return new Point3D(res[0], res[1], res[2], res[3]);
    }
    
    public MyMat transposition() {
        MyMat result = new MyMat();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result.mat[i][j] = this.mat[j][i];
            }
        }
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(mat[j][i]).append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
