package ru.nsu.shmakov.view;

import ru.nsu.shmakov.controller.Panel2DController;
import ru.nsu.shmakov.controller.Panel3DController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.DimensionUIResource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by kyb1k on 05.05.2015.
 */
public class MainForm extends JFrame {
    private Panel2DController panel2DController;
    private Panel3DController panel3DController;

    private JPanel rootPanel;
    private ImagePanel imagePanel2D;
    private JButton applyButton;
    private JButton undoButton;
    private JButton clearButton;
    private ImagePanel imagePanel3D;
    private JSlider bezierSlider;
    private JSlider routeStepSlider;
    private JSpinner translateXSpinner;
    private JSpinner translateZSpinner;
    private JSpinner translateYSpinner;
    private JSpinner scaleXSpinner;
    private JSpinner scaleYSpinner;
    private JSpinner scaleZSpinner;
    private JSpinner routeXSpinner;
    private JSpinner routeYSpinner;
    private JSpinner routeZSpinner;
    private JButton applyParamButton;
    private JButton body5Button;
    private JButton body1Button;
    private JButton body2Button;
    private JButton body3Button;
    private JButton body4Button;


    public MainForm() {
        super("Main Frame");
        setContentPane(rootPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new DimensionUIResource(600, 600));

        setSize(900, 600);
        setVisible(true);

        imagePanel2D.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel2DController.clicked(e.getX(), e.getY());
                repaint();
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel2DController.undo();
                repaint();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel2DController.clear();
                repaint();
            }
        });
        repaint();

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel2DController.apply();
                repaint();
            }
        });

        bezierSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                panel2DController.changeBezierStep(1./ bezierSlider.getValue());
                repaint();
            }
        });
        routeStepSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                panel3DController.changeRouteParam(routeStepSlider.getValue());
                repaint();
            }
        });
        applyParamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double[] p = new Double[9];
                p[0] = (Double)((Integer)(translateXSpinner.getValue())).doubleValue();
                p[1] = (Double)((Integer)(translateYSpinner.getValue())).doubleValue();
                p[2] = (Double)((Integer)(translateZSpinner.getValue())).doubleValue();

                p[3] = (Double)((Integer)(scaleXSpinner.getValue())).doubleValue();
                p[4] = (Double)((Integer)(scaleYSpinner.getValue())).doubleValue();
                p[5] = (Double)((Integer)(scaleZSpinner.getValue())).doubleValue();

                p[6] = (Double)((Integer)(routeXSpinner.getValue())).doubleValue()*Math.PI/180.;
                p[7] = (Double)((Integer)(routeYSpinner.getValue())).doubleValue()*Math.PI/180.;
                p[8] = (Double)((Integer)(routeZSpinner.getValue())).doubleValue()*Math.PI/180.;

                panel3DController.changeParams(p);
                repaint();
            }
        });
        body1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel3DController.changeCurrentFigure(0);
                repaint();
            }
        });
        body2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel3DController.changeCurrentFigure(1);
                repaint();
            }
        });
        body3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel3DController.changeCurrentFigure(2);
                repaint();
            }
        });
        body4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel3DController.changeCurrentFigure(3);
                repaint();
            }
        });
        body5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel3DController.changeCurrentFigure(4);
                repaint();
            }
        });
    }

    public int getImagePanel2DHeight () {
        return imagePanel2D.getHeight();
    }
    public int getImagePanel2DWidth () {
        return imagePanel2D.getWidth();
    }

    public void set2DImage(BufferedImage image) {
        imagePanel2D.setImage(image);
    }

    public int getImagePanel3DHeight () {
        return imagePanel2D.getHeight();
    }
    public int getImagePanel3DWidth () {
        return imagePanel2D.getWidth();
    }

    public void set3DImage(BufferedImage image) {
        imagePanel3D.setImage(image); repaint();
    }


    public Panel2DController getPanel2DController() {
        return panel2DController;
    }

    public void setPanel2DController(Panel2DController panel2DController) {
        this.panel2DController = panel2DController;
    }

    public Panel3DController getPanel3DController() {
        return panel3DController;
    }

    public void setPanel3DController(Panel3DController panel3DController) {
        this.panel3DController = panel3DController;
    }
}
