package com.lezko.grid.frame;

import javax.swing.*;

public class FrameMain extends JFrame {

    public FrameMain(int width, int height) {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
