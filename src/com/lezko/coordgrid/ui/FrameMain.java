package com.lezko.coordgrid.ui;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class FrameMain extends JFrame {

    public FrameMain(int width, int height) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ScreenPanel screenPanel = new ScreenPanel(width, height);
        add(screenPanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

//        addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                screenPanel.setWidth(e.getComponent().getWidth());
//                screenPanel.setHeight(e.getComponent().getHeight());
//                revalidate();
//                repaint();
//            }
//        });
    }

    public static void main(String[] args) {
        new FrameMain(800, 600);
    }
}
