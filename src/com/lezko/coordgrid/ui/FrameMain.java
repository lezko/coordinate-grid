package com.lezko.coordgrid.ui;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

public class FrameMain extends JFrame {

    public FrameMain(int width, int height) throws IOException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        AppPanel appPanel = new AppPanel(width, height);
        add(appPanel);
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

    public static void main(String[] args) throws IOException {
        new FrameMain(800, 600);
    }
}
