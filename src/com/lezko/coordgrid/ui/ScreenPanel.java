package com.lezko.coordgrid.ui;

import com.lezko.coordgrid.drawer.TextDrawer;
import com.lezko.coordgrid.geometry.Circle;
import com.lezko.coordgrid.geometry.Line;
import com.lezko.coordgrid.geometry.Rect;
import com.lezko.coordgrid.grid.Grid;
import com.lezko.coordgrid.screen.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class ScreenPanel extends JPanel {

    private final Screen screen;
    private final Graphics2D imageGraphics;
    private final BufferedImage image;

    private final double SCALE_RATE = 0.3;
    private final double MIN_SCALE = 0.01;
    private final double MAX_SCALE = 10;
    private final int STEP_INTERVAL = 10;
    private final int ANIMATION_FRAMES_COUNT = 20;
    private static final int ANIMATION_DELAY = 10;

    private int width, height;

    private double currentScale = 1;
    private boolean isResizing = false;

    public ScreenPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        imageGraphics = image.createGraphics();

        this.width = width;
        this.height = height;

        screen = new Screen((x, y, c) -> {
            imageGraphics.fillRect(x, y, 1, 1);
        }, width, height);

        Grid grid = new Grid(100, 100, 200, new TextDrawer() {
            @Override
            public void drawText(int x, int y, int size, String text, Color c) {
                imageGraphics.drawString(text, x, y);
            }
        });
        grid.setBeforeRender(() -> imageGraphics.setColor(new Color(222, 222, 222)));
        grid.setAfterRender(() -> imageGraphics.setColor(Color.GRAY));
        screen.addObject(grid);

        screen.addObject(new Circle(100, 300, 100));
        screen.addObject(new Rect(20, -20, 300, 100));
        screen.addObject(new Line(60, -60, 160, 160));

        screen.setX(-width / 2.0);
        screen.setY(-height / 2.0);

        initKeyListeners();
        initMouseListeners();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                animateReset();
            }
        }, 3000);
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
            if (isResizing) {
                return;
            }

            currentScale -= currentScale * SCALE_RATE * e.getWheelRotation();

            if (currentScale > MAX_SCALE) {
                currentScale = MAX_SCALE;
            }
            if (currentScale < MIN_SCALE) {
                currentScale = MIN_SCALE;
            }

            animateResize(e.getX(), e.getY(), currentScale);
        });
    }

    private void initKeyListeners() {
        // UP
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "ACTION_UP");
        getActionMap().put("ACTION_UP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double newY = screen.getY() - STEP_INTERVAL;
                screen.setY(newY);
                repaint();
            }
        });
        // DOWN
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "ACTION_DOWN");
        getActionMap().put("ACTION_DOWN", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double newY = screen.getY() + STEP_INTERVAL;
                screen.setY(newY);
                repaint();
            }
        });
        // LEFT
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "ACTION_LEFT");
        getActionMap().put("ACTION_LEFT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double newX = screen.getX() - STEP_INTERVAL;
                screen.setX(newX);
                repaint();
            }
        });
        // RIGHT
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "ACTION_RIGHT");
        getActionMap().put("ACTION_RIGHT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double newX = screen.getX() + STEP_INTERVAL;
                screen.setX(newX);
                repaint();
            }
        });
    }

    public void setWidth(int width) {
        this.width = width;
        setPreferredSize(new Dimension(this.width, this.height));
        screen.setWidth(width);
        repaint();
    }

    public void setHeight(int height) {
        this.height = height;
        setPreferredSize(new Dimension(this.width, this.height));
        screen.setHeight(height);
        repaint();
    }

    private void resizeScreen(double clientX, double clientY, double scale) {
        double dx = clientX / width;
        double dy = clientY / height;
        double x = clientX / screen.getScale();
        double y = clientY / screen.getScale();

        double newX = screen.getX() + x - width / scale * dx;
        double newY = screen.getY() + y - height / scale * dy;

        screen.update(newX, newY, scale);
    }

    private void animateReset() {
        double dx = (-width / 2.0 / currentScale - screen.getX()) / ANIMATION_FRAMES_COUNT;
        double dy = (-height / 2.0 / currentScale - screen.getY()) / ANIMATION_FRAMES_COUNT;
        double dScale = (1 - screen.getScale()) / ANIMATION_FRAMES_COUNT;

        final int[] counter = { 0 };
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (counter[0]++ == ANIMATION_FRAMES_COUNT) {
                    cancel();
                    return;
                }
                screen.setX(screen.getX() + dx);
                screen.setY(screen.getY() + dy);
                repaint();

            }
        }, 0, ANIMATION_DELAY);
    }

    private void animateResize(double clientX, double clientY, double scale) {
        double dScale = (scale - screen.getScale()) / ANIMATION_FRAMES_COUNT;
        double initialScale = screen.getScale();

        isResizing = true;
        final int[] counter = { 0 };
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                resizeScreen(clientX, clientY, initialScale + dScale * counter[0]);
                repaint();

                if (counter[0]++ == ANIMATION_FRAMES_COUNT) {
                    isResizing = false;
                    cancel();
                }
            }
        }, 0, ANIMATION_DELAY);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;

        imageGraphics.setColor(Color.WHITE);
        imageGraphics.fillRect(0, 0, getWidth(), getHeight());
        imageGraphics.setColor(Color.GRAY);

        screen.render();

        g2d.drawImage(image, 0, 0, null);
    }
}
