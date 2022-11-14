package com.lezko.coordgrid.ui;

import com.lezko.coordgrid.screen.Screen;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Toolbar extends JPanel {

    private final ScreenPanel screenPanel;
    private final Screen screen;
    private final Runnable onUpdate;

    public Toolbar(ScreenPanel screenPanel, Screen screen, Runnable onUpdate) throws IOException {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        this.screenPanel = screenPanel;
        this.screen = screen;
        this.onUpdate = onUpdate;

        initButtons();
    }

    private void initButtons() throws IOException {
        ButtonGroup buttonGroup = new ButtonGroup(onUpdate);

        buttonGroup.addButton(new AxisTopLeftButton(screen));
        buttonGroup.addButton(new AxisBottomLeftButton(screen));

        AxisTopRightButton axisTopRightButton = new AxisTopRightButton(screen);
        buttonGroup.addButton(axisTopRightButton);
        axisTopRightButton.setActive(true);

        buttonGroup.addButton(new AxisBottomRightButton(screen));

        add(buttonGroup);

        add(new ResetButton(screenPanel));
        add(new ShowAxisButton());
//        revalidate();
    }
}
