package com.lezko.coordgrid.ui;

import com.lezko.coordgrid.geometry.*;
import com.lezko.coordgrid.geometry.Point;
import com.lezko.coordgrid.screen.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ScreenPanel extends JPanel {

    private final Screen screen;
    private final Graphics2D imageGraphics;
    private final BufferedImage image;

    private final double SCALE_RATE = 0.1;
    private final double SCALE_STEP = 0.1;
    private final double MIN_SCALE = 0.3;
    private final double MAX_SCALE = 2;
    private final int STEP_INTERVAL = 10;

    private int width, height;

    private double currentScale = 1;

    public ScreenPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.width = width;
        this.height = height;
        imageGraphics = image.createGraphics();

        screen = new Screen(imageGraphics, width, height);

        screen.addObject(new Circle(30, 30, 100));
        screen.addObject(new Line(60, 60, 160, 160));
        initKeyListeners();
        initMouseListeners();
    }

    private void initMouseListeners() {

        class PointHolder {
            int x;
            int y;
        }
        PointHolder holder = new PointHolder();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                holder.x = e.getX();
                holder.y = e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                screen.setX(screen.getX() - (e.getX() - holder.x) / currentScale);
                screen.setY(screen.getY() - (e.getY() - holder.y) / currentScale);
                repaint();

                holder.x = e.getX();
                holder.y = e.getY();
            }
        });

        addMouseWheelListener(e -> {
            double dx = (double) e.getX() / width;
            double dy = (double) e.getY() / height;
            double x = e.getX() / currentScale;
            double y = e.getY() / currentScale;

            currentScale -= e.getWheelRotation() * SCALE_RATE;

            if (currentScale > MAX_SCALE) {
                currentScale = MAX_SCALE;
                return;
            }
            if (currentScale < MIN_SCALE) {
                currentScale = MIN_SCALE;
                return;
            }

            screen.setScale(currentScale);
            screen.setX(screen.getX() + x - screen.getAreaWidth() * dx);
            screen.setY(screen.getY() + y - screen.getAreaHeight() * dy);
            screen.update();
            repaint();
        });
    }

    private void initKeyListeners() {
        // UP
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "ACTION_UP");
        getActionMap().put("ACTION_UP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                screen.setY(screen.getY() - STEP_INTERVAL);
                screen.update();
                repaint();
            }
        });
        // DOWN
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "ACTION_DOWN");
        getActionMap().put("ACTION_DOWN", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                screen.setY(screen.getY() + STEP_INTERVAL);
                screen.update();
                repaint();
            }
        });
        // LEFT
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "ACTION_LEFT");
        getActionMap().put("ACTION_LEFT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                screen.setX(screen.getX() - STEP_INTERVAL);
                screen.update();
                repaint();
            }
        });
        // RIGHT
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "ACTION_RIGHT");
        getActionMap().put("ACTION_RIGHT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                screen.setX(screen.getX() + STEP_INTERVAL);
                screen.update();
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;

        imageGraphics.setColor(Color.WHITE);
        imageGraphics.fillRect(0, 0, getWidth(), getHeight());
        imageGraphics.setColor(Color.GRAY);

        screen.update();

        g2d.drawImage(image, 0, 0, null);
    }
}
