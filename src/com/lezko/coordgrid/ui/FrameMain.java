package com.lezko.coordgrid.ui;

import javax.swing.*;

public class FrameMain extends JFrame {

    public FrameMain(int width, int height) {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new ScreenPanel(width, height));
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new FrameMain(800, 600);
    }
}
