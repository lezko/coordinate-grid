package com.lezko.grid.ui;

import com.lezko.grid.Grid;
import com.lezko.grid.line.SourceLine;
import com.lezko.grid.point.SourcePoint;
import com.lezko.grid.screen.ScreenAdapter;
import com.lezko.grid.screen.SourceScreen;
import com.lezko.grid.screen.TargetScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ScreenPanel extends JPanel {

    private final SourceScreen sourceScreen;
    private final TargetScreen targetScreen;
    private final ScreenAdapter adapter;

    private final double SCALE_RATE = 0.1;
    private final double MIN_SCALE = 0.3;
    private final double MAX_SCALE = 2;
    private final int STEP_INTERVAL = 10;

    private double currentScale = 1;

    public ScreenPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));

        sourceScreen = new SourceScreen(width, height);
        targetScreen = new TargetScreen(width, height);
        adapter = new ScreenAdapter(sourceScreen, targetScreen);

        initKeyListeners();
        initMouseListeners();

        Grid.createGrid(sourceScreen, 30);
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
                adapter.setTargetX(adapter.getTargetX() + (holder.x - e.getX()) / currentScale);
                adapter.setTargetY(adapter.getTargetY() + (holder.y - e.getY()) / currentScale);
                repaint();

                holder.x = e.getX();
                holder.y = e.getY();
            }
        });

        addMouseWheelListener(e -> {
            adapter.setTargetX(adapter.getTargetX() / currentScale);
            adapter.setTargetY(adapter.getTargetY() / currentScale);

            currentScale -= e.getWheelRotation() * SCALE_RATE;

            currentScale = Math.max(currentScale, MIN_SCALE);
            currentScale = Math.min(currentScale, MAX_SCALE);

            adapter.setScaleRate(currentScale);
            adapter.setTargetX(adapter.getTargetX() * currentScale);
            adapter.setTargetY(adapter.getTargetY() * currentScale);
            adapter.updateTarget();
            repaint();
        });
    }

    private void initKeyListeners() {
        // UP
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "ACTION_UP");
        getActionMap().put("ACTION_UP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                adapter.setTargetY(adapter.getTargetY() - STEP_INTERVAL);
                adapter.updateTarget();
                repaint();
            }
        });
        // DOWN
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "ACTION_DOWN");
        getActionMap().put("ACTION_DOWN", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                adapter.setTargetY(adapter.getTargetY() + STEP_INTERVAL);
                adapter.updateTarget();
                repaint();
            }
        });
        // LEFT
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "ACTION_LEFT");
        getActionMap().put("ACTION_LEFT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                adapter.setTargetX(adapter.getTargetX() - STEP_INTERVAL);
                adapter.updateTarget();
                repaint();
            }
        });
        // RIGHT
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "ACTION_RIGHT");
        getActionMap().put("ACTION_RIGHT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                adapter.setTargetX(adapter.getTargetX() + STEP_INTERVAL);
                adapter.updateTarget();
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;

        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D imageGraphics = image.createGraphics();
        imageGraphics.setColor(Color.WHITE);
        imageGraphics.fillRect(0, 0, getWidth(), getHeight());
        imageGraphics.setColor(Color.GRAY);

        adapter.updateTarget();
        adapter.renderTarget(imageGraphics);

        g2d.drawImage(image, 0, 0, null);
        imageGraphics.dispose();
    }
}
