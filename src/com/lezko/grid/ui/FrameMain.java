package com.lezko.grid.ui;

import com.lezko.grid.screen.TargetScreen;

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
