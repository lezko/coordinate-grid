package com.lezko.coordgrid.ui;

import javax.swing.*;

public class FrameMain extends JFrame {

    public FrameMain(int width, int height) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new ScreenPanel(width, height));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new FrameMain(800, 600);
    }
}
