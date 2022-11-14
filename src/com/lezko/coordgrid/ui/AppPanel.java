package com.lezko.coordgrid.ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AppPanel extends JPanel {

    private final ScreenPanel screenPanel;
    private final Toolbar toolbar;

    private int width, height;

    public AppPanel(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        screenPanel = new ScreenPanel(width, height - 100);
        toolbar = new Toolbar(screenPanel, screenPanel.getScreen(), screenPanel::repaint);
        add(toolbar);

        add(screenPanel);
    }
}
